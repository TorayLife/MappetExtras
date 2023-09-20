package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTriggerFactory;

@Mixin(value = ScriptFactory.class, remap = false)
public abstract class MixinScriptFactory{

    public ScriptTriggerFactory getTriggerFactory() {
        return new ScriptTriggerFactory();
    }
}