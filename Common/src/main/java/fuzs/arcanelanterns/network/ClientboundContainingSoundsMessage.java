package fuzs.arcanelanterns.network;

import fuzs.puzzleslib.api.network.v3.ClientMessageListener;
import fuzs.puzzleslib.api.network.v3.ClientboundMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public record ClientboundContainingSoundsMessage(
        BlockPos pos) implements ClientboundMessage<ClientboundContainingSoundsMessage> {

    @Override
    public ClientMessageListener<ClientboundContainingSoundsMessage> getHandler() {
        return new ClientMessageListener<>() {

            @Override
            public void handle(ClientboundContainingSoundsMessage message, Minecraft client, ClientPacketListener handler, LocalPlayer player, ClientLevel level) {
                level.playLocalSound(message.pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS, 1, 0.6F, true);
            }
        };
    }
}
