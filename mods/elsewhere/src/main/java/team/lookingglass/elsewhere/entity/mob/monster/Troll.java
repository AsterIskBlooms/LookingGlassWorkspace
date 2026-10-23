package team.lookingglass.elsewhere.entity.mob.monster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import team.lookingglass.elsewhere.Elsewhere;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Troll extends Monster {
    private static final Identifier ENRAGE_SPEED_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "enrage_speed");
    private static final Identifier ENRAGE_DAMAGE_ID = Identifier.fromNamespaceAndPath(Elsewhere.MODID, "enrage_damage");
    private static final double ENRAGE_HEALTH_FRACTION = 0.5;
    private static final EntityDataAccessor<Boolean> DATA_PROVOKED = SynchedEntityData.defineId(Troll.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> DATA_STOLEN_ITEM = SynchedEntityData.defineId(Troll.class, EntityDataSerializers.ITEM_STACK);

    private static final EntityDataAccessor<Boolean> DATA_ANGRY =
            SynchedEntityData.defineId(Troll.class, EntityDataSerializers.BOOLEAN);

    public Troll(final EntityType<? extends Troll> type, final Level level) {
        super(type, level);
    }

    protected void defineSynchedData(final SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ANGRY, false);
        builder.define(DATA_PROVOKED, false);
        builder.define(DATA_STOLEN_ITEM, ItemStack.EMPTY);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new FleeWithLootGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(4, new StealItemGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true) {
            public boolean canUse() {
                return Troll.this.isProvoked() && super.canUse();
            }});
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    public boolean isAngry() {
        return this.entityData.get(DATA_ANGRY);
    }

    public boolean isProvoked() {
        return this.entityData.get(DATA_PROVOKED) || this.isAngry();
    }

    public ItemStack getStolenItem() {
        return this.entityData.get(DATA_STOLEN_ITEM);
    }

    public void setStolenItem(final ItemStack stack) {
        this.entityData.set(DATA_STOLEN_ITEM, stack);
    }

    public void tick() {
        super.tick();
        boolean shouldBeAngry = this.getHealth() <= this.getMaxHealth() * ENRAGE_HEALTH_FRACTION;
        if (shouldBeAngry != this.isAngry()) {
            this.setAngry(shouldBeAngry);
        }

        if (this.isAngry() && this.level() instanceof ServerLevel serverLevel && this.random.nextInt(10) == 0) {
            double angle = this.random.nextDouble() * Math.PI * 2.0;
            double radius = 0.4;
            double offsetX = Math.cos(angle) * radius;
            double offsetZ = Math.sin(angle) * radius;
            serverLevel.sendParticles(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    this.getX() + offsetX, this.getEyeY() + 0.5, this.getZ() + offsetZ,
                    1, 0.02, 0.02, 0.02, 0.01
            );
        }
    }

    @Override
    protected void actuallyHurt(final ServerLevel level, final DamageSource source, final float amount) {
        super.actuallyHurt(level, source, amount);
        if (!this.getStolenItem().isEmpty()) {
            this.spawnAtLocation(level, this.getStolenItem());
            this.setStolenItem(ItemStack.EMPTY);
        } else {
            this.entityData.set(DATA_PROVOKED, true);
        }
    }

    private void setAngry(final boolean angry) {
        this.entityData.set(DATA_ANGRY, angry);

        AttributeInstance speed = this.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance damage = this.getAttribute(Attributes.ATTACK_DAMAGE);
        if (angry) {
            if (speed != null) {
                speed.addOrUpdateTransientModifier(new AttributeModifier(ENRAGE_SPEED_ID, 0.4, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
            if (damage != null) {
                damage.addOrUpdateTransientModifier(new AttributeModifier(ENRAGE_DAMAGE_ID, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
        } else {
            if (speed != null) {
                speed.removeModifier(ENRAGE_SPEED_ID);
            }
            if (damage != null) {
                damage.removeModifier(ENRAGE_DAMAGE_ID);
            }
        }
    }

    private class StealItemGoal extends Goal {
        private static final double STEAL_RANGE_SQ = 2.5;
        private static final double APPROACH_RANGE_SQ = 8.0;
        private static final double WANDER_CONE_RADIANS = Math.PI * 0.6;
        private static final double WANDER_MIN_DIST = 3.0;
        private static final double WANDER_MAX_DIST = 6.0;

        private @Nullable Player target;

        public StealItemGoal(final Troll troll) {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            if (Troll.this.isProvoked() || !Troll.this.getStolenItem().isEmpty()) {
                return false;
            }
            this.target = Troll.this.level().getNearestPlayer(
                    Troll.this.getX(), Troll.this.getY(), Troll.this.getZ(), 12.0, true
            );
            return this.target != null;
        }

        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive() && !Troll.this.isProvoked() && Troll.this.getStolenItem().isEmpty();
        }

        public void tick() {
            if (this.target == null) return;

            double distSq = Troll.this.distanceToSqr(this.target);
            if (distSq <= STEAL_RANGE_SQ) {
                this.attemptSteal(this.target);
                this.target = null;
                return;
            }

            if (distSq <= APPROACH_RANGE_SQ) {
                Troll.this.getNavigation().moveTo(this.target, 1.4);
                return;
            }

            if (!Troll.this.getNavigation().isInProgress()) {
                double angleToPlayer = Math.atan2(
                        this.target.getZ() - Troll.this.getZ(),
                        this.target.getX() - Troll.this.getX()
                );
                double angleOffset = (Troll.this.random.nextDouble() - 0.5) * WANDER_CONE_RADIANS;
                double angle = angleToPlayer + angleOffset;
                double distance = WANDER_MIN_DIST + Troll.this.random.nextDouble() * (WANDER_MAX_DIST - WANDER_MIN_DIST);

                double waypointX = Troll.this.getX() + Math.cos(angle) * distance;
                double waypointZ = Troll.this.getZ() + Math.sin(angle) * distance;
                Troll.this.getNavigation().moveTo(waypointX, Troll.this.getY(), waypointZ, 1.0);
            }
        }

        private void attemptSteal(final Player player) {
            Inventory inventory = player.getInventory();
            List<Integer> candidates = new ArrayList<>();
            for (int slot = 0; slot < 9; slot++) {
                if (!inventory.getItem(slot).isEmpty()) {
                    candidates.add(slot);
                }
            }
            if (candidates.isEmpty()) return;

            int slot = candidates.get(Troll.this.random.nextInt(candidates.size()));
            ItemStack stolen = inventory.getItem(slot).copy();
            inventory.setItem(slot, ItemStack.EMPTY);
            Troll.this.setStolenItem(stolen);
        }
    }

    private class FleeWithLootGoal extends Goal {
        private @Nullable Player fleeingFrom;

        public FleeWithLootGoal(final Troll troll) {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            if (Troll.this.getStolenItem().isEmpty()) {
                return false;
            }
            this.fleeingFrom = Troll.this.level().getNearestPlayer(Troll.this.getX(), Troll.this.getY(), Troll.this.getZ(), 12.0, true);
            return this.fleeingFrom != null;
        }

        public boolean canContinueToUse() {
            return !Troll.this.getStolenItem().isEmpty();
        }

        public void tick() {
            if (this.fleeingFrom == null || !Troll.this.getNavigation().isInProgress()) {
                Player nearest = Troll.this.level().getNearestPlayer(
                        Troll.this.getX(), Troll.this.getY(), Troll.this.getZ(), 16.0, true
                );
                if (nearest != null) {
                    double dx = Troll.this.getX() - nearest.getX();
                    double dz = Troll.this.getZ() - nearest.getZ();
                    double fleeX = Troll.this.getX() + dx * 4.0;
                    double fleeZ = Troll.this.getZ() + dz * 4.0;
                    Troll.this.getNavigation().moveTo(fleeX, Troll.this.getY(), fleeZ, 1.5);
                }
            }
        }
    }
}