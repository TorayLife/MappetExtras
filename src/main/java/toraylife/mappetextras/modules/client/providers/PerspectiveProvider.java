package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.client.ClientData;

public class PerspectiveProvider implements IClientDataProvider {
    public NBTTagCompound getData() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        int thirdPersonView = Minecraft.getMinecraft().gameSettings.thirdPersonView;
        nbtTagCompound.setInteger(ClientData.PESPECTIVE.toString(), thirdPersonView);

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {
        Minecraft.getMinecraft().gameSettings.thirdPersonView = data.getInteger(ClientData.PESPECTIVE.toString());
    }
}