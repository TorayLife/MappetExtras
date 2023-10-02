package toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils;

import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;

public interface IScriptTriggerBlock<T extends AbstractTriggerBlock> {

    boolean isEmpty();

    T getTriggerBlock();
}
