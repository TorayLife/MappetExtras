package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.ItemTriggerBlock;
import mchorse.mappet.api.triggers.blocks.StateTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEStringTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPETriggerBlock;

public class MPEItemTriggerBlock extends MPETriggerBlock<ItemTriggerBlock> {

    public String getTarget() {
        return this.triggerBlock.target.mode.name();
    }
    public void setTarget(String target) {
        this.triggerBlock.target.mode = TargetMode.valueOf(target.toUpperCase());
    }

    public String getMode() {
        return this.triggerBlock.mode.name();
    }
    public void setMode(String mode) {
        this.triggerBlock.mode = ItemTriggerBlock.ItemMode.valueOf(mode.toUpperCase());
    }

    public MPEItemTriggerBlock() {
        this(new ItemTriggerBlock());
    }

    public MPEItemTriggerBlock(ItemTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
