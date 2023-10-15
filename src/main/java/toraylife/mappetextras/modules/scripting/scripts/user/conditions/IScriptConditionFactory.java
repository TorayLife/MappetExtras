package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.Checker;
import mchorse.mappet.api.conditions.Condition;
import mchorse.mappet.api.conditions.blocks.*;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

/**
 * Using for creating {@link IScriptConditionBlock} objects.
 *
 * <pre>{@code
 * function main(c) {
 *     var tile = c.world.getTileEntity(62, 80, -142);
 *     var conditionFactory = mappet.getConditionFactory();
 *     var checker = conditionFactory.createChecker();
 *     var condition = conditionFactory.createCondition();
 *     var conditionBlock = conditionFactory.createWorldTimeConditionBlock();
 *     conditionBlock.setTime("RANGE")
 *     conditionBlock.setMax(15000);
 *     conditionBlock.setMin(14000);
 *     condition.add(conditionBlock);
 *     checker.setCondition(condition);
 *     tile.setChecker(checker);
 * }
 * }</pre>
 *
 * <p>You can also create condition blocks from mappet objects. ({@link IScriptCondition#getBlocks()})</p>
 *
 * <pre>{@code
 * function main(c) {
 *     var tile = c.world.getTileEntity(62, 80, -142);
 *     var conditionFactory = mappet.getConditionFactory();
 *     var checker = tile.getChecker();
 *     var condition = conditionFactory.createWorldTimeConditionBlock(checker.getBlocks()[0]);
 *     // edit condition
 * }
 * }</pre>
 */
public interface IScriptConditionFactory {

    /**
     * Creates an instance of the IScriptChecker.
     */
    IScriptChecker createChecker();

    /**
     * Creates a new IScriptChecker based on the given mappet Checker object (NOT A {@link IScriptChecker}).
     */
    IScriptChecker createChecker(Checker mappetChecker);

    /**
     * Creates a script condition.
     */
    IScriptCondition createCondition();

    /**
     * Create a script condition based on the given mappet condition object (NOT A {@link IScriptCondition}).
     */
    IScriptCondition createCondition(Condition condition);

    /**
     * Creates a new {@link IScriptConditionConditionBlock}.
     */
    IScriptConditionConditionBlock createConditionConditionBlock();

    /**
     * Creates a new {@link IScriptConditionConditionBlock} based on the given mappet {@link ConditionConditionBlock} object.
     */
    IScriptConditionConditionBlock createConditionConditionBlock(ConditionConditionBlock conditionBlock);


    /**
     * Creates a new {@link IScriptDialogueConditionBlock}.
     */
    IScriptDialogueConditionBlock createDialogueConditionBlock();

    /**
     * Creates a new {@link IScriptDialogueConditionBlock} based on the given mappet {@link DialogueConditionBlock} object.
     */
    IScriptDialogueConditionBlock createDialogueConditionBlock(DialogueConditionBlock conditionBlock);

    /**
     * Creates a new {@link IScriptExpressionConditionBlock}.
     */
    IScriptExpressionConditionBlock createExpressionConditionBlock();

    /**
     * Creates a new {@link IScriptExpressionConditionBlock} based on the given mappet {@link ExpressionConditionBlock} object.
     */
    IScriptExpressionConditionBlock createExpressionConditionBlock(ExpressionConditionBlock conditionBlock);

    /**
     * Creates a new {@link IScriptMorphConditionBlock}.
     */
    IScriptMorphConditionBlock createMorphConditionBlock();

    /**
     * Creates a new {@link IScriptMorphConditionBlock} based on the given mappet {@link MorphConditionBlock} object.
     */
    IScriptMorphConditionBlock createMorphConditionBlock(MorphConditionBlock conditionBlock);

    /**
     * Creates a new {@link IScriptQuestConditionBlock}.
     */
    IScriptQuestConditionBlock createQuestConditionBlock();

    /**
     * Creates a new {@link IScriptQuestConditionBlock} based on the given mappet {@link QuestConditionBlock} object.
     */
    IScriptQuestConditionBlock createQuestConditionBlock(QuestConditionBlock conditionBlock);

    /**
     * Creates a new {@link IScriptWorldTimeConditionBlock}.
     */
    IScriptWorldTimeConditionBlock createWorldTimeConditionBlock();

    /**
     * Creates a new {@link IScriptWorldTimeConditionBlock} based on the given mappet {@link WorldTimeConditionBlock} object.
     */
    IScriptWorldTimeConditionBlock createWorldTimeConditionBlock(WorldTimeConditionBlock conditionBlock);

    /**
     * Creates a new {@link IScriptItemConditionBlock}.
     */
    IScriptItemConditionBlock createItemConditionBlock();

    /**
     * Creates a new {@link IScriptItemConditionBlock} based on the given mappet {@link ItemConditionBlock} object.
     */
    IScriptItemConditionBlock createItemConditionBlock(ItemConditionBlock conditionBlock);

    /**
     * Creates a new {@link IScriptConditionBlock} based on the given mappet {@link AbstractConditionBlock} object.
     */
    IScriptConditionBlock createConditionBlock(AbstractConditionBlock conditionBlock);
}
