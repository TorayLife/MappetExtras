package toraylife.mappetextras.modules.main.documentation.mixins;

import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

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


    @Inject(
            method = "parseDocs",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 5
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void parseDocs(CallbackInfo ci, boolean dev, DocList topPackage, DocList scripting, DocList entities, DocList nbt, DocList items, DocList blocks, DocList ui, DocList triggers, DocList conditions, boolean useNewStructure) {
        if (useNewStructure) {
            triggers.name = "/ Triggers";
            triggers.doc = MixinGuiDocumentationOverlayPanelAccessor.getFieldDocs().getPackage("toraylife.mappetextras.modules.scripting.scripts.user.triggers").doc;
            triggers.parent = scripting;
            triggers.source = "MappetExtras";
            scripting.entries.add(triggers);

            conditions.name = "/ Conditions";
            conditions.doc = MixinGuiDocumentationOverlayPanelAccessor.getFieldDocs().getPackage("toraylife.mappetextras.modules.scripting.scripts.user.conditions").doc;
            conditions.parent = scripting;
            conditions.source = "MappetExtras";
            scripting.entries.add(conditions);
        }
    }
}
