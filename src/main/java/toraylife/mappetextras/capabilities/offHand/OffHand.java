package toraylife.mappetextras.capabilities.offHand;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public class OffHand implements IOffHand {
    private EntityPlayer player;
    private double x;
    private double y;
    private double z;
    private double angle;

    private double posX;
    private double posY;
    private double posZ;

    private boolean render = true;

    private int hand;

    public static OffHand get(EntityPlayer player)
    {
        IOffHand offHandCapability = player == null ? null : player.getCapability(OffHandProvider.OFF, null);

        if (offHandCapability instanceof OffHand)
        {
            OffHand profile = (OffHand) offHandCapability;
            profile.player = player;

            return profile;
        }
        return null;
    }

    //ANGLE
    @Override
    public ScriptVectorAngle getRotate() {
        return new ScriptVectorAngle(angle, x, y, z);
    }

    @Override
    public void setRotate(double angle, double x, double y, double z) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //RENDER
    @Override
    public boolean isRender() {
        return this.render;
    }

    @Override
    public void setRender(boolean render) {
        this.render = render;
    }

    //POSITION
    @Override
    public ScriptVector getPosition(){
        return new ScriptVector(this.posX, this.posY, this.posZ);
    }

    @Override
    public void setPosition(double x, double y, double z){
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    @Override
    public void setPosition(ScriptVector pos){
        this.posX = pos.x;
        this.posY = pos.y;
        this.posZ = pos.z;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();

        tag.setDouble("x", this.getRotate().x);
        tag.setDouble("y", this.getRotate().y);
        tag.setDouble("z", this.getRotate().z);
        tag.setDouble("angle", this.getRotate().angle);

        tag.setDouble("posX", this.getPosition().x);
        tag.setDouble("posY", this.getPosition().y);
        tag.setDouble("posZ", this.getPosition().z);

        tag.setBoolean("render", this.isRender());

        tag.setInteger("hand", 1);

        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag)
    {
        if (tag.hasKey("x")) {
            this.x = tag.getDouble("x");
        }
        if (tag.hasKey("y")) {
            this.y = tag.getDouble("y");
        }
        if (tag.hasKey("z")) {
            this.z = tag.getDouble("z");
        }
        if (tag.hasKey("angle")) {
            this.angle = tag.getDouble("angle");
        }

        if (tag.hasKey("posX")) {
            this.posX = tag.getDouble("posX");
        }
        if (tag.hasKey("posY")) {
            this.posY = tag.getDouble("posY");
        }
        if (tag.hasKey("posZ")) {
            this.posZ = tag.getDouble("posZ");
        }

        if (tag.hasKey("render")) {
            this.render = tag.getBoolean("render");
        }

        if (tag.hasKey("hand")) {
            this.hand = tag.getInteger("hand");
        }
    }
}
