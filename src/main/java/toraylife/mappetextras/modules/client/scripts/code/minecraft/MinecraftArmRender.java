package toraylife.mappetextras.modules.client.scripts.code.minecraft;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.mclib.utils.Interpolation;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.CapabilitiesType;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.modules.client.network.PacketCapability;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftArmRender;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.network.Dispatcher;

public class MinecraftArmRender implements IMinecraftArmRender {
    private final EntityPlayerMP player;
    private final MainHand mainHand;
    private final OffHand offHand;
    private final int hand;

    public MinecraftArmRender(EntityPlayerMP player, int hand) {
        this.player = player;
        this.mainHand = MainHand.get(this.player);
        this.offHand = OffHand.get(this.player);

        this.hand = hand;
    }

    @Override
    public void setRotate(double angle, double x, double y, double z) {
        if(this.hand == 0){
            this.mainHand.setRotate(angle, x, y, z);
        }else{
            this.offHand.setRotate(angle, x, y, z);
        }

        this.sendToCapability();
    }

    @Override
    public void setRotations(float pitch, float yaw, float yawHead){
        pitch = (float) Math.toDegrees(pitch);
        yaw = (float) Math.toDegrees(yaw);
        double x = Math.cos(yaw) * Math.cos(pitch);
        double y = Math.sin(pitch);
        double z = Math.sin(yaw) * Math.cos(pitch);

        float angle = yawHead;

        this.setRotate(angle, x, y, z);
    }

    @Override
    public ScriptVector getRotations(){
        ScriptVectorAngle rotate = this.getRotate();

        double pitch = Math.atan2(rotate.y, rotate.z);
        double yaw = Math.atan2(rotate.x, rotate.z);
        double yawHead = rotate.angle;

        return new ScriptVector(pitch, yaw, yawHead);
    }

    @Override
    public ScriptVectorAngle getRotate() {
        if(this.hand == 0){
            return this.mainHand.getRotate();
        }else{
            return this.offHand.getRotate();
        }
    }

    @Override
    public void setPosition(double x, double y, double z){
        if(this.hand == 0){
            this.mainHand.setPosition(x, y, z);
        }else{
            this.offHand.setPosition(x, y, z);
        }

        this.sendToCapability();
    }

    @Override
    public void setPosition(ScriptVector pos){
        if(this.hand == 0){
            this.mainHand.setPosition(pos.x, pos.y, pos.z);
        }else{
            this.offHand.setPosition(pos.x, pos.y, pos.z);
        }

        this.sendToCapability();
    }

    @Override
    public ScriptVector getPosition() {
        if(this.hand == 0){
            ScriptVector pos = this.mainHand.getPosition();
            return new ScriptVector(pos.x, pos.y, pos.z);
        }else{
            ScriptVector pos = this.offHand.getPosition();
            return new ScriptVector(pos.x, pos.y, pos.z);
        }
    }

    @Override
    public boolean isRender() {
        if(this.hand == 0){
            return this.mainHand.isRender();
        }else{
            return this.offHand.isRender();
        }
    }

    @Override
    public void setRender(boolean render) {
        if(this.hand == 0){
            this.mainHand.setRender(render);
        }else{
            this.offHand.setRender(render);
        }

        this.sendToCapability();
    }

    private void sendToCapability(){
        if(this.hand == 0){
            Dispatcher.sendTo(new PacketCapability(this.mainHand.serializeNBT(), CapabilitiesType.ARM_RENDER), this.player);
        }

        if(this.hand == 1){
            Dispatcher.sendTo(new PacketCapability(this.offHand.serializeNBT(), CapabilitiesType.ARM_RENDER), this.player);
        }
    }

    @Override
    public void moveTo(String interpolation, int durationTicks, double x, double y, double z){
        Interpolation interp = Interpolation.valueOf(interpolation.toUpperCase());

        double startX = this.getPosition().x;
        double startY = this.getPosition().y;
        double startZ = this.getPosition().z;

        for (int i = 0; i < durationTicks; i++) {
            float progress = (float) i / (float) durationTicks;
            double interpX = interp.interpolate(startX, x, progress);
            double interpY = interp.interpolate(startY, y, progress);
            double interpZ = interp.interpolate(startZ, z, progress);

            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(i, () -> {
                this.setPosition(interpX, interpY, interpZ);
            }));
        }
    }

    @Override
    public void rotateTo(String interpolation, int durationTicks, double angle, double x, double y, double z){
        Interpolation interp = Interpolation.valueOf(interpolation.toUpperCase());

        double startAngle = this.getRotate().angle;
        double startX = this.getRotate().x;
        double startY = this.getRotate().y;
        double startZ = this.getRotate().z;

        for (int i = 0; i < durationTicks; i++) {
            float progress = (float) i / (float) durationTicks;
            double interpAngle = interp.interpolate(startAngle, angle, progress);
            double interpX = interp.interpolate(startX, x, progress);
            double interpY = interp.interpolate(startY, y, progress);
            double interpZ = interp.interpolate(startZ, z, progress);


            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(i, () -> {
                this.setRotate(interpAngle, interpX, interpY, interpZ);
            }));
        }
    }

    @Override
    public void reset() {
        this.setPosition(0, 0, 0);
        this.setRotate(0, 0, 0, 0);
    }
}
