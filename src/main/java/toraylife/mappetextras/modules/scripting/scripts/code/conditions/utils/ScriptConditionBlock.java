package toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils;

import mchorse.mappet.api.conditions.blocks.AbstractConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

public abstract class ScriptConditionBlock<T extends AbstractConditionBlock> implements IScriptConditionBlock<T> {
    public T conditionBlock;

    @Override
    public T getConditionBlock() {
        return conditionBlock;
    }
}
