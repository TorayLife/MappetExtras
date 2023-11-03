package toraylife.mappetextras.modules.main.mixins.documentation;

import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.Docs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = GuiDocumentationOverlayPanel.class, remap = false)
public interface MixinGuiDocumentationOverlayPanelAccessor {
    @Accessor(value = "docs", remap = false)
    static Docs getFieldDocs() {
        throw new AssertionError();
    }
}
