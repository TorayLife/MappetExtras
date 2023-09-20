package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.ExpressionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;

public class ScriptExpressionConditionBlock extends ScriptConditionBlock<ExpressionConditionBlock> {

    public String getExpression() {
        return this.conditionBlock.expression;
    }

    public void setExpression(String expression) {
        this.conditionBlock.expression = expression;
    }
}
