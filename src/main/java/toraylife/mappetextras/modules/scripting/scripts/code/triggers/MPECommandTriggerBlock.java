package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.CommandTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEStringTriggerBlock;

public class MPECommandTriggerBlock extends MPEStringTriggerBlock<CommandTriggerBlock> {

    public MPECommandTriggerBlock() {
        this(new CommandTriggerBlock());
    }

    public MPECommandTriggerBlock(CommandTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
