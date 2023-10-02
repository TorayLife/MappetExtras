package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.conditions.blocks.AbstractConditionBlock;
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
    List<IScriptConditionBlock<? extends AbstractConditionBlock>> getBlocks();

    /**
     * Removes a condition block at the specified index.
     */
    void removeBlock(int index);

    /**
     * Adds a condition block to this condition.
     */
    void addBlock(IScriptConditionBlock<? extends AbstractConditionBlock> conditionBlock);
}
