package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.DialogueConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptDialogueConditionBlock;

public class ScriptDialogueConditionBlock extends ScriptTargetConditionBlock<DialogueConditionBlock> implements IScriptDialogueConditionBlock {

    public ScriptDialogueConditionBlock() {
        this(new DialogueConditionBlock());
    }

    public ScriptDialogueConditionBlock(DialogueConditionBlock conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public String getMarker() {
        return this.conditionBlock.marker;
    }

    public void setMarker(String marker) {
        this.conditionBlock.marker = marker;
    }
}
