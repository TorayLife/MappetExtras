package toraylife.mappetextras.capabilities.shake;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public class Shake implements IShake{
    private EntityPlayer player;

    private boolean active;

    private float x;

    private float y;

    private float z;

    private float angle;


    private float rotation;

    private float scale;

    private float minus;
    private float plus;

    public static Shake get(EntityPlayer player)
    {
        IShake shakeCapability = player == null ? null : player.getCapability(ShakeProvider.SHAKE, null);

        if (shakeCapability instanceof Shake)
        {
            Shake profile = (Shake) shakeCapability;
            profile.player = player;

            return profile;
        }
        return null;
    }

    @Override
    public void setActive(boolean active){
        this.active = active;
    }

    @Override
    public boolean isActive(){
        return this.active;
    }

    @Override
    public void setRotate(double angle, double x, double y, double z){
        this.angle = (float) angle;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
    }

    @Override
    public ScriptVectorAngle getRotate(){
        return new ScriptVectorAngle(this.angle, this.x, this.y, this.z);
    }

    @Override
    public void setRotation(double rotation){
        this.rotation = (float) rotation;
    }

    @Override
    public double getRotation(){
        return this.rotation;
    }

    @Override
    public void setPlus(double plus){
        this.plus = (float) plus;
    }

    @Override
    public double getPlus(){
        return this.plus;
    }

    @Override
    public void setMinus(double minus){
        this.minus = (float) minus;
    }

    @Override
    public double getMinus(){
        return this.minus;
    }

    @Override
    public void setScale(double scale){
        this.scale = (float) scale;
    }

    @Override
    public double getScale(){
        return this.scale;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();

        tag.setBoolean("active", this.isActive());
        tag.setDouble("x", this.getRotate().x);
        tag.setDouble("y", this.getRotate().y);
        tag.setDouble("z", this.getRotate().z);
        tag.setDouble("angle", this.getRotate().angle);
        tag.setDouble("rotation", this.getRotation());
        tag.setDouble("scale", this.getScale());
        tag.setDouble("minus", this.getMinus());
        tag.setDouble("plus", this.getPlus());

        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag)
    {
        if (tag.hasKey("active")) {
            this.active = tag.getBoolean("active");
        }
        if (tag.hasKey("x")) {
            this.x = (float) tag.getDouble("x");
        }
        if (tag.hasKey("y")) {
            this.y = (float) tag.getDouble("y");
        }
        if (tag.hasKey("z")) {
            this.z = (float) tag.getDouble("z");
        }
        if (tag.hasKey("angle")) {
            this.angle = (float) tag.getDouble("angle");
        }

        if (tag.hasKey("rotation")) {
            this.rotation = (float) tag.getDouble("rotation");
        }

        if (tag.hasKey("scale")) {
            this.scale = (float) tag.getDouble("scale");
        }

        if (tag.hasKey("minus")) {
            this.minus = (float) tag.getDouble("minus");
        }
        if (tag.hasKey("plus")) {
            this.plus = (float) tag.getDouble("plus");
        }
    }
}
