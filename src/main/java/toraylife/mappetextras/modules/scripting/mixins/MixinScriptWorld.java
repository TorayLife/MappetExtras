package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.blockbuster.common.tileentity.TileEntityModel;
import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
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
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.blocks.ScriptConditionModelTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.blocks.ScriptEmitterTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.blocks.ScriptModelTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.blocks.ScriptTriggerTileEntity;

import java.lang.reflect.Constructor;
import java.util.HashMap;

@Mixin(value = ScriptWorld.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptWorld")
public abstract class MixinScriptWorld {
    @Shadow
    private World world;
    @Shadow
    private BlockPos.MutableBlockPos pos;

    /**
     * Gets the biome name at the given block position coordinates.
     *
     * @return the name of the biome
     */
    public String getBiome(int x, int y, int z) {
        return world.getBiome(new BlockPos(x, y, z)).getBiomeName();
    }

    /**
     * Gets the biome name at the position specified by a ScriptVector.
     *
     * @param pos the ScriptVector holding the position
     * @return the name of the biome at that position
     */
    public String getBiome(ScriptVector pos) {
        return this.getBiome((int) pos.x, (int) pos.y, (int) pos.z);
    }

    @Inject(method = "getTileEntity",
            at = @At(
                    value = "RETURN",
                    opcode = 1
            ),
            remap = false, cancellable = true)
    public void getTileEntity(int x, int y, int z, CallbackInfoReturnable<IScriptTileEntity> cir) {

        // Thanks to dyamo
        final HashMap<Class<? extends TileEntity>, Class<? extends IScriptTileEntity>> tiles = new HashMap<>();
        tiles.put(TileTrigger.class, ScriptTriggerTileEntity.class);
        tiles.put(TileEmitter.class, ScriptEmitterTileEntity.class);
        tiles.put(TileConditionModel.class, ScriptConditionModelTileEntity.class);
        tiles.put(TileEntityModel.class, ScriptModelTileEntity.class);

        TileEntity tileEntity = this.world.getTileEntity(this.pos.setPos(x, y, z));

        Class<? extends TileEntity> tileEntityClass = tileEntity.getClass();
        Class<? extends IScriptTileEntity> scriptTileEntityClass = tiles.get(tileEntityClass);

        try {
            if (scriptTileEntityClass != null) {
                Constructor<? extends IScriptTileEntity> constructor = scriptTileEntityClass.getConstructor(tileEntityClass);
                IScriptTileEntity scriptTileEntity = constructor.newInstance(tileEntity);
                cir.setReturnValue(scriptTileEntity);
            } else {
                cir.setReturnValue(new ScriptTileEntity(tileEntity));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}