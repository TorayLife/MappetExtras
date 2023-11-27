package toraylife.mappetextras.modules.client.scripts.code;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import toraylife.mappetextras.modules.client.scripts.user.IScriptCamera;

public class ScriptCamera extends ScriptPlayer implements IScriptCamera {
    public ScriptCamera(EntityPlayerMP entity) {
        super(entity);
    }

    public ScriptCameraShake getShake(){
        return new ScriptCameraShake(entity);
    }
}
