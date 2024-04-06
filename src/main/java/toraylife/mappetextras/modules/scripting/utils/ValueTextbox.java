package toraylife.mappetextras.modules.scripting.utils;

import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.IGuiElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTextElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiLabel;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.config.gui.GuiConfigPanel;
import mchorse.mclib.config.values.ValueString;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class ValueTextbox extends ValueString {

    public ValueTextbox(String id) {
        super(id);
    }

    public ValueTextbox(String id, String defaultValue) {
        super(id, defaultValue);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<GuiElement> getFields(Minecraft mc, GuiConfigPanel gui) {
        GuiElement element = new GuiElement(mc);
        GuiLabel label = Elements.label(IKey.lang(this.getConfig().getValueLabelKey(this)), 0).anchor(0.0F, 0.5F);
        GuiTextElement textbox = new GuiTextElement(mc, this);
        textbox.flex().w(150);
        textbox.field.setMaxStringLength(9999);
        element.flex().row(0).preferred(0).height(20);
        element.add(new IGuiElement[]{label, textbox.removeTooltip()});
        return Arrays.asList(element.tooltip(IKey.lang(this.getConfig().getValueCommentKey(this))));
    }
}
