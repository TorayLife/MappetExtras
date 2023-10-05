package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.blocks.DialogueConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptTargetConditionBlock;

public interface IScriptDialogueConditionBlock extends IScriptTargetConditionBlock<DialogueConditionBlock> {

    String getMarker();

    void setMarker(String marker);
}
