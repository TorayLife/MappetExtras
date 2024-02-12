package toraylife.mappetextras.modules.utils.mixins.late;

import io.netty.buffer.ByteBuf;
import mchorse.mappet.api.utils.IContentType;
import mchorse.mappet.network.common.content.PacketContentBase;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.apache.commons.lang3.EnumUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.utils.ContentType;

@Mixin(value = PacketContentBase.class, remap = false)
public class MixinPacketContentBase {

	@Shadow
	public IContentType type;

	@Shadow
	public int requestId;

	@Inject(method = "fromBytes", at = @At("HEAD"), cancellable = true)
	private void fromBytes(ByteBuf buf, CallbackInfo ci) {
		buf.markReaderIndex();
		if ((this.type = EnumUtils.getEnum(ContentType.class, ByteBufUtils.readUTF8String(buf))) != null) {
			this.requestId = buf.readInt();
			ci.cancel();
			return;
		}
		buf.resetReaderIndex();
	}
}
