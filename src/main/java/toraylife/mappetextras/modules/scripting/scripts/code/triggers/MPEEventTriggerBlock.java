package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.EventTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEDataTriggerBlock;

public class MPEEventTriggerBlock extends MPEDataTriggerBlock<EventTriggerBlock> {

    public MPEEventTriggerBlock() {
        this(new EventTriggerBlock());
    }

    public MPEEventTriggerBlock(EventTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
