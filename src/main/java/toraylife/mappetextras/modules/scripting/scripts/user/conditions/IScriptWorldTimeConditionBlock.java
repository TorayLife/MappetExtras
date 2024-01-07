package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.blocks.WorldTimeConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

public interface IScriptWorldTimeConditionBlock extends IScriptConditionBlock<WorldTimeConditionBlock> {
    String getTime();

    void setTime(String time);

    int getMax();

    void setMax(int max);

    int getMin();

    void setMin(int min);
}
