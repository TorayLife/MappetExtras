package toraylife.mappetextras.capabilities.shake;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IShake {
    void setActive(boolean active);
    boolean isActive();

    void setRotate(double angle, double x, double y, double z);
    ScriptVectorAngle getRotate();

    void setRotation(double rot);
    double getRotation();

    void setZoom(double zoom);
    double getZoom();

    void setSpeed(double minus, double plus);
    ScriptVector getSpeed();

    //NBT
    public NBTTagCompound serializeNBT();
    public void deserializeNBT(NBTTagCompound tag);
}
