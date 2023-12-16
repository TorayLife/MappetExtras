package toraylife.mappetextras.modules.client.scripts.code;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.mclib.utils.Interpolation;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.camera.Camera;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.network.PacketCapability;
import toraylife.mappetextras.modules.client.network.PacketRenderWithEntity;
import toraylife.mappetextras.modules.client.scripts.user.IMinecraftCamera;
import toraylife.mappetextras.modules.client.scripts.user.IMinecraftCameraShake;
import toraylife.mappetextras.network.Dispatcher;

public class MinecraftCamera extends ScriptPlayer implements IMinecraftCamera {
    private final Camera camera = Camera.get(entity);
    public MinecraftCamera(EntityPlayerMP entity) {
        super(entity);
    }

    @Override
    public IMinecraftCameraShake getShake(){
        return new MinecraftCameraShake(entity);
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
        Dispatcher.sendTo(new PacketRenderWithEntity(entity.getMinecraftEntity().getEntityId()), this.entity);
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

                this.sendToCapability();
            }));
        }
    }

    private void sendToCapability(){
        Dispatcher.sendTo(new PacketCapability(this.camera.serializeNBT(), AccessType.CAMERA), entity);
    }
}
