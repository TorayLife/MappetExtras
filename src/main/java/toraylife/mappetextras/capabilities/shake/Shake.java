package toraylife.mappetextras.capabilities.shake;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
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

    private float zoom;

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
    public void setSpeed(double minus, double plus){
        this.minus = (float) minus;
        this.plus = (float) plus;
    }

    @Override
    public ScriptVector getSpeed(){
        return new ScriptVector(this.minus, this.plus, 0);
    }

    @Override
    public void setZoom(double zoom){
        this.zoom = (float) zoom;
    }

    @Override
    public double getZoom(){
        return this.zoom;
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
        tag.setDouble("zoom", this.getZoom());
        tag.setDouble("minus", this.getSpeed().x);
        tag.setDouble("plus", this.getSpeed().y);

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

        if (tag.hasKey("zoom")) {
            this.zoom = (float) tag.getDouble("zoom");
        }

        if (tag.hasKey("minus")) {
            this.minus = (float) tag.getDouble("minus");
        }
        if (tag.hasKey("plus")) {
            this.plus = (float) tag.getDouble("plus");
        }
    }
}
