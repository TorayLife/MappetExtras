package toraylife.mappetextras.modules.utils.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.utils.scripts.code.ScriptFiles;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

@Mixin(value = ScriptFactory.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptFactory")
public class MixinScriptFactory {

    /**
     * get script files test description //TODO delete before release
     * @return
     */
    public ScriptFiles getScriptFiles() {
        return new ScriptFiles();
    }
}
