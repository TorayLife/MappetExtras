package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.DialogueTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEDataTriggerBlock;

public class MPEDialogueTriggerBlock extends MPEDataTriggerBlock<DialogueTriggerBlock> {

    public MPEDialogueTriggerBlock() {
        this(new DialogueTriggerBlock());
    }

    public MPEDialogueTriggerBlock(DialogueTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
