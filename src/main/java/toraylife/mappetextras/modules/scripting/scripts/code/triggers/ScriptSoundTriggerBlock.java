package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptStringTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptSoundTriggerBlock;

public class ScriptSoundTriggerBlock extends ScriptStringTriggerBlock<SoundTriggerBlock> implements IScriptSoundTriggerBlock {

    public String getTarget() {
        return this.getTriggerBlock().target.name();
    }
    public void setTarget(String target) {
        this.getTriggerBlock().target = TargetMode.valueOf(target.toUpperCase());
    }

    public ScriptSoundTriggerBlock() {
        this(new SoundTriggerBlock());
    }

    public ScriptSoundTriggerBlock(SoundTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
