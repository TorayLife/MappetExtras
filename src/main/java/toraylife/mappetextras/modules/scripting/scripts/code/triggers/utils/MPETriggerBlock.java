package toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils;

import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;

public abstract class MPETriggerBlock<T extends AbstractTriggerBlock> {
    public T triggerBlock;

    public boolean isEmpty() {
        return this.triggerBlock.isEmpty();
    }
}
