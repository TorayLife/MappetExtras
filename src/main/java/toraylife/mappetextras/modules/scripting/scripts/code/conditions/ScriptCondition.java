package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.Condition;
import mchorse.mappet.api.conditions.blocks.*;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptCondition;
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

    @Override
    public void setBlocks(List<IScriptConditionBlock<? extends AbstractConditionBlock>> blocks) {
        this.condition.blocks.clear();
        this.condition.blocks.addAll(blocks.stream().map(IScriptConditionBlock::getConditionBlock).collect(Collectors.toList()));
    }

    @Override
    public void remove(int index) {
        this.condition.blocks.remove(index);
    }

    @Override
    public void add(IScriptConditionBlock<? extends AbstractConditionBlock> triggerBlock) {
        this.condition.blocks.add(triggerBlock.getConditionBlock());
    }

    @Override
    public IScriptConditionBlock<? extends AbstractConditionBlock> get(int index) {
        return createConditionBlock(this.condition.blocks.get(index));
    }

    @Override
    public void set(int index, IScriptConditionBlock<? extends AbstractConditionBlock> triggerBlock) {
        this.condition.blocks.set(index, triggerBlock.getConditionBlock());
    }

    @Override
    public boolean isEmpty() {
        return this.condition.blocks.isEmpty();
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
