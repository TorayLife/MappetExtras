package toraylife.mappetextras.modules.scripting.scripts.user.triggers;

import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptStringTriggerBlock;

public interface IScriptSoundTriggerBlock extends IScriptStringTriggerBlock<SoundTriggerBlock> {

    String getTarget();
    void setTarget(String target);
}
