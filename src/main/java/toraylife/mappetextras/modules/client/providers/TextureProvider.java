package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

public class TextureProvider implements IClientDataProvider{
    @Override
    public void setData(NBTTagCompound data) {
        Minecraft.getMinecraft().getTextureManager().onResourceManagerReload(Minecraft.getMinecraft().getResourceManager());
    }
}

