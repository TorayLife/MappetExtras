package toraylife.mappetextras.modules.client.providers;

import mchorse.mclib.client.gui.utils.GuiUtils;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.client.ClientData;

public class WebLinkProvider implements IClientDataProvider{
    @Override
    public void setData(NBTTagCompound data) {
        GuiUtils.openWebLink(data.getString(ClientData.WEB_LINK.toString()));
    }
}