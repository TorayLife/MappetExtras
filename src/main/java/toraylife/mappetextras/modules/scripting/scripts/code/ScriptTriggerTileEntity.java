package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.tile.TileTrigger;

public class ScriptTriggerTileEntity extends ScriptTileEntity {
    public ScriptTriggerTileEntity(TileTrigger tile) {
        super(tile);
    }

    public ScriptTrigger getLeft() {
        return new ScriptTrigger(((TileTrigger)this.getMinecraftTileEntity()).leftClick);
    }

    public void setLeft(ScriptTrigger trigger) {
        ((TileTrigger)this.getMinecraftTileEntity()).leftClick = trigger.trigger;
    }

    public ScriptTrigger getRight() {
        return new ScriptTrigger(((TileTrigger)this.getMinecraftTileEntity()).rightClick);
    }

    public void setRight(ScriptTrigger trigger) {
        ((TileTrigger)this.getMinecraftTileEntity()).rightClick = trigger.trigger;
    }
}
