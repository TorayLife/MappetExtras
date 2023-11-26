package toraylife.mappetextras.modules.client.scripts.code;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
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
}
