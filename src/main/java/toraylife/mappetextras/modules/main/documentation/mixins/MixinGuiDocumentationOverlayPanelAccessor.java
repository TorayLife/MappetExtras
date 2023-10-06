package toraylife.mappetextras.modules.main.documentation.mixins;

import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.Docs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = GuiDocumentationOverlayPanel.class)
public interface MixinGuiDocumentationOverlayPanelAccessor {
    @Accessor("docs")
    static Docs getFieldDocs() {
        throw new AssertionError();
    }
}
