package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.conditions.Condition;
import mchorse.mappet.api.conditions.blocks.*;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.*;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptCondition;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptConditionBlock;

import java.util.List;
import java.util.stream.Collectors;

public class ScriptCondition implements IScriptCondition {
    public Condition condition;

    public ScriptCondition() {
        this(new Condition(false));
    }

    public ScriptCondition(Condition condition) {
        this.condition = condition;
    }

    public List<IScriptConditionBlock<? extends AbstractConditionBlock>> getBlocks() {
        return this.condition.blocks.stream().map(ScriptCondition::createConditionBlock).collect(Collectors.toList());
    }

    public void removeBlock(int index) {
        this.condition.blocks.remove(index);
    }

    public void addBlock(IScriptConditionBlock<? extends AbstractConditionBlock> conditionBlock) {
        this.condition.blocks.add(conditionBlock.getConditionBlock());
    }

    public static IScriptConditionBlock<? extends AbstractConditionBlock> createConditionBlock(AbstractConditionBlock abstractConditionBlock) {
        if (abstractConditionBlock instanceof ConditionConditionBlock) {
            return new ScriptConditionConditionBlock((ConditionConditionBlock) abstractConditionBlock);
        } else if (abstractConditionBlock instanceof DialogueConditionBlock) {
            return new ScriptDialogueConditionBlock((DialogueConditionBlock) abstractConditionBlock);
        } else if (abstractConditionBlock instanceof ExpressionConditionBlock) {
            return new ScriptExpressionConditionBlock((ExpressionConditionBlock) abstractConditionBlock);
        } else if (abstractConditionBlock instanceof ItemConditionBlock) {
            return new ScriptItemConditionBlock((ItemConditionBlock) abstractConditionBlock);
        } else if (abstractConditionBlock instanceof MorphConditionBlock) {
            return new ScriptMorphConditionBlock((MorphConditionBlock) abstractConditionBlock);
        } else if (abstractConditionBlock instanceof QuestConditionBlock) {
            return new ScriptQuestConditionBlock((QuestConditionBlock) abstractConditionBlock);
        } else if (abstractConditionBlock instanceof WorldTimeConditionBlock) {
            return new ScriptWorldTimeConditionBlock((WorldTimeConditionBlock) abstractConditionBlock);
        } else {
            throw new IllegalArgumentException("Unknown condition block type!");
        }
    }
}
