package toraylife.mappetextras.capabilities.offHand;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IOffHand {
    // Rotate
    ScriptVectorAngle getRotate();
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