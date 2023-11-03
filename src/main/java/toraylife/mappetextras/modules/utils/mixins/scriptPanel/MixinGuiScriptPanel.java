package toraylife.mappetextras.modules.utils.mixins.scriptPanel;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.utils.client.gui.codeEditor.SearchPanel;

@Mixin(value = GuiScriptPanel.class, remap = false)
public abstract class MixinGuiScriptPanel extends GuiMappetDashboardPanel {

    @Shadow
    public GuiTextEditor code;


    public SearchPanel search;

    public MixinGuiScriptPanel(Minecraft mc, GuiMappetDashboard dashboard) {
        super(mc, dashboard);
    }

    @Inject(at = @At("TAIL"), method = "<init>", remap = false)
    public void inject(Minecraft mc, GuiMappetDashboard dashboard, CallbackInfo ci) {
        this.search = new SearchPanel(mc, this.code);
        this.search.flex().relative(this.code).xy(1F, 1F).anchor(1F, 1F).wh(200, 130);
        this.search.setVisible(false);
        this.editor.add(this.search);

        this.code.keys().ignoreFocus().register(IKey.lang("mappetextras.todo"), Keyboard.KEY_F, this.search::toggleSearch)
                .category(GuiMappetDashboardPanel.KEYS_CATEGORY)
                .held(Keyboard.KEY_LCONTROL);
    }


}
