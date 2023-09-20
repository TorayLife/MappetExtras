package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptStringTriggerBlock;

public class ScriptSoundTriggerBlock extends ScriptStringTriggerBlock<SoundTriggerBlock> {

    public String getTarget() {
        return this.triggerBlock.target.name();
    }
    public void setTarget(String target) {
        this.triggerBlock.target = TargetMode.valueOf(target.toUpperCase());
    }

    public ScriptSoundTriggerBlock() {
        this(new SoundTriggerBlock());
    }

    public ScriptSoundTriggerBlock(SoundTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
