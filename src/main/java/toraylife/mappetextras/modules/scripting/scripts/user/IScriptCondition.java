package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.conditions.blocks.AbstractConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

import java.util.List;

public interface IScriptCondition {

    /**
     * Retrieves the list of condition blocks.
     *
     * <p>See {@link IScriptConditionFactory} for more info.</p>
     */
    List<IScriptConditionBlock<? extends AbstractConditionBlock>> getBlocks();

    /**
     * Sets the given list of condition blocks to this condition.
     */
    void setBlocks(List<IScriptConditionBlock<? extends AbstractConditionBlock>> blocks);

    /**
     * Removes a condition block at the specified index.
     */
    void remove(int index);

    /**
     * Adds a condition block to the condition.
     */
    void add(IScriptConditionBlock<? extends AbstractConditionBlock> triggerBlock);

    /**
     * Retrieves the {@link IScriptConditionBlock} element at the specified index.
     */
    IScriptConditionBlock<? extends AbstractConditionBlock> get(int index);

    /**
     * Set the script condition block at the specified index.
     */
    void set(int index, IScriptConditionBlock<? extends AbstractConditionBlock> triggerBlock);

    /**
     * Checks if the blocks list doesn't contain any condition.
     */
    boolean isEmpty();
}
