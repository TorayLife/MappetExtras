package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptTrigger;

import java.util.List;

public class ScriptTrigger implements IScriptTrigger {
    public Trigger trigger;

    public ScriptTrigger() {
        this(new Trigger());
    }

    public ScriptTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public List<AbstractTriggerBlock> getBlocks() {
        return this.trigger.blocks;
    }

    public void removeBlock(int index) {
        this.trigger.blocks.remove(index);
    }

    public void addBlock(ScriptTriggerBlock<? extends AbstractTriggerBlock> triggerBlock) {
        this.trigger.blocks.add(triggerBlock.triggerBlock);
    }

    public boolean isEmpty() {
        return this.trigger.isEmpty();
    }
}
