package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.ItemTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptItemTriggerBlock;

public class ScriptItemTriggerBlock extends ScriptTriggerBlock<ItemTriggerBlock> implements IScriptItemTriggerBlock {

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

    public ScriptItemTriggerBlock() {
        this(new ItemTriggerBlock());
    }

    public ScriptItemTriggerBlock(ItemTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
