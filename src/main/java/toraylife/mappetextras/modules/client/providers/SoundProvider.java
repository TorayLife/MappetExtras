package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

public class SoundProvider implements IClientDataProvider{
    @Override
    public void setData(NBTTagCompound data) {
        Minecraft.getMinecraft().getSoundHandler().update();
    }
}
