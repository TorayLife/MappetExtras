package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.conditions.Checker;
import mchorse.mappet.api.conditions.Condition;
import mchorse.mappet.api.conditions.blocks.ConditionConditionBlock;
import mchorse.mappet.api.conditions.blocks.DialogueConditionBlock;
import mchorse.mappet.api.conditions.blocks.ExpressionConditionBlock;
import mchorse.mappet.api.conditions.blocks.ItemConditionBlock;
import mchorse.mappet.api.conditions.blocks.MorphConditionBlock;
import mchorse.mappet.api.conditions.blocks.QuestConditionBlock;
import mchorse.mappet.api.conditions.blocks.WorldTimeConditionBlock;
import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptConditionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptDialogueConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptExpressionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptItemConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptQuestConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptWorldTimeConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptMorphTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptConditionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptDialogueConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptExpressionConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptItemConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptMorphConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptQuestConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptWorldTimeConditionBlock;

public interface IScriptConditionFactory {
    IScriptChecker createChecker();

    IScriptChecker createChecker(Checker mappetChecker);

    IScriptCondition createCondition();

    IScriptCondition createCondition(Condition condition);

    IScriptConditionConditionBlock createConditionConditionBlock();

    IScriptConditionConditionBlock createConditionConditionBlock(ConditionConditionBlock conditionBlock);

    IScriptDialogueConditionBlock createDialogueConditionBlock();

    IScriptDialogueConditionBlock createDialogueConditionBlock(DialogueConditionBlock conditionBlock);

    IScriptExpressionConditionBlock createExpressionConditionBlock();

    IScriptExpressionConditionBlock createExpressionConditionBlock(ExpressionConditionBlock conditionBlock);

    IScriptMorphConditionBlock createMorphConditionBlock();

    IScriptMorphConditionBlock createMorphConditionBlock(MorphConditionBlock conditionBlock);

    IScriptQuestConditionBlock createQuestConditionBlock();

    IScriptQuestConditionBlock createQuestConditionBlock(QuestConditionBlock conditionBlock);

    IScriptWorldTimeConditionBlock createWorldTimeConditionBlock();

    IScriptWorldTimeConditionBlock createWorldTimeConditionBlock(WorldTimeConditionBlock conditionBlock);

    IScriptItemConditionBlock createItemConditionBlock();

    IScriptItemConditionBlock createItemConditionBlock(ItemConditionBlock conditionBlock);
}
