package toraylife.untitledmappetaddon.mixin;

import mchorse.mappet.Mappet;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mclib.client.gui.framework.elements.context.GuiContextMenu;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GuiScriptPanel.class)
public abstract class MixinGuiScriptPanel
{
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
        menu.action(Icons.ALL_DIRECTIONS, IKey.str("LMAO"), () -> Mappet.logger.debug("LMAO"));
    }
}
