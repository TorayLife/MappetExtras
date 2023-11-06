package toraylife.mappetextras.modules.utils.mixins.scriptPanel;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import mchorse.mappet.client.gui.scripts.GuiRepl;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mappet.client.gui.utils.text.undo.TextEditUndo;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.context.GuiContextMenu;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.utils.Direction;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
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

    @Inject(
            method = "createScriptContextMenu",
            at = @At(
                    value = "JUMP"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void onCreateScriptContextMenu(Minecraft mc, GuiTextEditor editor, CallbackInfoReturnable<GuiContextMenu> cir, GuiSimpleContextMenu menu) {
        menu.action(MPEIcons.CLOTHES_FAVOUR, IKey.lang("mappetextras.utils_module.beautify"), () -> MixinGuiScriptPanel.onBeautifierAction(editor));
    }

    private static void onBeautifierAction(GuiTextEditor editor) {
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
