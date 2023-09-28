package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptTriggerBlock;

import java.util.List;

public interface IScriptTrigger {

    /**
     * Retrieves the list of condition blocks.
     *
     * @return list of {@link AbstractTriggerBlock} blocks,
     * that are required to create a {@link IScriptTriggerBlock}.
     * See {@link IScriptTriggerFactory} for more info.
     */
    List<AbstractTriggerBlock> getBlocks();

    /**
     * Removes a trigger block at the specified index.
     */
    void removeBlock(int index);


    /**
     * Adds a trigger block to the script trigger.
     */
    void addBlock(ScriptTriggerBlock<? extends AbstractTriggerBlock> triggerBlock);

    /**
     * Checks if the blocks list doesn't contain any trigger.
     */
    boolean isEmpty();
}
