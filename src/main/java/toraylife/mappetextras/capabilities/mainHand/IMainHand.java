package toraylife.mappetextras.capabilities.mainHand;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IMainHand {
    ScriptVectorAngle getRotate();
    void setRotate(double angle, double x, double y, double z);

    boolean isRender();
    void setRender(boolean render);

    void setPosition(double x, double y, double z);
    void setPosition(ScriptVector pos);
    ScriptVector getPosition();

    public NBTTagCompound serializeNBT();
    public void deserializeNBT(NBTTagCompound tag);
}
