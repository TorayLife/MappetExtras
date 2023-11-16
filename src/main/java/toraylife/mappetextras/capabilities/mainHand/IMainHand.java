package toraylife.mappetextras.capabilities.mainHand;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;

public interface IMainHand {
    // Rotate
    NBTTagCompound getRotate();
    void setRotate(double angle, double x, double y, double z);

    // Render
    boolean isRender();
    void setRender(boolean render);

    //Position
    void setPosition(double x, double y, double z);
    void setPosition(ScriptVector pos);
    ScriptVector getPosition();

    // NBT
    public NBTTagCompound serializeNBT();
    public void deserializeNBT(NBTTagCompound tag);
}
