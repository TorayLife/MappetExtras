package toraylife.mappetextras.modules.client.scripts.code.minecraft;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.CapabilitiesType;
import toraylife.mappetextras.capabilities.shake.Shake;
import toraylife.mappetextras.modules.client.network.PacketCapability;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftCameraShake;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.network.Dispatcher;

public class MinecraftCameraShake implements IMinecraftCameraShake {
    private final EntityPlayerMP player;
    private final Shake shake;
    public MinecraftCameraShake(EntityPlayerMP player) {
        this.player = player;
        this.shake = Shake.get(this.player);
    }

    public void setActive(boolean active){
        this.shake.setActive(active);

        this.sendToCapability();
    }

    public boolean isActive(){
        return this.shake.isActive();
    }


    public void setRotate(double angle, double x, double y, double z){
        this.shake.setRotate(angle, x, y, z);

        this.sendToCapability();
    }

    public ScriptVectorAngle getRotate(){
        return this.shake.getRotate();
    }

    public void setRotation(double rotation){
        this.shake.setRotation(rotation);

        this.sendToCapability();
    }

    public double getRotation(){
        return this.shake.getRotation();
    }

    public void setZoom(double scale){
        this.shake.setZoom(scale);

        this.sendToCapability();
    }

    public double getZoom(){
        return this.shake.getZoom();
    }

    public void setSpeed(double minus, double plus){
        this.shake.setSpeed(minus, plus);

        this.sendToCapability();
    }

    public void setSpeed(double speed){
        this.shake.setSpeed(speed, speed);

        this.sendToCapability();
    }

    public ScriptVector getSpeed(){
        return new ScriptVector(this.shake.getSpeed().x, this.shake.getSpeed().y, 0);
    }

    public void reset(){
        this.setRotate(0, 0, 0, 0);
        this.setRotation(0);
        this.setZoom(0);
        this.setSpeed(0);
    }

    private void sendToCapability(){
        Dispatcher.sendTo(new PacketCapability(this.shake.serializeNBT(), CapabilitiesType.SHAKE), this.player);
    }
}
