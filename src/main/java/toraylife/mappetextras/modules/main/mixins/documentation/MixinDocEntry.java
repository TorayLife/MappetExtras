package toraylife.mappetextras.modules.main.mixins.documentation;

import mchorse.mappet.client.gui.scripts.utils.documentation.DocEntry;
import mchorse.mappet.client.gui.utils.text.GuiText;
import mchorse.mclib.client.gui.framework.elements.GuiScrollElement;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import toraylife.mappetextras.modules.main.documentation.ClassLinkManager;


@Mixin(value = DocEntry.class, remap = false)
public abstract class MixinDocEntry {


    @Inject(
            method = "process",
            at = @At(value = "INVOKE",
                    target = "Lmchorse/mclib/client/gui/framework/elements/GuiScrollElement;add(Lmchorse/mclib/client/gui/framework/elements/IGuiElement;)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER),
            remap = false,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void addDocs(String doc, Minecraft mc, GuiScrollElement target, CallbackInfo ci, String[] splits, boolean parsing, String code, String[] var6, int var7, int var8, String line, boolean p, GuiText text) {
        ClassLinkManager linkManager = new ClassLinkManager(mc);

        linkManager.addLinks(text, linkManager.getEntries(linkManager.parseLinks(line)));
    }
}
