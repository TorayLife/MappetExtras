package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.blocks.ExpressionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

public interface IScriptExpressionConditionBlock extends IScriptConditionBlock<ExpressionConditionBlock> {
    String getExpression();

    void setExpression(String expression);
}
