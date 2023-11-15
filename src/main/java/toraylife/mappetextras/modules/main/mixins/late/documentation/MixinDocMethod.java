package toraylife.mappetextras.modules.main.mixins.late.documentation;

import mchorse.mappet.client.gui.scripts.utils.documentation.DocMethod;
import mchorse.mappet.client.gui.utils.text.GuiText;
import mchorse.mclib.client.gui.framework.elements.IGuiElement;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import toraylife.mappetextras.modules.main.documentation.ClassLinkManager;

@Mixin(value = DocMethod.class, remap = false)
public class MixinDocMethod {

    @ModifyArg(
            method = "fillIn",
            at = @At(
                    value = "INVOKE",
                    target = "Lmchorse/mclib/client/gui/framework/elements/GuiScrollElement;add(Lmchorse/mclib/client/gui/framework/elements/IGuiElement;)V"
            ), remap = false
    )
    private IGuiElement fillIn(IGuiElement par1) {

        ClassLinkManager linkManager = new ClassLinkManager(Minecraft.getMinecraft());

        String text = ((GuiText) par1).getText().get();

        linkManager.addLinks((GuiText) par1, linkManager.getEntries(linkManager.parseLinks(text)));

//        Set<String> classLinks = ClassLinkManager.parse(text);
//
//        if (!classLinks.isEmpty()) {
//            ClassLinkManager.addLinks(Minecraft.getMinecraft(), ((GuiText) par1), ClassLinkManager.getEntries(classLinks));
//        }

        return par1;
    }
}
