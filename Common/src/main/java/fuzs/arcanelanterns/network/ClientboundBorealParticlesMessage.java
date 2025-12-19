package fuzs.arcanelanterns.network;

import fuzs.puzzleslib.api.network.v4.message.MessageListener;
import fuzs.puzzleslib.api.network.v4.message.play.ClientboundPlayMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;

public record ClientboundBorealParticlesMessage(BlockPos blockPos,
                                                BlockPos entityPos) implements ClientboundPlayMessage {
    public static final StreamCodec<ByteBuf, ClientboundBorealParticlesMessage> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ClientboundBorealParticlesMessage::blockPos,
            BlockPos.STREAM_CODEC,
            ClientboundBorealParticlesMessage::entityPos,
            ClientboundBorealParticlesMessage::new);

    @Override
    public MessageListener<Context> getListener() {
        return new MessageListener<Context>() {
            @Override
            public void accept(Context context) {
                BlockPos blockPos = ClientboundBorealParticlesMessage.this.blockPos;
                BlockPos entityPos = ClientboundBorealParticlesMessage.this.entityPos;
                RandomSource randomSource = context.level().random;
                for (int i = 0; i < 2; ++i) {
                    double posX = blockPos.getX() + randomSource.nextDouble();
                    double posY = blockPos.getY() + randomSource.nextDouble();
                    double posZ = blockPos.getZ() + randomSource.nextDouble();
                    double speedX = (entityPos.getX() - blockPos.getX()) / 10.0;
                    double speedY = (entityPos.getY() + randomSource.nextDouble() - blockPos.getY()) / 10.0;
                    double speedZ = (entityPos.getZ() - blockPos.getZ()) / 10.0;
                    context.level().addParticle(ParticleTypes.END_ROD, posX, posY, posZ, speedX, speedY, speedZ);
                }
            }
        };
    }
}
