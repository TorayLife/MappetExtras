package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.client.ClientData;

public class RenderWithEntityProvider implements IClientDataProvider {
    public NBTTagCompound getData() {
        return null;
    }

    @Override
    public void setData(NBTTagCompound data) {
        Entity entity = Minecraft.getMinecraft().player.world.getEntityByID( data.getInteger(ClientData.RENDER_WITH_ENTITY.toString()) );

        Minecraft.getMinecraft().setRenderViewEntity(entity);
    }
}
