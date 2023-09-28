package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.conditions.blocks.AbstractConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

import java.util.List;

public interface IScriptCondition {

    /**
     * Retrieves the list of condition blocks.
     *
     * @return list of {@link AbstractConditionBlock} blocks,
     * that are required to create a {@link IScriptConditionBlock}.
     * See {@link IScriptConditionFactory} for more info.
     */
    List<AbstractConditionBlock> getBlocks();

    /**
     * Removes a condition block at the specified index.
     */
    void removeBlock(int index);

    /**
     * Adds a condition block to this condition.
     */
    void addBlock(ScriptConditionBlock<? extends AbstractConditionBlock> conditionBlock);
}
