package toraylife.mappetextras.modules.utils.mixins.late;

import mchorse.mappet.client.renders.tile.TileConditionModelRenderer;
import mchorse.mappet.tile.TileConditionModel;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileConditionModelRenderer.class, remap = false)
public class MixinTileConditionModelRenderer {

	@Inject(
			method = "renderDebug",
			at = @At(
					value = "HEAD"
			),
			remap = false,
			cancellable = true
	)
	private void renderDebug(TileConditionModel te, double x, double y, double z, CallbackInfo ci) {
		if (!Minecraft.getMinecraft().player.isCreative()) {
			ci.cancel();
		}
	}
}
