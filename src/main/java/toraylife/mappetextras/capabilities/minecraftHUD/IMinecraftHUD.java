package toraylife.mappetextras.capabilities.minecraftHUD;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IMinecraftHUD {
    public ScriptVector getScale() throws NoSuchFieldException, IllegalAccessException;
    public void setScale(double x, double y) throws NoSuchFieldException, IllegalAccessException;
    public ScriptVector getPosition() throws NoSuchFieldException, IllegalAccessException;
    public void setPosition(double x, double y) throws NoSuchFieldException, IllegalAccessException;
    public ScriptVectorAngle getRotate() throws NoSuchFieldException, IllegalAccessException;
    public void setRotate(double angle, double x, double y, double z) throws NoSuchFieldException, IllegalAccessException;
    public boolean isRender() throws NoSuchFieldException, IllegalAccessException;
    public void setRender(boolean render) throws NoSuchFieldException, IllegalAccessException;
    public void setName(String name);
    public String getName();


    public NBTTagCompound serializeNBT();
    public void deserializeNBT(NBTTagCompound tag);
}
