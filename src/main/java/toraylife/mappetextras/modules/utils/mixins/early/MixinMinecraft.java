package toraylife.mappetextras.modules.utils.mixins.early;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import toraylife.mappetextras.CommonProxy;

@Mixin(value = Minecraft.class)
public abstract class MixinMinecraft {
    @Inject(method = "displayGuiScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;onGuiClosed()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void onDisplayGuiScreen(GuiScreen guiScreenIn, CallbackInfo ci, GuiScreen old, GuiOpenEvent event){
        CommonProxy.eventHandler.onPlayerCloseGuiEvent(old);
    }
}
