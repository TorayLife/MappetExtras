package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.EventTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptDataTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptEventTriggerBlock;

public class ScriptEventTriggerBlock extends ScriptDataTriggerBlock<EventTriggerBlock> implements IScriptEventTriggerBlock {

    public ScriptEventTriggerBlock() {
        this(new EventTriggerBlock());
    }

    public ScriptEventTriggerBlock(EventTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
