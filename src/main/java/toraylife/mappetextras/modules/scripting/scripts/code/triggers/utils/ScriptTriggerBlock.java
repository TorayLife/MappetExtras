package toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils;

import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptTriggerBlock;

public abstract class ScriptTriggerBlock<T extends AbstractTriggerBlock> implements IScriptTriggerBlock<T> {
    public T triggerBlock;

    public boolean isEmpty() {
        return this.triggerBlock.isEmpty();
    }

    @Override
    public T getTriggerBlock() {
        return triggerBlock;
    }
}
