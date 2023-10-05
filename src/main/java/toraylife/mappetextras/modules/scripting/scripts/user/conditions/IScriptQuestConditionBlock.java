package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.blocks.QuestConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptTargetConditionBlock;

public interface IScriptQuestConditionBlock extends IScriptTargetConditionBlock<QuestConditionBlock> {
    String getCheckType();

    void setCheckType(String type);
}
