package toraylife.mappetextras.capabilities.mainHand;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MainHand implements IMainHand {
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

    public static MainHand get(EntityPlayer player)
    {
        IMainHand mainHandCapability = player == null ? null : player.getCapability(MainHandProvider.MAIN, null);

        if (mainHandCapability instanceof MainHand)
        {
            MainHand profile = (MainHand) mainHandCapability;
            profile.player = player;

            return profile;
        }
        return null;
    }

    //ANGLE
    @Override
    public NBTTagCompound getRotate() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setDouble("angle", this.angle);
        nbt.setDouble("x", this.x);
        nbt.setDouble("y", this.y);
        nbt.setDouble("z", this.z);

        return nbt;
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

        tag.setDouble("x", this.getRotate().getDouble("x"));
        tag.setDouble("y", this.getRotate().getDouble("y"));
        tag.setDouble("z", this.getRotate().getDouble("z"));
        tag.setDouble("angle", this.getRotate().getDouble("angle"));

        tag.setDouble("posX", this.getPosition().x);
        tag.setDouble("posY", this.getPosition().y);
        tag.setDouble("posZ", this.getPosition().z);

        tag.setBoolean("render", this.isRender());

        tag.setInteger("hand", 0);

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

