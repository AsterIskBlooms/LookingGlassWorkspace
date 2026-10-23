package team.lookingglass.elsewhere.mixin.entity.possession;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lookingglass.elsewhere.entity.util.PossessionAccess;

@Mixin(LivingEntity.class)
public abstract class PossessionMixin extends Entity implements PossessionAccess {
    @Unique private static final EntityDataAccessor<Boolean> ELSEWHERE$POSSESSED = SynchedEntityData.defineId(PossessionMixin.class, EntityDataSerializers.BOOLEAN);

    protected PossessionMixin(final EntityType<?> type, final Level level) {
        super(type, level);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void elsewhere$defineSynchedData(final SynchedEntityData.Builder builder, final CallbackInfo ci) {
        builder.define(ELSEWHERE$POSSESSED, false);
    }

    @Override
    public boolean elsewhere$isPossessed() {
        return this.getEntityData().get(ELSEWHERE$POSSESSED);
    }

    @Override
    public void elsewhere$setPossessed(final boolean possessed) {
        this.getEntityData().set(ELSEWHERE$POSSESSED, possessed);
    }
}
