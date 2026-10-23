package team.lookingglass.elsewhere.registry.blocktypes.entity;

import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.DevUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.trialspawner.PlayerDetector;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerConfig;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.EBlockEntityTypes;

public class DungeonSpawnerBlockEntity extends BlockEntity implements TrialSpawner.StateAccessor, Spawner {
    public static final ResourceKey<LootTable> REWARD_LOOT_KEY = ResourceKey.create(Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID,"spawner/dungeon/reward"));
    public static final ResourceKey<LootTable> OMINOUS_REWARD_LOOT_KEY = ResourceKey.create(Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID,"spawner/dungeon/reward_ominous"));

    private final TrialSpawner trialSpawner = this.createDefaultSpawner();

    public DungeonSpawnerBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        super(EBlockEntityTypes.DUNGEON_SPAWNER, worldPosition, blockState);
    }

    private TrialSpawner createDefaultSpawner() {
        PlayerDetector playerDetector = SharedConstants.DEBUG_TRIAL_SPAWNER_DETECTS_SHEEP_AS_PLAYERS ? PlayerDetector.SHEEP : PlayerDetector.NO_CREATIVE_PLAYERS;
        PlayerDetector.EntitySelector entitySelector = PlayerDetector.EntitySelector.SELECT_FROM_LEVEL;
        return new TrialSpawner(overlayLoot(TrialSpawner.FullConfig.DEFAULT), this, playerDetector, entitySelector);
    }

    private static TrialSpawner.FullConfig overlayLoot(final TrialSpawner.FullConfig base) {
        return new TrialSpawner.FullConfig(
                Holder.direct(overlayLoot(base.normal().value(), REWARD_LOOT_KEY)),
                Holder.direct(overlayLoot(base.ominous().value(), OMINOUS_REWARD_LOOT_KEY)),
                base.targetCooldownLength(),
                base.requiredPlayerRange());
    }

    private static TrialSpawnerConfig overlayLoot(final TrialSpawnerConfig base, final ResourceKey<LootTable> lootKey) {
        return new TrialSpawnerConfig(
                base.spawnRange(),
                base.totalMobs(),
                base.simultaneousMobs(),
                base.totalMobsAddedPerPlayer(),
                base.simultaneousMobsAddedPerPlayer(),
                base.ticksBetweenSpawn(),
                base.spawnPotentialsDefinition(),
                WeightedList.of(lootKey),
                base.itemsToDropWhenOminous());
    }

    @Override
    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        this.trialSpawner.load(input);
        this.trialSpawner.config = overlayLoot(this.trialSpawner.config);
        if (this.level != null) {
            this.markUpdated();
        }
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        super.saveAdditional(output);
        this.trialSpawner.store(output);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(final HolderLookup.Provider registries) {
        return this.trialSpawner.getStateData().getUpdateTag(this.getBlockState().getValue(BlockStateProperties.TRIAL_SPAWNER_STATE));
    }

    @Override
    public void setEntityId(final EntityType<?> type, final RandomSource random) {
        if (this.level == null) {
            DevUtil.logAndPauseIfInIde("Expected non-null level");
        } else {
            this.trialSpawner.overrideEntityToSpawn(type, this.level);
            this.setChanged();
        }
    }

    @Override
    public void setEntityData(final TypedEntityData<EntityType<?>> entityData, final RandomSource random) {
        if (this.level == null) {
            DevUtil.logAndPauseIfInIde("Expected non-null level");
        } else {
            this.trialSpawner.overrideEntityToSpawn(entityData, this.level);
            this.setChanged();
        }
    }

    public TrialSpawner getTrialSpawner() {
        return this.trialSpawner;
    }

    @Override
    public TrialSpawnerState getState() {
        return !this.getBlockState().hasProperty(BlockStateProperties.TRIAL_SPAWNER_STATE) ? TrialSpawnerState.INACTIVE : this.getBlockState().getValue(BlockStateProperties.TRIAL_SPAWNER_STATE);
    }

    @Override
    public void setState(final Level level, final TrialSpawnerState state) {
        this.setChanged();
        level.setBlockAndUpdate(this.worldPosition, this.getBlockState().setValue(BlockStateProperties.TRIAL_SPAWNER_STATE, state));
    }

    @Override
    public void markUpdated() {
        this.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }
}