package toraylife.mappetextras.modules.client.scripts.user;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;

public interface IScriptArmRender {
    void setRotate(double angle, double x, double y, double z);
    NBTTagCompound getRotate();

    void setPosition(double x, double y, double z);
    void setPosition(ScriptVector pos);
    ScriptVector getPosition();

    boolean isRender();
    void setRender(boolean render);

}