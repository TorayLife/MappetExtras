package toraylife.mappetextras.capabilities.minecraftHUD;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IMinecraftHUD {
    public ScriptVector getScale();
    public void setScale(double x, double y);
    public ScriptVector getPosition();
    public void setPosition(double x, double y);
    public ScriptVectorAngle getRotate();
    public void setRotate(double angle, double x, double y, double z);
    public boolean isRender();
    public void setRender(boolean render);
    public void setName(String name);
    public String getName();


    public NBTTagCompound serializeNBT();
    public void deserializeNBT(NBTTagCompound tag);
}
