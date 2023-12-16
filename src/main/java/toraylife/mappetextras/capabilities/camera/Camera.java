package toraylife.mappetextras.capabilities.camera;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class Camera implements ICamera {
    private EntityPlayer player;

    public float yaw, pitch, roll;
    public boolean enabled = false;

    public static Camera get(EntityPlayer player)
    {
        ICamera cameraCapability = player == null ? null : player.getCapability(CameraProvider.CAMERA, null);

        if (cameraCapability instanceof Camera)
        {
            Camera profile = (Camera) cameraCapability;
            profile.player = player;

            return profile;
        }
        return null;
    }

    @Override
    public float getYaw(){
        return this.yaw;
    }

    @Override
    public float getPitch(){
        return this.pitch;
    }

    @Override
    public float getRoll() {
        return this.roll;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public void setRoll(float roll){
        this.roll = roll;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();

        tag.setFloat("pitch", this.pitch);
        tag.setFloat("yaw", this.yaw);
        tag.setFloat("roll", this.roll);
        tag.setBoolean("enabled", this.enabled);

        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag) {
        if (tag.hasKey("pitch")) {
            this.pitch = tag.getFloat("pitch");
        }
        if (tag.hasKey("yaw")) {
            this.yaw = tag.getFloat("yaw");
        }
        if(tag.hasKey("roll")){
            this.roll = tag.getFloat("roll");
        }
        if(tag.hasKey("enabled")){
            this.enabled = tag.getBoolean("enabled");
        }
    }
}
