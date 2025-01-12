package fuzs.arcanelanterns.network;

import fuzs.puzzleslib.api.network.v3.ClientMessageListener;
import fuzs.puzzleslib.api.network.v3.ClientboundMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public record ClientboundCraftLanternParticlesMessage(BlockPos pos) implements ClientboundMessage<ClientboundCraftLanternParticlesMessage> {

    @Override
    public ClientMessageListener<ClientboundCraftLanternParticlesMessage> getHandler() {
        return new ClientMessageListener<>() {

            @Override
            public void handle(ClientboundCraftLanternParticlesMessage message, Minecraft client, ClientPacketListener handler, LocalPlayer player, ClientLevel level) {
                level.playLocalSound(message.pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1, true);
                for (int i = 0; i < 20; i++) {
                    level.addParticle(ParticleTypes.END_ROD, message.pos.getX() + 0.5, message.pos.getY() + 1.25, message.pos.getZ() + 0.5, 0.5 - level.random.nextDouble(), 0.5 - level.random.nextDouble(), 0.5 - level.random.nextDouble());
                }
            }
        };
    }
}
