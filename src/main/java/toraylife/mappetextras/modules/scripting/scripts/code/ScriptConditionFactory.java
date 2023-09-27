package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.conditions.Checker;
import mchorse.mappet.api.conditions.Condition;
import mchorse.mappet.api.conditions.blocks.ConditionConditionBlock;
import mchorse.mappet.api.conditions.blocks.DialogueConditionBlock;
import mchorse.mappet.api.conditions.blocks.ExpressionConditionBlock;
import mchorse.mappet.api.conditions.blocks.ItemConditionBlock;
import mchorse.mappet.api.conditions.blocks.MorphConditionBlock;
import mchorse.mappet.api.conditions.blocks.QuestConditionBlock;
import mchorse.mappet.api.conditions.blocks.WorldTimeConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptConditionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptDialogueConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptExpressionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptItemConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptMorphConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptQuestConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptWorldTimeConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptChecker;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptCondition;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptConditionFactory;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptConditionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptDialogueConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptExpressionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptItemConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptMorphConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptQuestConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptWorldTimeConditionBlock;

public class ScriptConditionFactory implements IScriptConditionFactory {

    // Checker
    public IScriptChecker createChecker() {
        return this.createChecker(new Checker());
    }

    public IScriptChecker createChecker(Checker mappetChecker) {
        return new ScriptChecker(mappetChecker);
    }

    public IScriptCondition createCondition() {
        return new ScriptCondition();
    }

    public IScriptCondition createCondition(Condition condition) {
        return new ScriptCondition(condition);
    }

    // Condition blocks
    public IScriptConditionConditionBlock createConditionConditionBlock() {
        return new ScriptConditionConditionBlock();
    }

    public IScriptConditionConditionBlock createConditionConditionBlock(ConditionConditionBlock conditionBlock) {
        return new ScriptConditionConditionBlock(conditionBlock);
    }
    public IScriptDialogueConditionBlock createDialogueConditionBlock() {
        return new ScriptDialogueConditionBlock();
    }

    public IScriptDialogueConditionBlock createDialogueConditionBlock(DialogueConditionBlock conditionBlock) {
        return new ScriptDialogueConditionBlock(conditionBlock);
    }

    public IScriptExpressionConditionBlock createExpressionConditionBlock() {
        return new ScriptExpressionConditionBlock();
    }

    public IScriptExpressionConditionBlock createExpressionConditionBlock(ExpressionConditionBlock conditionBlock) {
        return new ScriptExpressionConditionBlock(conditionBlock);
    }

    public IScriptItemConditionBlock createItemConditionBlock() {
        return new ScriptItemConditionBlock();
    }

    public ScriptItemConditionBlock createItemConditionBlock(ItemConditionBlock conditionBlock) {
        return new ScriptItemConditionBlock(conditionBlock);
    }

    public IScriptMorphConditionBlock createMorphConditionBlock() {
        return new ScriptMorphConditionBlock();
    }

    public IScriptMorphConditionBlock createMorphConditionBlock(MorphConditionBlock conditionBlock) {
        return new ScriptMorphConditionBlock(conditionBlock);
    }

    public IScriptQuestConditionBlock createQuestConditionBlock() {
        return new ScriptQuestConditionBlock();
    }

    public IScriptQuestConditionBlock createQuestConditionBlock(QuestConditionBlock conditionBlock) {
        return new ScriptQuestConditionBlock(conditionBlock);
    }

    public IScriptWorldTimeConditionBlock createWorldTimeConditionBlock() {
        return new ScriptWorldTimeConditionBlock();
    }

    public IScriptWorldTimeConditionBlock createWorldTimeConditionBlock(WorldTimeConditionBlock conditionBlock) {
        return new ScriptWorldTimeConditionBlock(conditionBlock);
    }

}
