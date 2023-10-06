package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.client.ClientData;

public class ResolutionProvider implements IClientDataProvider{
    @Override
    public NBTTagCompound getData() {
        int height = Minecraft.getMinecraft().displayHeight;
        int width = Minecraft.getMinecraft().displayWidth;

        NBTTagCompound object = new NBTTagCompound();
        object.setInteger("x", width);
        object.setInteger("y", height);

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.RESOLUTION.toString(), object);

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {}
}
