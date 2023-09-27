package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.QuestConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptQuestConditionBlock;

public class ScriptQuestConditionBlock extends ScriptTargetConditionBlock<QuestConditionBlock> implements IScriptQuestConditionBlock {

    public ScriptQuestConditionBlock() {
        this(new QuestConditionBlock());
    }

    public ScriptQuestConditionBlock(QuestConditionBlock conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public String getCheckType() {
        return this.conditionBlock.quest.name();
    }

    public void setCheckType(String type) {
        this.conditionBlock.quest = QuestConditionBlock.QuestCheck.valueOf(type.toUpperCase());
    }

}
