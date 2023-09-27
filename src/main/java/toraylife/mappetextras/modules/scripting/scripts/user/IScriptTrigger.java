package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.triggers.blocks.AbstractTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;

import java.util.List;

public interface IScriptTrigger {

    List<AbstractTriggerBlock> getBlocks();


    void removeBlock(int index);


    void addBlock(ScriptTriggerBlock<? extends AbstractTriggerBlock> triggerBlock);


    boolean isEmpty();
}
