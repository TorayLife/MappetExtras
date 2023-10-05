package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptConditionFactory;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTriggerFactory;

import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

@Mixin(value = ScriptFactory.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptFactory")
public abstract class MixinScriptFactory{
  
    /**
     * Retrieves the trigger factory.
     */
    public ScriptTriggerFactory getTriggerFactory() {
        return new ScriptTriggerFactory();
    }

    /**
     * Get the condition factory.
     */
    public ScriptConditionFactory getConditionFactory() {
        return new ScriptConditionFactory();
    }
}