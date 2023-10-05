package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ScriptTileEntity.class, remap = false)
public class MixinScriptTileEntity {
    public ScriptTileEntity create(TileEntity tileEntity){
        return new ScriptTileEntity(tileEntity);
    }
}
