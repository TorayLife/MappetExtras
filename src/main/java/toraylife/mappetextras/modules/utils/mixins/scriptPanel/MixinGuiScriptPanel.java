package toraylife.mappetextras.modules.utils.mixins.scriptPanel;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import mchorse.mappet.client.gui.scripts.GuiRepl;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.utils.Direction;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.utils.MPEIcons;
import toraylife.mappetextras.modules.utils.UtilsModule;
import toraylife.mappetextras.modules.utils.client.gui.codeEditor.SearchPanel;

@Mixin(value = GuiScriptPanel.class, remap = false)
public abstract class MixinGuiScriptPanel extends GuiMappetDashboardPanel {

    @Shadow
    public GuiTextEditor code;

    @Shadow
    public GuiRepl repl;
    public GuiIconElement searchIcon;
    public SearchPanel search;

    public MixinGuiScriptPanel(Minecraft mc, GuiMappetDashboard dashboard) {
        super(mc, dashboard);
    }

    @Inject(at = @At("TAIL"), method = "<init>", remap = false)
    public void inject(Minecraft mc, GuiMappetDashboard dashboard, CallbackInfo ci) {
        this.search = new SearchPanel(mc, this.code);
        float verticalShift = UtilsModule.getInstance().codeSearchOnTop.get() ? 0F : 1F;
        this.search.flex().relative(this.code).xy(1F, verticalShift).anchor(1F, verticalShift).wh(160, 80);
        this.search.setVisible(false);
        this.editor.add(this.search);

        this.code.keys().ignoreFocus().register(IKey.lang("mappetextras.todo"), Keyboard.KEY_F, this.search::toggleSearch)
                .category(GuiMappetDashboardPanel.KEYS_CATEGORY)
                .held(Keyboard.KEY_LCONTROL);

        this.searchIcon = new GuiIconElement(mc, MPEIcons.FOOD_PIPE, (b) -> {
            this.search.toggleSearch();
        });
        this.searchIcon.setVisible(this.data != null && this.allowed && this.code.isVisible());
        this.searchIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.search_and_replace"), Direction.LEFT);

        this.iconBar.add(this.searchIcon);
    }

    @Inject(method = "updateButtons", at = @At("TAIL"), remap = false)
    public void updateButtons(CallbackInfo ci) {
        if (this.searchIcon == null) {
            return;
        }
        this.searchIcon.setVisible(this.data != null && this.allowed && this.code.isVisible());
    }
}
