package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

@Mixin(value = ScriptWorld.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptWorld")
public abstract class MixinScriptWorld {
    @Shadow
    private World world;

    public String getBiome(int x, int y, int z){
        return world.getBiome(new BlockPos(x, y, z)).getBiomeName();
    }

    public String getBiome(ScriptVector pos){
        return this.getBiome((int) pos.x, (int) pos.y, (int) pos.z);
    }
}
