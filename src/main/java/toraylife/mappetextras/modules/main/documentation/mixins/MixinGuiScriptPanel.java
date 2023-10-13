package toraylife.mappetextras.modules.main.documentation.mixins;

import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = GuiScriptPanel.class, remap = false)
public class MixinGuiScriptPanel {
    @ModifyArg(
            method = "openDocumentation",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mappet/client/gui/utils/overlays/GuiOverlay;addOverlay(Lmchorse/mclib/client/gui/framework/elements/utils/GuiContext;Lmchorse/mappet/client/gui/utils/overlays/GuiOverlayPanel;FF)V"
            ),
            index = 2
    )
    public float wOpenDocumentation(float value) {
        return 0.9F;
    }

    @ModifyArg(
            method = "searchDocumentation",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mappet/client/gui/utils/overlays/GuiOverlay;addOverlay(Lmchorse/mclib/client/gui/framework/elements/utils/GuiContext;Lmchorse/mappet/client/gui/utils/overlays/GuiOverlayPanel;FF)V"
            ),
            index = 2
    )
    private static float wSearchDocumentation(float value) {
        return 0.9F;
    }
}
