package team.lookingglass.monstrous.goal.spider;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.level.Level;
import team.lookingglass.monstrous.render.Crawler;

public class SurfaceNavigation extends WallClimberNavigation {
    public SurfaceNavigation(Mob mob, Level level) {
        super(mob, level);
    }

    @Override
    public void tick() {
        if (this.mob instanceof Crawler clinger && clinger.monstrous$isDriven()) return;
        super.tick();
    }
}