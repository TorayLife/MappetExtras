package toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils;

import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;

public abstract class ScriptTriggerBlock<T extends AbstractTriggerBlock> {
    public T triggerBlock;

    public boolean isEmpty() {
        return this.triggerBlock.isEmpty();
    }
}
