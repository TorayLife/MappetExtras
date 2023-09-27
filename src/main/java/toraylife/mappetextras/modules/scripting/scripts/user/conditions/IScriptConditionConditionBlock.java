package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.blocks.ConditionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptCondition;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

public interface IScriptConditionConditionBlock extends IScriptConditionBlock<ConditionConditionBlock> {

    ScriptCondition getCondition();

    void setCondition(ScriptCondition condition);
}
