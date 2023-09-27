package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.WorldTimeConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptWorldTimeConditionBlock;

public class ScriptWorldTimeConditionBlock extends ScriptConditionBlock<WorldTimeConditionBlock> implements IScriptWorldTimeConditionBlock {

    public ScriptWorldTimeConditionBlock() {
        this(new WorldTimeConditionBlock());
    }

    public ScriptWorldTimeConditionBlock(WorldTimeConditionBlock conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public String getTime() {
        return this.conditionBlock.check.name();
    }

    public void setTime(String time) {
        this.conditionBlock.check = WorldTimeConditionBlock.TimeCheck.valueOf(time.toUpperCase());
    }

    public int getMax() {
        return this.conditionBlock.max;
    }

    public void setMax(int max) {
        this.conditionBlock.max = max;
    }

    public int getMin() {
        return this.conditionBlock.min;
    }

    public void setMin(int min) {
        this.conditionBlock.min = min;
    }
}
