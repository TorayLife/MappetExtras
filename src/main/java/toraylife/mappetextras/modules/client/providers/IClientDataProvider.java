package toraylife.mappetextras.modules.client.providers;

import net.minecraft.nbt.NBTTagCompound;

public interface IClientDataProvider{
    void setData(NBTTagCompound value) throws Throwable;

    default void setData() throws Throwable {}

    default NBTTagCompound getData() {
        return new NBTTagCompound();
    }

    default NBTTagCompound getData(NBTTagCompound nbtTagCompound) throws NoSuchFieldException, IllegalAccessException {
        return nbtTagCompound;
    }
}