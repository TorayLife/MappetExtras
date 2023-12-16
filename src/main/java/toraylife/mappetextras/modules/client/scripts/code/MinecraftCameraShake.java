package toraylife.mappetextras.modules.client.scripts.code;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.shake.Shake;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.network.PacketCapability;
import toraylife.mappetextras.modules.client.scripts.user.IMinecraftCameraShake;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.network.Dispatcher;

public class MinecraftCameraShake extends ScriptPlayer implements IMinecraftCameraShake {
    private final Shake shake = Shake.get(entity);
    public MinecraftCameraShake(EntityPlayerMP entity) {
        super(entity);
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

    private void sendToCapability(){
        Dispatcher.sendTo(new PacketCapability(this.shake.serializeNBT(), AccessType.SHAKE), entity);
    }
}
