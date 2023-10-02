package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.StateTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptStringTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptStateTriggerBlock;

public class ScriptStateTriggerBlock extends ScriptStringTriggerBlock<StateTriggerBlock> implements IScriptStateTriggerBlock {

    public String getTarget() {
        return this.getTriggerBlock().target.mode.name();
    }
    public void setTarget(String target) {
        this.getTriggerBlock().target.mode = TargetMode.valueOf(target.toUpperCase());
    }

    public Object getValue() {
        return this.getTriggerBlock().value;
    }
    public void setValue(Object value) {
        this.getTriggerBlock().value = value;
    }

    public String getMode() {
        return this.getTriggerBlock().mode.name();
    }
    public void setMode(String mode) {
        this.getTriggerBlock().mode = StateTriggerBlock.StateMode.valueOf(mode.toUpperCase());
    }

    public ScriptStateTriggerBlock() {
        this(new StateTriggerBlock());
    }

    public ScriptStateTriggerBlock(StateTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
