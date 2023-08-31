package toraylife.mappetextras.modules.main.client.gui;

import mchorse.mappet.ClientProxy;
import mchorse.mappet.client.gui.utils.text.GuiText;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiButtonElement;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.GuiUtils;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.config.gui.GuiConfigPanel;
import mchorse.mclib.config.values.ValueGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.CommonProxy;

import java.util.Arrays;
import java.util.List;

public class ValueVersionCheck extends ValueGUI
{
    public ValueVersionCheck(String id)
    {
        super(id);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<GuiElement> getFields(Minecraft mc, GuiConfigPanel config)
    {
        return Arrays.asList(
            new GuiButtonElement(
                mc,
                IKey.lang("mappetextras.config.main_module.show_version_update_message"),
                (b) -> CommonProxy.eventHandler.sendUpdateMessage(mc.player, true)
            ).marginTop(6)
        );
    }
}