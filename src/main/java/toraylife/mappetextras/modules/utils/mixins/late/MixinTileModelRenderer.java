package toraylife.mappetextras.modules.utils.mixins.late;

import mchorse.blockbuster.client.render.tileentity.TileEntityModelRenderer;
import mchorse.blockbuster.common.tileentity.TileEntityModel;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileEntityModelRenderer.class, remap = false)
public class MixinTileModelRenderer {

	@Inject(
			method = "render(Lmchorse/blockbuster/common/tileentity/TileEntityModel;DDDFIF)V",
			at = @At(
					value = "INVOKE",
					target = "Lorg/lwjgl/opengl/GL11;glGetInteger(I)I"
			),
			remap = false,
			cancellable = true
	)
	private void renderDebug(TileEntityModel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha, CallbackInfo ci) {
		if (!Minecraft.getMinecraft().player.isCreative()) {
			ci.cancel();
		}
	}
}
