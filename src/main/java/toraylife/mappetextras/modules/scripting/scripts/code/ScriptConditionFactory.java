package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.conditions.Checker;
import mchorse.mappet.api.conditions.Condition;
import mchorse.mappet.api.conditions.blocks.*;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.*;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptChecker;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptCondition;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptConditionFactory;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.*;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

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

    public IScriptConditionBlock<? extends AbstractConditionBlock> createConditionBlock(AbstractConditionBlock conditionBlock) {
        return ScriptCondition.createConditionBlock(conditionBlock);
    }
}
