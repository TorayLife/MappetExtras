package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.ItemTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptItemTriggerBlock;

public class ScriptItemTriggerBlock extends ScriptTriggerBlock<ItemTriggerBlock> implements IScriptItemTriggerBlock {

    public String getTarget() {
        return this.getTriggerBlock().target.mode.name();
    }
    public void setTarget(String target) {
        this.getTriggerBlock().target.mode = TargetMode.valueOf(target.toUpperCase());
    }

    public String getMode() {
        return this.getTriggerBlock().mode.name();
    }
    public void setMode(String mode) {
        this.getTriggerBlock().mode = ItemTriggerBlock.ItemMode.valueOf(mode.toUpperCase());
    }

    public ScriptItemTriggerBlock() {
        this(new ItemTriggerBlock());
    }

    public ScriptItemTriggerBlock(ItemTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
