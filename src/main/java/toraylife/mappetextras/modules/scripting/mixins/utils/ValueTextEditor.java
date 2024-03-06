package toraylife.mappetextras.modules.scripting.mixins.utils;

import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiLabel;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.config.gui.GuiConfigPanel;
import mchorse.mclib.config.values.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class ValueTextEditor extends ValueString implements IServerValue, IConfigGuiProvider {
    public ValueTextEditor(String id, String defaultValue) {
        super(id, defaultValue);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<GuiElement> getFields(Minecraft mc, GuiConfigPanel gui) {
        GuiElement element = new GuiElement(mc);
        GuiLabel label = Elements.label(IKey.lang(this.getConfig().getValueLabelKey(this)), 20).anchor(0.0F, 0.5F);
        GuiTextEditor textEditor = new GuiTextEditor(mc, this::setValue);
        textEditor.setText((String)this.getValue());
        textEditor.background().flex().wh(240, 80);
        element.flex().h(100).column(0);
        element.add(label, textEditor.removeTooltip());
        return Arrays.asList(element.tooltip(IKey.lang(this.getConfig().getValueCommentKey(this))));
    }
}
