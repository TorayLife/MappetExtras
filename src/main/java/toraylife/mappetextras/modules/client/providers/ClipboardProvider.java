package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.client.ClientData;

public class ClipboardProvider implements IClientDataProvider{
    public NBTTagCompound getData() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        String clipboard = GuiScreen.getClipboardString();
        nbtTagCompound.setString(ClientData.CLIPBOARD.toString(), clipboard);

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {
        GuiScreen.setClipboardString(data.getString(ClientData.CLIPBOARD.toString()));
    }
}