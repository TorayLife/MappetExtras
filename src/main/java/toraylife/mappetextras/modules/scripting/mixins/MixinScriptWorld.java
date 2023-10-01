package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.tile.TileConditionModel;
import mchorse.mappet.tile.TileEmitter;
import mchorse.mappet.tile.TileTrigger;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptConditionModelTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptEmitterTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTriggerTileEntity;

@Mixin(value = ScriptWorld.class, remap = false)
public abstract class MixinScriptWorld {
    @Shadow
    private World world;
    @Shadow
    private BlockPos.MutableBlockPos pos;

    protected IScriptWorld create(World world) {
        return new ScriptWorld(world);
    }

    @Inject(method = "getTileEntity",
        at = @At(
            value = "RETURN",
            opcode = 1
        ),
        remap = false, cancellable = true)
    public void getTileEntity(int x, int y, int z, CallbackInfoReturnable<IScriptTileEntity> cir) {
        TileEntity tileEntity = this.world.getTileEntity(this.pos.setPos(x, y, z));

        // TODO rewrite
        if (tileEntity instanceof TileTrigger) {
            cir.setReturnValue(new ScriptTriggerTileEntity((TileTrigger) tileEntity));
        }
        if (tileEntity instanceof TileEmitter) {
            cir.setReturnValue(new ScriptEmitterTileEntity((TileEmitter) tileEntity));
        }
        if (tileEntity instanceof TileConditionModel) {
            cir.setReturnValue(new ScriptConditionModelTileEntity((TileConditionModel) tileEntity));
        } else {
            cir.setReturnValue(new ScriptTileEntity(tileEntity));
        }
    }
}
