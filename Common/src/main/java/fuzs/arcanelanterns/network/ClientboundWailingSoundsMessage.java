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

public record ClientboundWailingSoundsMessage(BlockPos pos, boolean scream) implements ClientboundMessage<ClientboundWailingSoundsMessage> {

    @Override
    public ClientMessageListener<ClientboundWailingSoundsMessage> getHandler() {
        return new ClientMessageListener<>() {

            @Override
            public void handle(ClientboundWailingSoundsMessage message, Minecraft client, ClientPacketListener handler, LocalPlayer player, ClientLevel level) {
                if (!message.scream) {
                    level.playLocalSound(message.pos, SoundEvents.GHAST_AMBIENT, SoundSource.BLOCKS, 20, 1, true);
                } else {
                    level.playLocalSound(message.pos, SoundEvents.ENDER_DRAGON_GROWL, SoundSource.BLOCKS, 60, 1.3f, true);
                }
            }
        };
    }
}
