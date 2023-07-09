package toraylife.mappetextras.modules.utils.mixins;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import mchorse.mappet.Mappet;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mclib.client.gui.framework.elements.context.GuiContextMenu;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import toraylife.mappetextras.modules.utils.Beautifier;
import toraylife.mappetextras.modules.utils.UtilsModule;

import java.io.IOException;

@Mixin(GuiScriptPanel.class)
public abstract class MixinGuiScriptPanel
{
    @Shadow public GuiTextEditor code;

    @Inject(
            method = "createScriptContextMenu",
            at = @At(
                    value = "JUMP"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void onCreateScriptContextMenu(Minecraft mc, GuiTextEditor editor, CallbackInfoReturnable<GuiContextMenu> cir, GuiSimpleContextMenu menu)
    {
        if (UtilsModule.getInstance().isEnabled()) return;

        menu.action(Icons.FAVORITE, IKey.lang("mappetextras.utils_module.beautify"), () -> MixinGuiScriptPanel.onClick(editor));
    }

    private static void onClick(GuiTextEditor editor) {
        try {

            editor.setText(Beautifier.beautify(editor.getText()));
        }
        catch (IOException ignored) {}
    }
}
