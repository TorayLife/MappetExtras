package toraylife.mappetextras.modules.client.scripts.code;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;
import toraylife.mappetextras.modules.client.network.PacketMinecraftHUDCapability;
import toraylife.mappetextras.modules.client.scripts.user.IScriptMinecraftHUD;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.network.Dispatcher;

public class ScriptMinecraftHUD extends ScriptPlayer implements IScriptMinecraftHUD {
    private MinecraftHUD minecraftHUD = MinecraftHUD.get(entity);
    private String name;
    public ScriptMinecraftHUD(EntityPlayerMP entity, String hud) {
        super(entity);

        this.name = hud.toUpperCase();
        this.minecraftHUD.setName(this.name);
    }

    @Override
    public void setPosition(double x, double y){
        try {
            this.minecraftHUD.setPosition(x, y);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        this.sendToCapability();
    }

    @Override
    public void setScale(double x, double y){
        try {
            this.minecraftHUD.setScale(x, y);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setRotate(double angle, double x, double y, double z){
        try {
            this.minecraftHUD.setRotate(angle, x, y, z);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setRender(boolean render){
        try{
            this.minecraftHUD.setRender(render);
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ScriptVector getPosition(){
        try {
            return this.minecraftHUD.getPosition();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ScriptVector getScale(){
        try {
            return this.minecraftHUD.getScale();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ScriptVectorAngle getRotate(){
        try {
            return this.minecraftHUD.getRotate();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isRender(){
        try {
            return this.minecraftHUD.isRender();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendToCapability(){
        Dispatcher.sendTo(new PacketMinecraftHUDCapability(this.minecraftHUD.serializeNBT()), entity);
    }
}
