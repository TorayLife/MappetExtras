package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.ScriptEvent;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;

@Mixin(value = ScriptEvent.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptEvent")
public abstract class MixinScriptEvent {
    public void stopScript(String script){
        CommonProxy.eventHandler.removeExecutables(script);
    }
}
