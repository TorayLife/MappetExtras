package toraylife.mappetextras.modules.utils.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.utils.scripts.code.ScriptFiles;

@Mixin(value = ScriptFactory.class, remap = false)
public class MixinScriptFactory {

    public ScriptFiles getScriptFiles() {
        return new ScriptFiles();
    }
}
