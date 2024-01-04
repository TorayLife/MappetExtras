package toraylife.mappetextras.modules.main.mixins.late.documentation;

import mchorse.mappet.Mappet;
import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocClass;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Mixin(value = GuiDocumentationOverlayPanel.class, remap = false)
public class MixinGuiDocumentationOverlayPanel {


    @Inject(
            method = "parseDocs",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mappet/client/gui/scripts/GuiDocumentationOverlayPanel;mixinsHook()V",
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void parseDocs(CallbackInfo ci, boolean dev, Map docLists, DocList topPackage, DocList scripting, DocList entities, DocList nbt, DocList items, DocList blocks, DocList ui) {
        DocList triggers = new DocList();
        DocList conditions = new DocList();

        if (Mappet.scriptDocsNewStructure.get()) {
            triggers.name = "/ Triggers";
            triggers.doc = GuiDocumentationOverlayPanel.docs.getPackage("toraylife.mappetextras.modules.scripting.scripts.user.triggers").doc;
            triggers.parent = scripting;
            triggers.source = "MappetExtras";
            scripting.entries.add(triggers);

            conditions.name = "/ Conditions";
            conditions.doc = GuiDocumentationOverlayPanel.docs.getPackage("toraylife.mappetextras.modules.scripting.scripts.user.conditions").doc;
            conditions.parent = scripting;
            conditions.source = "MappetExtras";
            scripting.entries.add(conditions);
        }
        docLists.put("triggers", triggers);
        docLists.put("conditions", conditions);
    }

    @Inject(
            method = "parseDocs",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mappet/client/gui/scripts/GuiDocumentationOverlayPanel;mixinsHook()V",
                    ordinal = 1
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void mixinsHook(CallbackInfo ci, boolean dev, Map<String, DocList> docLists, DocList topPackage, DocList scripting, DocList entities, DocList nbt, DocList items, DocList blocks, DocList ui, boolean useNewStructure, List extraPackages, List extraDocLists, Iterator var12, DocClass docClass, List<Callable<Boolean>> functions, boolean added) {
        functions.add(() -> GuiDocumentationOverlayPanel.addWithNewStructure(input -> input.name.contains("triggers"), docClass, docLists.get("triggers")));
        functions.add(() -> GuiDocumentationOverlayPanel.addWithNewStructure(input -> input.name.contains("conditions"), docClass, docLists.get("conditions")));
    }
}
