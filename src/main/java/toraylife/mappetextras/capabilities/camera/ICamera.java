package toraylife.mappetextras.capabilities.camera;

import net.minecraft.nbt.NBTTagCompound;

public interface ICamera {
    float getPitch();
    float getYaw();
    float getRoll();
    boolean isEnabled();

    void setPitch(float pitch);
    void setYaw(float yaw);
    void setRoll(float roll);
    void setEnabled(boolean enabled);

    NBTTagCompound serializeNBT();
    void deserializeNBT(NBTTagCompound tag);
}
