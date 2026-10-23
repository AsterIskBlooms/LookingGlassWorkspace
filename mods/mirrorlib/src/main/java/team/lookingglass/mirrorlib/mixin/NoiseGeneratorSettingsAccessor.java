package team.lookingglass.mirrorlib.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = NoiseGeneratorSettings.class, remap = true)
public interface NoiseGeneratorSettingsAccessor {

    @Accessor("materialRule")
    Holder<MaterialRule> getMaterialRule();

    @Mutable
    @Accessor("materialRule")
    void setMaterialRule(Holder<MaterialRule> materialRule);
}