package team.lookingglass.elsewhere.entity.renderer.states;

import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class VanguardRenderState extends ArmedEntityRenderState {
    public float walkPhase;
    public float walkWeight;
    public float blockWeight;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState blockStartAnimationState = new AnimationState();
    public final AnimationState blockAnimationState = new AnimationState();
    public final AnimationState singleSwingAnimationState = new AnimationState();
    public final AnimationState doubleSwingAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState stunAnimationState = new AnimationState();
}