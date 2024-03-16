package toraylife.mappetextras.modules.client.scripts.code.minecraft;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.mclib.utils.Interpolation;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.capabilities.CapabilitiesType;
import toraylife.mappetextras.capabilities.camera.Camera;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.ClientData;
import toraylife.mappetextras.modules.client.network.PacketCapability;
import toraylife.mappetextras.modules.client.network.PacketClientData;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftCamera;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftCameraShake;
import toraylife.mappetextras.network.Dispatcher;

public class MinecraftCamera implements IMinecraftCamera {
    private EntityPlayerMP player;
    private Camera camera;
    public MinecraftCamera(EntityPlayerMP player) {
        this.player = player;
        this.camera = Camera.get(this.player);
    }

    @Override
    public IMinecraftCameraShake getShake(){
        return new MinecraftCameraShake(this.player);
    }

    @Override
    public void setYaw(float yaw) {
        this.camera.setYaw(yaw);

        this.sendToCapability();
    }

    @Override
    public float getYaw(){
        return this.camera.getYaw();
    }

    @Override
    public float getPitch(){
        return this.camera.getPitch();
    }

    @Override
    public void setPitch(float pitch) {
        this.camera.setPitch(pitch);

        this.sendToCapability();
    }

    @Override
    public void setRoll(float roll) {
        this.camera.setRoll(roll);

        this.sendToCapability();
    }

    @Override
    public float getRoll() {
        return this.camera.getRoll();
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.camera.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return this.camera.isEnabled();
    }

    @Override
    public void setRenderWithEntity(ScriptEntity entity) {
        NBTTagCompound data = new NBTTagCompound();
        data.setInteger(ClientData.RENDER_WITH_ENTITY.toString(), entity.getMinecraftEntity().getEntityId());

        Dispatcher.sendTo(new PacketClientData(ClientData.RENDER_WITH_ENTITY, AccessType.SET, data), this.player);
    }

    @Override
    public void rotateTo(String interpolation, int durationTicks, double pitch, double yaw, double roll){
        Interpolation interp = Interpolation.valueOf(interpolation.toUpperCase());

        double startPitch = this.getPitch();
        double startYaw = this.getYaw();
        double startRoll = this.getRoll();

        for (int i = 0; i < durationTicks; i++) {
            float progress = (float) i / (float) durationTicks;
            double interpPitch = interp.interpolate(startPitch, pitch, progress);
            double interpYaw = interp.interpolate(startYaw, yaw, progress);
            double interpRoll = interp.interpolate(startRoll, roll, progress);

            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(i, () -> {
                this.setPitch((float) interpPitch);
                this.setYaw((float) interpYaw);
                this.setRoll((float) interpRoll);
            }));
        }
    }

    @Override
    public void reset() {
        this.setPitch(0);
        this.setYaw(0);
        this.setRoll(0);
    }

    private void sendToCapability(){
        Dispatcher.sendTo(new PacketCapability(this.camera.serializeNBT(), CapabilitiesType.CAMERA), this.player);
    }
}
