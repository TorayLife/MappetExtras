package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.conditions.blocks.AbstractConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;

import java.util.List;

public interface IScriptCondition {

    List<AbstractConditionBlock> getBlocks();

    void removeBlock(int index);

    void addBlock(ScriptConditionBlock<? extends AbstractConditionBlock> conditionBlock);
}
