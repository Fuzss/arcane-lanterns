package fuzs.arcanelanterns.network;

import fuzs.puzzleslib.api.network.v4.message.MessageListener;
import fuzs.puzzleslib.api.network.v4.message.play.ClientboundPlayMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public record ClientboundCraftLanternParticlesMessage(BlockPos blockPos) implements ClientboundPlayMessage {
    public static final StreamCodec<ByteBuf, ClientboundCraftLanternParticlesMessage> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ClientboundCraftLanternParticlesMessage::blockPos,
            ClientboundCraftLanternParticlesMessage::new);

    @Override
    public MessageListener<Context> getListener() {
        return new MessageListener<Context>() {
            @Override
            public void accept(Context context) {
                BlockPos blockPos = ClientboundCraftLanternParticlesMessage.this.blockPos;
                RandomSource randomSource = context.level().random;
                context.level()
                        .playLocalSound(blockPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1, true);
                for (int i = 0; i < 20; i++) {
                    context.level()
                            .addParticle(ParticleTypes.END_ROD,
                                    blockPos.getX() + 0.5,
                                    blockPos.getY() + 1.25,
                                    blockPos.getZ() + 0.5,
                                    0.5 - randomSource.nextDouble(),
                                    0.5 - randomSource.nextDouble(),
                                    0.5 - randomSource.nextDouble());
                }
            }
        };
    }
}
