package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.DialogueConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;

public class ScriptDialogueConditionBlock extends ScriptTargetConditionBlock<DialogueConditionBlock> {

    public String getMarker() {
        return this.conditionBlock.marker;
    }

    public void setMarker(String marker) {
        this.conditionBlock.marker = marker;
    }
}
