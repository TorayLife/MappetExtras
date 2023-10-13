package toraylife.mappetextras.modules.main.documentation.mixins;

import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = GuiDocumentationOverlayPanel.class, remap = false)
public class MixinGuiDocumentationOverlayPanel {


    @ModifyArg(
            method = "<init>(Lnet/minecraft/client/Minecraft;Lmchorse/mappet/client/gui/scripts/utils/documentation/DocEntry;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mclib/client/gui/utils/resizers/Flex;w(I)Lmchorse/mclib/client/gui/utils/resizers/Flex;",
                    ordinal = 0
            )
    )
    public int wList(int value) {
        return 240;
    }

    @ModifyArg(
            method = "<init>(Lnet/minecraft/client/Minecraft;Lmchorse/mappet/client/gui/scripts/utils/documentation/DocEntry;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mclib/client/gui/utils/resizers/Flex;x(I)Lmchorse/mclib/client/gui/utils/resizers/Flex;",
                    ordinal = 0
            )
    )
    public int xDocs(int value) {
        return 240;
    }

    @ModifyArg(
            method = "<init>(Lnet/minecraft/client/Minecraft;Lmchorse/mappet/client/gui/scripts/utils/documentation/DocEntry;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mclib/client/gui/utils/resizers/Flex;w(FI)Lmchorse/mclib/client/gui/utils/resizers/Flex;",
                    ordinal = 0
            ),
            index = 1
    )
    public int wDocs(int value) {
        return -240;
    }
}
