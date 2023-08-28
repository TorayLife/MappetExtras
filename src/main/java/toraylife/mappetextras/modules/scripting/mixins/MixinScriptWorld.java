package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ScriptWorld.class, remap = false)
public abstract class MixinScriptWorld{
    @Shadow private World world;

    protected IScriptWorld create(World world) {
        return new ScriptWorld(world);
    }
}
