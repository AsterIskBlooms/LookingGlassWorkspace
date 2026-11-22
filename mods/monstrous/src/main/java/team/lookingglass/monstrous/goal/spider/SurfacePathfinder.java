package team.lookingglass.monstrous.goal.spider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

/**
 * Over "cling cells": air cells touching a solid face, where the spider's real hitbox fits when pushed
 * flush against that face. Floors, walls and ceilings are all just nodes.
 */
public final class SurfacePathfinder {
    private static final int MAX_EXPANSIONS = 400;
    private static final int RANGE = 20;
    private static final double OFF_FLOOR_PENALTY = 0.3D;

    /** face = the surface the spider clings to at this cell: DOWN floor, UP ceiling, horizontal = wall on that side. */
    public record Step(BlockPos cell, Vec3 rest, Direction face) {}

    private static final class Node {
        final BlockPos pos;
        final Step step;
        Node parent;
        double g;
        double f;

        Node(BlockPos pos, Step step) {
            this.pos = pos;
            this.step = step;
        }
    }

    private SurfacePathfinder() {}

    public static @Nullable List<Step> find(Level level, Spider spider, Vec3 goal) {
        BlockPos start = BlockPos.containing(spider.getX(), spider.getY() + spider.getBbHeight() * 0.5D, spider.getZ());
        if (unloaded(level, start)) return null;

        Map<Long, Optional<Step>> steps = new HashMap<>();
        Map<Long, Node> nodes = new HashMap<>();
        Set<Long> closed = new HashSet<>();
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingDouble((Node n) -> n.f));

        Node first = new Node(start, new Step(start, spider.position(), Direction.DOWN));
        first.f = heuristic(start, goal);
        nodes.put(start.asLong(), first);
        open.add(first);

        Node best = first;
        double bestH = first.f;
        double startH = first.f;
        int expansions = 0;

        while (!open.isEmpty() && expansions < MAX_EXPANSIONS) {
            Node cur = open.poll();
            if (!closed.add(cur.pos.asLong())) continue;
            expansions++;

            if (atGoal(cur.pos, goal)) return build(cur);
            double h = heuristic(cur.pos, goal);
            if (h < bestH) {
                bestH = h;
                best = cur;
            }

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        int axes = (dx != 0 ? 1 : 0) + (dy != 0 ? 1 : 0) + (dz != 0 ? 1 : 0);
                        if (axes == 0 || axes == 3) continue;

                        BlockPos np = cur.pos.offset(dx, dy, dz);
                        if (closed.contains(np.asLong())) continue;
                        if (Math.abs(np.getX() - start.getX()) > RANGE
                                || Math.abs(np.getY() - start.getY()) > RANGE
                                || Math.abs(np.getZ() - start.getZ()) > RANGE) continue;
                        if (!cornerClear(level, cur.pos, dx, dy, dz)) continue;

                        Step st = stepFor(level, spider, np, steps);
                        if (st == null) continue;

                        double g = cur.g + Math.sqrt(axes) + (st.face() == Direction.DOWN ? 0.0D : OFF_FLOOR_PENALTY);
                        Node existing = nodes.get(np.asLong());
                        if (existing != null && existing.g <= g) continue;

                        Node next = new Node(np, st);
                        next.parent = cur;
                        next.g = g;
                        next.f = g + heuristic(np, goal);
                        nodes.put(np.asLong(), next);
                        open.add(next);
                    }
                }
            }
        }

        if (best != first && bestH < startH - 3.0D) return build(best);
        return null;
    }

    private static List<Step> build(Node end) {
        List<Step> out = new ArrayList<>();
        for (Node n = end; n != null; n = n.parent) out.add(n.step);
        Collections.reverse(out);
        return out;
    }

    private static boolean atGoal(BlockPos p, Vec3 goal) {
        double dx = p.getX() + 0.5D - goal.x;
        double dz = p.getZ() + 0.5D - goal.z;
        double dy = p.getY() - goal.y;
        return dx * dx + dz * dz <= 2.25D && dy >= -1.5D && dy <= 4.5D;
    }

    private static double heuristic(BlockPos p, Vec3 goal) {
        double dx = p.getX() + 0.5D - goal.x;
        double dy = p.getY() + 0.5D - goal.y;
        double dz = p.getZ() + 0.5D - goal.z;
        return Math.max(0.0D, Math.sqrt(dx * dx + dy * dy + dz * dz) - 1.5D);
    }

    // diagonal moves must not cut through solid corners
    private static boolean cornerClear(Level level, BlockPos from, int dx, int dy, int dz) {
        if (dx != 0 && notPassable(level, from.offset(dx, 0, 0))) return false;
        if (dy != 0 && notPassable(level, from.offset(0, dy, 0))) return false;
        return dz == 0 || !notPassable(level, from.offset(0, 0, dz));
    }

    private static boolean notPassable(Level level, BlockPos pos) {
        return !level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
    }

    private static boolean sturdy(Level level, BlockPos pos, Direction face) {
        return level.getBlockState(pos).isFaceSturdy(level, pos, face);
    }

    private static @Nullable Step stepFor(Level level, Spider s, BlockPos cell, Map<Long, Optional<Step>> cache) {
        return cache.computeIfAbsent(cell.asLong(), k -> Optional.ofNullable(computeStep(level, s, cell))).orElse(null);
    }

    private static @Nullable Step computeStep(Level level, Spider s, BlockPos cell) {
        // never let a path search load chunks
        if (unloaded(level, cell)) return null;

        BlockState here = level.getBlockState(cell);
        if (!here.getCollisionShape(level, cell).isEmpty() || !here.getFluidState().isEmpty()) return null;

        boolean down = sturdy(level, cell.below(), Direction.UP);
        boolean up = sturdy(level, cell.above(), Direction.DOWN);
        boolean east = sturdy(level, cell.east(), Direction.WEST);
        boolean west = sturdy(level, cell.west(), Direction.EAST);
        boolean south = sturdy(level, cell.south(), Direction.NORTH);
        boolean north = sturdy(level, cell.north(), Direction.SOUTH);

        Direction face;
        if (down) face = Direction.DOWN;
        else if (up) face = Direction.UP;
        else if (east) face = Direction.EAST;
        else if (west) face = Direction.WEST;
        else if (south) face = Direction.SOUTH;
        else if (north) face = Direction.NORTH;
        else return null;

        double w = s.getBbWidth() * 0.5D;
        double h = s.getBbHeight();
        double x = cell.getX() + 0.5D;
        double z = cell.getZ() + 0.5D;
        double y = cell.getY();
        if (east) x = cell.getX() + 1.0D - w;
        else if (west) x = cell.getX() + w;
        if (south) z = cell.getZ() + 1.0D - w;
        else if (north) z = cell.getZ() + w;
        if (!down && up) y = cell.getY() + 1.0D - h;

        AABB box = new AABB(x - w, y, z - w, x + w, y + h, z + w).deflate(1.0E-3D);
        if (!level.noBlockCollision(s, box)) return null;

        return new Step(cell, new Vec3(x, y, z), face);
    }

    private static boolean unloaded(Level level, BlockPos pos) {
        return level.getChunk(
                SectionPos.blockToSectionCoord(pos.getX()),
                SectionPos.blockToSectionCoord(pos.getZ()),
                ChunkStatus.FULL,
                false) == null;
    }
}