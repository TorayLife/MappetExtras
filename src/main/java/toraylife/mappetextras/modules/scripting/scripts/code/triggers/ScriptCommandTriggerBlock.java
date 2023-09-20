package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.CommandTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptStringTriggerBlock;

public class ScriptCommandTriggerBlock extends ScriptStringTriggerBlock<CommandTriggerBlock> {

    public ScriptCommandTriggerBlock() {
        this(new CommandTriggerBlock());
    }

    public ScriptCommandTriggerBlock(CommandTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
