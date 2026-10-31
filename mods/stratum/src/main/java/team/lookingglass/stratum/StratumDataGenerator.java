package team.lookingglass.stratum;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import team.lookingglass.stratum.datagen.SRegistryProvider;
import team.lookingglass.stratum.worldgen.StratumDensityFunctions;
import team.lookingglass.stratum.worldgen.StratumNoises;

public class StratumDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(SRegistryProvider::new);


    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.NOISE, StratumNoises::bootstrap);
        registryBuilder.add(Registries.DENSITY_FUNCTION, StratumDensityFunctions::bootstrap);
    }
}
