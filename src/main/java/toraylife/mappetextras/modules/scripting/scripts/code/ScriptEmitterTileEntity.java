package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.tile.TileEmitter;

public class ScriptEmitterTileEntity extends ScriptTileEntity {
    public ScriptEmitterTileEntity(TileEmitter tile) {
        super(tile);
    }


    public ScriptChecker getChecker() {
        return new ScriptChecker(((TileEmitter)this.getMinecraftTileEntity()).getChecker());
    }
}
