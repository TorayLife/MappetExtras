package toraylife.mappetextras.modules.utils.mixins.late.scriptPanel;

import mchorse.mappet.client.gui.utils.text.GuiMultiTextElement;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.utils.ScrollArea;
import mchorse.mclib.utils.undo.UndoManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = GuiMultiTextElement.class, remap = false)
public abstract class MixinGuiMultiTextElement extends GuiElement {
    @Shadow
    private UndoManager<GuiMultiTextElement> undo;

    @Shadow
    public int padding;

    @Shadow
    public int lineHeight;

    @Shadow
    public abstract String getText();

    @Shadow
    public ScrollArea vertical;

    @Shadow
    public ScrollArea horizontal;


    public UndoManager<GuiMultiTextElement> getUndo() {
        return undo;
    }

    public MixinGuiMultiTextElement(Minecraft mc) {
        super(mc);
    }
}
