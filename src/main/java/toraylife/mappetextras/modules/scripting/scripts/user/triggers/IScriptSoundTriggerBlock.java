package toraylife.mappetextras.modules.scripting.scripts.user.triggers;

import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptStringTriggerBlock;

public interface IScriptSoundTriggerBlock extends IScriptStringTriggerBlock<SoundTriggerBlock> {

    String getTarget();
    void setTarget(String target);
}
