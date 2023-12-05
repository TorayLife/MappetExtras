package toraylife.mappetextras.modules.utils.mixins.late.scriptPanel;

import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocEntry;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocMethod;
import mchorse.mappet.client.gui.utils.overlays.GuiOverlayPanel;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiDocumentationOverlayPanel.class, remap = false)
public abstract class MixinGuiDocumentationOverlayPanel extends GuiOverlayPanel {
    public GuiIconElement saveMethodName;

    @Shadow
    public GuiDocumentationOverlayPanel.GuiDocEntrySearchList list;

    public MixinGuiDocumentationOverlayPanel(Minecraft mc, IKey title) {
        super(mc, title);
    }

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/client/Minecraft;Lmchorse/mappet/client/gui/scripts/utils/documentation/DocEntry;)V", remap = false)
    public void inject(Minecraft mc, DocEntry entry, CallbackInfo ci){
        this.saveMethodName = new GuiIconElement(mc, Icons.DUPE, (b) -> {
            if(this.list.list.getCurrentFirst() != null){
                this.saveName(this.list.list.getCurrentFirst().getName().replaceAll("§.", ""));
            }
        });
        this.saveMethodName.tooltip(IKey.lang("mappetextras.utils.saveEntry.save")).flex().wh(16, 16);
        this.icons.addAfter(this.close, this.saveMethodName);
    }

    private void saveName(String methodName) {
        GuiScreen.setClipboardString(methodName);
    }
}
