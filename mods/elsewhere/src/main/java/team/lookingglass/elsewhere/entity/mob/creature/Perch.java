package team.lookingglass.elsewhere.entity.mob.creature;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.fish.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lookingglass.elsewhere.registry.EItems;
import team.lookingglass.elsewhere.registry.sound.ESounds;

public class Perch extends AbstractFish {
    public Perch(EntityType<? extends AbstractFish> type, Level level) {
        super(type, level);
    }

    @Override
    protected SoundEvent getFlopSound() { return ESounds.PERCH_FLOP; }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return ESounds.PERCH_HURT; }

    @Override
    protected SoundEvent getDeathSound() { return ESounds.PERCH_DEATH; }

    @Override
    public ItemStack getBucketItemStack() { return new ItemStack(EItems.PERCH_BUCKET); }
}