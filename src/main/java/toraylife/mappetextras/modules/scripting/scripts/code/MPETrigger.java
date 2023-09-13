package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPETriggerBlock;

import java.util.List;

public class MPETrigger {
    public Trigger trigger;

    public MPETrigger() {
        this(new Trigger());
    }

    public MPETrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public List<AbstractTriggerBlock> getBlocks() {
        return this.trigger.blocks;
    }

    public void removeBlock(int index) {
        this.trigger.blocks.remove(index);
    }

    public void addBlock(MPETriggerBlock<? extends AbstractTriggerBlock> triggerBlock) {
        this.trigger.blocks.add(triggerBlock.triggerBlock);
    }

    public boolean isEmpty() {
        return this.trigger.isEmpty();
    }
}
