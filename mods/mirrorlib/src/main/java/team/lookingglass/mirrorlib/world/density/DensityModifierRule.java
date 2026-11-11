package team.lookingglass.mirrorlib.world.density;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction;
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions;
import net.minecraft.world.level.levelgen.densityfunction.DfRewriteRule;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

final class DensityModifierRule implements DfRewriteRule {
    private final Map<ResourceKey<DensityFunction>, List<DensityModifiers.Entry>> modifiers;
    private final HolderLookup.Provider registries;
    private final Map<DensityFunction, DensityFunction> nodes = new IdentityHashMap<>();
    private final Map<Holder<DensityFunction>, DensityFunction> holders = new IdentityHashMap<>();
    private boolean modified;

    DensityModifierRule(Map<ResourceKey<DensityFunction>, List<DensityModifiers.Entry>> modifiers, HolderLookup.Provider registries) {
        this.modifiers = modifiers;
        this.registries = registries;
    }

    boolean modified() {
        return this.modified;
    }

    @Override
    public DensityFunction rewrite(final DensityFunction function) {
        if (function instanceof DensityFunctions.HolderHolder holderHolder) {
            return this.rewriteHolder(holderHolder);
        }

        DensityFunction cached = this.nodes.get(function);
        if (cached != null) return cached;

        DensityFunction rewritten = function.rewriteChildren(this);
        this.nodes.put(function, rewritten);
        return rewritten;
    }

    private DensityFunction rewriteHolder(final DensityFunctions.HolderHolder function) {
        Holder<DensityFunction> holder = function.function();
        DensityFunction cached = this.holders.get(holder);
        if (cached != null) return cached;

        DensityFunction current = function.rewriteChildren(this);
        List<DensityModifiers.Entry> entries = holder.unwrapKey().map(this.modifiers::get).orElse(null);
        if (entries != null) {
            for (DensityModifiers.Entry entry : entries) {
                current = entry.modifier().modify(current, this.registries);
            }
            this.modified = true;
        }

        this.holders.put(holder, current);
        return current;
    }
}
