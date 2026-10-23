package team.lookingglass.elsewhere.mixin.entity.possession;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import team.lookingglass.elsewhere.entity.renderer.states.PossessedRenderState;

@Mixin(LivingEntityRenderState.class)
public abstract class PossessionRenderStateMixin implements PossessedRenderState {
    @Unique
    private boolean elsewhere$possessed;

    @Override
    public boolean elsewhere$isPossessed() {
        return this.elsewhere$possessed;
    }

    @Override
    public void elsewhere$setPossessed(boolean possessed) {
        this.elsewhere$possessed = possessed;
    }
}