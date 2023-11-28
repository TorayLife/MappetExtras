package toraylife.mappetextras.modules.client.scripts.code;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.shake.Shake;
import toraylife.mappetextras.modules.client.network.PacketShakeCapability;
import toraylife.mappetextras.modules.client.scripts.user.IScriptCameraShake;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.network.Dispatcher;

/**
 * CameraShake interface.
 *
 * This interface represents shake camera
 *
 * It allows you to make shake, turn, "earthquake" by controlling the camera.
 *
 * <pre>{@code
 *     function main(c)
 *     {
 *         const shake = c.player.camera.shake;
 *
 *         shake.setActive(true)
 *         shake.setMinus(0.01)
 *         shake.setPlus(0.01)
 *         shake.setRotation(10)
 *         shake.setScale(0)
 *         shake.setRotate(0.5, 0, 0, 1)
 *     }
 * }</pre>
 */
public class ScriptCameraShake extends ScriptPlayer implements IScriptCameraShake {
    private final Shake shake = Shake.get(entity);
    public ScriptCameraShake(EntityPlayerMP entity) {
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

    public void setScale(double scale){
        this.shake.setScale(scale);

        this.sendToCapability();
    }

    public double getScale(){
        return this.shake.getScale();
    }

    public void setMinus(double minus){
        this.shake.setMinus(minus);

        this.sendToCapability();
    }

    public double getMinus(){
        return this.shake.getMinus();
    }

    public void setPlus(double plus){
        this.shake.setPlus(plus);

        this.sendToCapability();
    }

    public double getPlus(){
        return this.shake.getPlus();
    }

    private void sendToCapability(){
        Dispatcher.sendTo(new PacketShakeCapability(this.shake.serializeNBT()), entity);
    }
}
