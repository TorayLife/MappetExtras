package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.EventTriggerBlock;
import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEDataTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEStringTriggerBlock;

public class MPESoundTriggerBlock extends MPEStringTriggerBlock<SoundTriggerBlock> {

    public String getTarget() {
        return this.triggerBlock.target.name();
    }
    public void setTarget(String target) {
        this.triggerBlock.target = TargetMode.valueOf(target.toUpperCase());
    }

    public MPESoundTriggerBlock() {
        this(new SoundTriggerBlock());
    }

    public MPESoundTriggerBlock(SoundTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
