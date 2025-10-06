package fuzs.arcanelanterns.network;

import fuzs.puzzleslib.api.network.v4.message.MessageListener;
import fuzs.puzzleslib.api.network.v4.message.play.ClientboundPlayMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public record ClientboundWailingSoundsMessage(BlockPos blockPos,
                                              boolean isScreaming) implements ClientboundPlayMessage {
    public static final StreamCodec<ByteBuf, ClientboundWailingSoundsMessage> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ClientboundWailingSoundsMessage::blockPos,
            ByteBufCodecs.BOOL,
            ClientboundWailingSoundsMessage::isScreaming,
            ClientboundWailingSoundsMessage::new);

    @Override
    public MessageListener<Context> getListener() {
        return new MessageListener<Context>() {
            @Override
            public void accept(Context context) {
                if (!ClientboundWailingSoundsMessage.this.isScreaming) {
                    context.level()
                            .playLocalSound(ClientboundWailingSoundsMessage.this.blockPos,
                                    SoundEvents.GHAST_AMBIENT,
                                    SoundSource.BLOCKS,
                                    20,
                                    1,
                                    true);
                } else {
                    context.level()
                            .playLocalSound(ClientboundWailingSoundsMessage.this.blockPos,
                                    SoundEvents.ENDER_DRAGON_GROWL,
                                    SoundSource.BLOCKS,
                                    60,
                                    1.3f,
                                    true);
                }
            }
        };
    }
}
