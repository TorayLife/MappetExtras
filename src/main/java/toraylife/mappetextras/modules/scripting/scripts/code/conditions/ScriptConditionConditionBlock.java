package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.ConditionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptCondition;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptConditionConditionBlock;

public class ScriptConditionConditionBlock extends ScriptConditionBlock<ConditionConditionBlock> implements IScriptConditionConditionBlock {

    public ScriptConditionConditionBlock() {
        this(new ConditionConditionBlock());
    }

    public ScriptConditionConditionBlock(ConditionConditionBlock conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public ScriptCondition getCondition() {
        return new ScriptCondition(this.conditionBlock.condition);
    }

    public void setCondition(ScriptCondition condition) {
        this.conditionBlock.condition = condition.condition;
    }
}
