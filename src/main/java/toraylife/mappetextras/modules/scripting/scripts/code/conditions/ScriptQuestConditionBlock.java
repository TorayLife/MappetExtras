package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.QuestConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;

public class ScriptQuestConditionBlock extends ScriptTargetConditionBlock<QuestConditionBlock> {


    public String getCheckType() {
        return this.conditionBlock.quest.name();
    }

    public void setCheckType(String type) {
        this.conditionBlock.quest = QuestConditionBlock.QuestCheck.valueOf(type.toUpperCase());
    }

}
