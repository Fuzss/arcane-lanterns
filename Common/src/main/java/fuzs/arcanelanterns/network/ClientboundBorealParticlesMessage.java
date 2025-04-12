package fuzs.arcanelanterns.network;

import fuzs.puzzleslib.api.network.v3.ClientMessageListener;
import fuzs.puzzleslib.api.network.v3.ClientboundMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;

public record ClientboundBorealParticlesMessage(BlockPos blockPos,
                                                BlockPos entityPos) implements ClientboundMessage<ClientboundBorealParticlesMessage> {

    @Override
    public ClientMessageListener<ClientboundBorealParticlesMessage> getHandler() {
        return new ClientMessageListener<>() {

            @Override
            public void handle(ClientboundBorealParticlesMessage message, Minecraft client, ClientPacketListener handler, LocalPlayer player, ClientLevel level) {
                for (int i = 0; i < 2; ++i) {
                    double posX = message.blockPos.getX() + level.random.nextDouble();
                    double posY = message.blockPos.getY() + level.random.nextDouble();
                    double posZ = message.blockPos.getZ() + level.random.nextDouble();
                    double speedX = (double) (message.entityPos.getX() - message.blockPos.getX()) / 10;
                    double speedY = (message.entityPos.getY() + level.random.nextDouble() - message.blockPos.getY()) / 10;
                    double speedZ = (double) (message.entityPos.getZ() - message.blockPos.getZ()) / 10;
                    client.level.addParticle(ParticleTypes.END_ROD, posX, posY, posZ, speedX, speedY, speedZ);
                }
            }
        };
    }
}
