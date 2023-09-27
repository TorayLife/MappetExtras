package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.conditions.Condition;
import mchorse.mappet.api.conditions.blocks.AbstractConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptCondition;

import java.util.List;

public class ScriptCondition implements IScriptCondition {
    public Condition condition;

    public ScriptCondition() {
        this(new Condition(false));
    }

    public ScriptCondition(Condition condition) {
        this.condition = condition;
    }

    public List<AbstractConditionBlock> getBlocks() {
        return this.condition.blocks;
    }

    public void removeBlock(int index) {
        this.condition.blocks.remove(index);
    }

    public void addBlock(ScriptConditionBlock<? extends AbstractConditionBlock> conditionBlock) {
        this.condition.blocks.add(conditionBlock.conditionBlock);
    }
}
