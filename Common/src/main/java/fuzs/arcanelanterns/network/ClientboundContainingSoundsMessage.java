package fuzs.arcanelanterns.network;

import fuzs.puzzleslib.api.network.v4.message.MessageListener;
import fuzs.puzzleslib.api.network.v4.message.play.ClientboundPlayMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public record ClientboundContainingSoundsMessage(BlockPos blockPos) implements ClientboundPlayMessage {
    public static final StreamCodec<ByteBuf, ClientboundContainingSoundsMessage> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ClientboundContainingSoundsMessage::blockPos,
            ClientboundContainingSoundsMessage::new);

    @Override
    public MessageListener<Context> getListener() {
        return new MessageListener<Context>() {
            @Override
            public void accept(Context context) {
                context.level()
                        .playLocalSound(ClientboundContainingSoundsMessage.this.blockPos,
                                SoundEvents.CHAIN_BREAK,
                                SoundSource.BLOCKS,
                                1,
                                0.6F,
                                true);
            }
        };
    }
}
