package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.CommandTriggerBlock;
import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import mchorse.mappet.api.triggers.blocks.StateTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEStringTriggerBlock;

public class MPEStateTriggerBlock extends MPEStringTriggerBlock<StateTriggerBlock> {

    public String getTarget() {
        return this.triggerBlock.target.mode.name();
    }
    public void setTarget(String target) {
        this.triggerBlock.target.mode = TargetMode.valueOf(target.toUpperCase());
    }

    public Object getValue() {
        return this.triggerBlock.value;
    }
    public void setValue(Object value) {
        this.triggerBlock.value = value;
    }

    public String getMode() {
        return this.triggerBlock.mode.name();
    }
    public void setMode(String mode) {
        this.triggerBlock.mode = StateTriggerBlock.StateMode.valueOf(mode.toUpperCase());
    }

    public MPEStateTriggerBlock() {
        this(new StateTriggerBlock());
    }

    public MPEStateTriggerBlock(StateTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
