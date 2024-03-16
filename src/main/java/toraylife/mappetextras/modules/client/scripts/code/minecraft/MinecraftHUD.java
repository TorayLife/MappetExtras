package toraylife.mappetextras.modules.client.scripts.code.minecraft;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.mclib.utils.Interpolation;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.CapabilitiesType;
import toraylife.mappetextras.modules.client.network.PacketCapability;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftHUD;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.network.Dispatcher;

import java.util.Set;

public class MinecraftHUD implements IMinecraftHUD {
    private EntityPlayerMP player;
    private toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD minecraftHUD;
    private String name;
    public MinecraftHUD(EntityPlayerMP entity, String hud) {
        this.player = entity;
        this.minecraftHUD = toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD.get(this.player);

        this.name = hud;
    }

    @Override
    public void setPosition(double x, double y){
        this.minecraftHUD.setName(this.name);
        this.minecraftHUD.setPosition(x, y);

        this.sendToCapability();
    }

    @Override
    public void setScale(double x, double y){
        this.minecraftHUD.setName(this.name);
        this.minecraftHUD.setScale(x, y);

        this.sendToCapability();
    }

    @Override
    public void setRotate(double angle, double x, double y, double z){
        this.minecraftHUD.setName(this.name);
        this.minecraftHUD.setRotate(angle, x, y, z);

        this.sendToCapability();
    }

    @Override
    public ScriptVector getPosition(){
        this.minecraftHUD.setName(this.name);
        return this.minecraftHUD.getPosition();
    }

    @Override
    public ScriptVector getScale(){
        this.minecraftHUD.setName(this.name);
        return this.minecraftHUD.getScale();
    }

    @Override
    public ScriptVectorAngle getRotate(){
        this.minecraftHUD.setName(this.name);
        return this.minecraftHUD.getRotate();
    }

    @Override
    public boolean isRender(){
        this.minecraftHUD.setName(this.name);
        return this.minecraftHUD.isRender();
    }

    @Override
    public void setRender(boolean render){
        this.minecraftHUD.setName(this.name);
        this.minecraftHUD.setRender(render);

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
    public void moveTo(String interpolation, int durationTicks, double x, double y) {
        Interpolation interp = Interpolation.valueOf(interpolation.toUpperCase());
        double startX = this.getPosition().x;
        double startY = this.getPosition().y;

        for (int i = 0; i < durationTicks; i++) {
            float progress = (float) i / (float) durationTicks;
            double interpX = interp.interpolate(startX, x, progress);
            double interpY = interp.interpolate(startY, y, progress);

            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(i, () -> {
                this.setPosition(interpX, interpY);
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
    public void reset(){
        this.setRotate(0, 0, 0, 0);
        this.setPosition(0, 0);
    }

    private void sendToCapability(){
        Dispatcher.sendTo(new PacketCapability(this.minecraftHUD.serializeNBT(), CapabilitiesType.MINECRAFT_HUD), this.player);
    }
}
