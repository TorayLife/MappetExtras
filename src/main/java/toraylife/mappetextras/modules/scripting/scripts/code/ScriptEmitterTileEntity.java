package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.network.common.blocks.PacketEditEmitter;
import mchorse.mappet.tile.TileEmitter;

public class ScriptEmitterTileEntity extends ScriptTileEntity {
    public ScriptEmitterTileEntity(TileEmitter tile) {
        super(tile);
    }


    public ScriptChecker getChecker() {
        return new ScriptChecker(((TileEmitter)this.getMinecraftTileEntity()).getChecker());
    }

    public void setChecker(ScriptChecker checker) {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.checker = checker.checker.toNBT();
        tile.setExpression(packetEditEmitter);
    }
}
