package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.ExpressionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptExpressionConditionBlock;

public class ScriptExpressionConditionBlock extends ScriptConditionBlock<ExpressionConditionBlock> implements IScriptExpressionConditionBlock {

    public ScriptExpressionConditionBlock() {
        this(new ExpressionConditionBlock());
    }

    public ScriptExpressionConditionBlock(ExpressionConditionBlock conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public String getExpression() {
        return this.getConditionBlock().expression;
    }

    public void setExpression(String expression) {
        this.getConditionBlock().expression = expression;
    }
}
