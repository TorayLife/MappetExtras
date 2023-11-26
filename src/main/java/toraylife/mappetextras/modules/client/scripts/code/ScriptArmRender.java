package toraylife.mappetextras.modules.client.scripts.code;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.mclib.utils.Interpolation;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.modules.client.network.PacketArmRenderCapability;
import toraylife.mappetextras.modules.client.scripts.user.IScriptArmRender;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.network.Dispatcher;

public class ScriptArmRender extends ScriptPlayer implements IScriptArmRender{
    private final MainHand mainHand = MainHand.get(entity);
    private final OffHand offHand = OffHand.get(entity);
    private int hand;

    public ScriptArmRender(EntityPlayerMP entity, int hand) {
        super(entity);

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
            Dispatcher.sendTo(new PacketArmRenderCapability(this.mainHand.serializeNBT()), entity);
        }

        if(this.hand == 1){
            Dispatcher.sendTo(new PacketArmRenderCapability(this.offHand.serializeNBT()), entity);
        }
    }

    @Override
    public void moveTo(String interpolation, int durationTicks, double x, double y, double z){
        Interpolation interp = Interpolation.valueOf(interpolation.toUpperCase());

        double startX = new ScriptArmRender(this.entity, this.hand).getPosition().x;
        double startY = new ScriptArmRender(this.entity, this.hand).getPosition().y;
        double startZ = new ScriptArmRender(this.entity, this.hand).getPosition().z;

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
}
