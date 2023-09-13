package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.tile.TileTrigger;

public class MPETriggerTileEntity extends ScriptTileEntity {
    public MPETriggerTileEntity(TileTrigger tile) {
        super(tile);
    }

    public MPETrigger getLeft() {
        return new MPETrigger(((TileTrigger)this.getMinecraftTileEntity()).leftClick);
    }

    public void setLeft(MPETrigger trigger) {
        ((TileTrigger)this.getMinecraftTileEntity()).leftClick = trigger.trigger;
    }

    public MPETrigger getRight() {
        return new MPETrigger(((TileTrigger)this.getMinecraftTileEntity()).rightClick);
    }

    public void setRight(MPETrigger trigger) {
        ((TileTrigger)this.getMinecraftTileEntity()).rightClick = trigger.trigger;
    }
}
