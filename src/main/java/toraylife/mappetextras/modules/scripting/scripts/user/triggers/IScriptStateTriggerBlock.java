package toraylife.mappetextras.modules.scripting.scripts.user.triggers;

import mchorse.mappet.api.triggers.blocks.StateTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptStringTriggerBlock;

public interface IScriptStateTriggerBlock extends IScriptStringTriggerBlock<StateTriggerBlock> {
    String getTarget();
    void setTarget(String target);

    Object getValue();
    void setValue(Object value);

    String getMode();
    void setMode(String mode);
}
