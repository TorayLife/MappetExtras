package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.ConditionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptCondition;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;

public class ScriptConditionConditionBlock extends ScriptConditionBlock<ConditionConditionBlock> {

    public ScriptCondition getCondition() {
        return new ScriptCondition(this.conditionBlock.condition);
    }

    public void setCondition(ScriptCondition condition) {
        this.conditionBlock.condition = condition.condition;
    }
}
