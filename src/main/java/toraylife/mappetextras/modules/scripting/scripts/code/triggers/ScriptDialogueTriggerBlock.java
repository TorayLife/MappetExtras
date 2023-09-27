package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.DialogueTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptDataTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptDialogueTriggerBlock;

public class ScriptDialogueTriggerBlock extends ScriptDataTriggerBlock<DialogueTriggerBlock> implements IScriptDialogueTriggerBlock {

    public ScriptDialogueTriggerBlock() {
        this(new DialogueTriggerBlock());
    }

    public ScriptDialogueTriggerBlock(DialogueTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
