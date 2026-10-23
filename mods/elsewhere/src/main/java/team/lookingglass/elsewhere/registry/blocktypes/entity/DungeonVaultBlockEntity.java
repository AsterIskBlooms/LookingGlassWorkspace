package team.lookingglass.elsewhere.registry.blocktypes.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultClientData;
import net.minecraft.world.level.block.entity.vault.VaultConfig;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.minecraft.world.level.block.entity.vault.VaultSharedData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jspecify.annotations.Nullable;
import team.lookingglass.elsewhere.Elsewhere;
import team.lookingglass.elsewhere.registry.EBlockEntityTypes;
import team.lookingglass.elsewhere.registry.EItems;

public class DungeonVaultBlockEntity extends BlockEntity {
    public static final ResourceKey<LootTable> REWARD_LOOT_KEY = ResourceKey.create(Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID,"vault/dungeon/reward"));
    public static final ResourceKey<LootTable> OMINOUS_REWARD_LOOT_KEY = ResourceKey.create(Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(Elsewhere.MODID,"vault/dungeon/reward_ominous"));    private final VaultServerData serverData = new VaultServerData();
    private final VaultSharedData sharedData = new VaultSharedData();
    private final VaultClientData clientData = new VaultClientData();
    private VaultConfig config;

    public DungeonVaultBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        super(EBlockEntityTypes.DUNGEON_VAULT, worldPosition, blockState);
        this.config = VaultConfig.DEFAULT;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(final HolderLookup.Provider registries) {
        return Util.make(new CompoundTag(), (tag) -> tag.store("shared_data", VaultSharedData.CODEC, registries.createSerializationContext(NbtOps.INSTANCE), this.sharedData));
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        super.saveAdditional(output);
        output.store("config", VaultConfig.CODEC, this.config);
        output.store("shared_data", VaultSharedData.CODEC, this.sharedData);
        output.store("server_data", VaultServerData.CODEC, this.serverData);
    }

    @Override
    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        input.read("server_data", VaultServerData.CODEC).ifPresent(this.serverData::set);
        this.config = input.read("config", VaultConfig.CODEC).orElse(VaultConfig.DEFAULT);
        input.read("shared_data", VaultSharedData.CODEC).ifPresent(this.sharedData::set);
    }

    public @Nullable VaultServerData getServerData() {
        return this.level != null && !this.level.isClientSide() ? this.serverData : null;
    }

    public VaultSharedData getSharedData() {
        return this.sharedData;
    }

    public VaultClientData getClientData() {
        return this.clientData;
    }

    public VaultConfig getConfig() {
        boolean ominous = this.getBlockState().getValue(VaultBlock.OMINOUS);
        ResourceKey<LootTable> lootTable = ominous ? OMINOUS_REWARD_LOOT_KEY : REWARD_LOOT_KEY;
        ItemStack key = new ItemStack(ominous ? EItems.OMINOUS_DUNGEON_KEY : EItems.DUNGEON_KEY);
        return new VaultConfig(lootTable, this.config.activationRange(), this.config.deactivationRange(), key, this.config.overrideLootTableToDisplay());
    }

    public void setConfig(final VaultConfig config) {
        this.config = config;
    }
}