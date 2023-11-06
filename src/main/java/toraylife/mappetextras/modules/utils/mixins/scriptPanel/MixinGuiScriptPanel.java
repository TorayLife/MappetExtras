package toraylife.mappetextras.modules.utils.mixins.scriptPanel;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mappet.client.gui.utils.text.undo.TextEditUndo;
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
import toraylife.mappetextras.modules.utils.client.Beautifier;
import toraylife.mappetextras.modules.utils.client.BeautifierOptions;
import toraylife.mappetextras.modules.utils.client.gui.codeEditor.GuiTextEditorSearchable;
import toraylife.mappetextras.modules.utils.client.gui.codeEditor.SearchPanel;

import javax.script.ScriptException;
@Mixin(value = GuiScriptPanel.class, remap = false)
public abstract class MixinGuiScriptPanel extends GuiMappetDashboardPanel {

    @Shadow
    public GuiTextEditor code;

    public GuiIconElement searchIcon;
    public GuiIconElement beautifierIcon;
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

        this.code.keys().ignoreFocus().register(IKey.lang("mappetextras.utils.codesearch.search_and_replace"), Keyboard.KEY_F, this.search::toggleSearch)
                .category(GuiMappetDashboardPanel.KEYS_CATEGORY)
                .held(Keyboard.KEY_LCONTROL);
        this.code.keys().ignoreFocus().register(IKey.lang("mappetextras.utils_module.beautify"), Keyboard.KEY_L, () -> this.onBeautifierAction(this.code))
                .category(GuiMappetDashboardPanel.KEYS_CATEGORY)
                .held(Keyboard.KEY_LCONTROL)
                .held(Keyboard.KEY_LMENU);

        this.searchIcon = new GuiIconElement(mc, MPEIcons.FOOD_PIPE, (b) -> this.search.toggleSearch());
        this.searchIcon.setVisible(this.data != null && this.allowed && this.code.isVisible());
        this.searchIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.search_and_replace"), Direction.LEFT);

        this.beautifierIcon = new GuiIconElement(mc, MPEIcons.CLOTHES_FAVOUR, (b) -> this.onBeautifierAction(this.code));
        this.beautifierIcon.setVisible(this.data != null && this.allowed && this.code.isVisible());
        this.beautifierIcon.tooltip(IKey.lang("mappetextras.utils_module.beautify"), Direction.LEFT);

        this.iconBar.add(this.searchIcon);
        this.iconBar.add(this.beautifierIcon);
    }

    @Inject(method = "updateButtons", at = @At("TAIL"), remap = false)
    public void updateButtons(CallbackInfo ci) {
        if (this.searchIcon != null) {
            this.searchIcon.setVisible(this.data != null && this.allowed && this.code.isVisible());
        }

        if (this.beautifierIcon != null) {
            this.beautifierIcon.setVisible(this.data != null && this.allowed && this.code.isVisible());
        }
    }

    private void onBeautifierAction(GuiTextEditor editor) {
        String formattedCode = "";
        try {

            UtilsModule module = UtilsModule.getInstance();
            BeautifierOptions options = Beautifier.getOptions(
                    module.beautifierIndentEmptyLines.get(),
                    module.beautifierUnindentChainedMethods.get(),
                    module.beautifierBreakChainedMethods.get()
            );
            formattedCode = UtilsModule.getInstance().beautifier.beautify(editor.getText(), options);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (formattedCode.isEmpty()) {
            return;
        }

        editor.selectAll();
        TextEditUndo undo = new TextEditUndo(editor);
        editor.deleteSelection();

        editor.writeString(formattedCode);

        undo.ready().post(editor.getText(), editor.cursor, editor.selection);
        ((GuiTextEditorSearchable) editor).getUndo().pushUndo(undo);
    }
}
