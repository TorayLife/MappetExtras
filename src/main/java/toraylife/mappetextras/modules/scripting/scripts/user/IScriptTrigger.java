package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptTriggerBlock;

import java.util.List;

public interface IScriptTrigger {

    /**
     * Retrieves the list of condition blocks.
     *
     * <p>See {@link IScriptTriggerFactory} for more info.</p>
     */
    List<IScriptTriggerBlock<? extends AbstractTriggerBlock>> getBlocks();

    /**
     * Sets the given list of trigger blocks to this trigger.
     */
    void setBlocks(List<IScriptTriggerBlock<? extends AbstractTriggerBlock>> blocks);

    /**
     * Removes a trigger block at the specified index.
     */
    void remove(int index);

    /**
     * Adds a trigger block to the script trigger.
     */
    void add(ScriptTriggerBlock<? extends AbstractTriggerBlock> triggerBlock);

    /**
     * Retrieves the IScriptTriggerBlock element at the specified index.
     */
    IScriptTriggerBlock<? extends AbstractTriggerBlock> get(int index);

    /**
     * Set the script trigger block at the specified index.
     */
    void set(int index, IScriptTriggerBlock<? extends AbstractTriggerBlock> triggerBlock);

    /**
     * Checks if the blocks list doesn't contain any trigger.
     */
    boolean isEmpty();
}
