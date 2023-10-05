package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.*;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.*;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptTriggerBlock;

import java.util.List;
import java.util.stream.Collectors;

public class ScriptTrigger implements IScriptTrigger {
    public Trigger trigger;

    public ScriptTrigger() {
        this(new Trigger());
    }

    public ScriptTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public List<IScriptTriggerBlock<? extends AbstractTriggerBlock>> getBlocks() {
        return this.trigger.blocks.stream().map(ScriptTrigger::createTriggerBlock).collect(Collectors.toList());
    }

    public void setBlocks(List<IScriptTriggerBlock<? extends AbstractTriggerBlock>> blocks) {
        this.trigger.blocks.clear();
        this.trigger.blocks.addAll(blocks.stream().map(IScriptTriggerBlock::getTriggerBlock).collect(Collectors.toList()));
    }

    public void remove(int index) {
        this.trigger.blocks.remove(index);
    }

    public void add(ScriptTriggerBlock<? extends AbstractTriggerBlock> triggerBlock) {
        this.trigger.blocks.add(triggerBlock.triggerBlock);
    }

    public IScriptTriggerBlock<? extends AbstractTriggerBlock> get(int index) {
        return ScriptTrigger.createTriggerBlock(this.trigger.blocks.get(index));
    }

    public void set(int index, IScriptTriggerBlock<? extends AbstractTriggerBlock> triggerBlock) {
        this.trigger.blocks.set(index, triggerBlock.getTriggerBlock());
    }

    public boolean isEmpty() {
        return this.trigger.isEmpty();
    }

    static public IScriptTriggerBlock<? extends AbstractTriggerBlock> createTriggerBlock(AbstractTriggerBlock abstractTriggerBlock) {
        if (abstractTriggerBlock instanceof CommandTriggerBlock) {
            return new ScriptCommandTriggerBlock((CommandTriggerBlock) abstractTriggerBlock);
        } else if (abstractTriggerBlock instanceof DialogueTriggerBlock) {
            return new ScriptDialogueTriggerBlock((DialogueTriggerBlock) abstractTriggerBlock);
        } else if (abstractTriggerBlock instanceof EventTriggerBlock) {
            return new ScriptEventTriggerBlock((EventTriggerBlock) abstractTriggerBlock);
        } else if (abstractTriggerBlock instanceof ItemTriggerBlock) {
            return new ScriptItemTriggerBlock((ItemTriggerBlock) abstractTriggerBlock);
        } else if (abstractTriggerBlock instanceof MorphTriggerBlock) {
            return new ScriptMorphTriggerBlock((MorphTriggerBlock) abstractTriggerBlock);
        } else if (abstractTriggerBlock instanceof mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock) {
            return new ScriptScriptTriggerBlock((mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock) abstractTriggerBlock);
        } else if (abstractTriggerBlock instanceof SoundTriggerBlock) {
            return new ScriptSoundTriggerBlock((SoundTriggerBlock) abstractTriggerBlock);
        } else if (abstractTriggerBlock instanceof StateTriggerBlock) {
            return new ScriptStateTriggerBlock((StateTriggerBlock) abstractTriggerBlock);
        } else {
            throw new IllegalArgumentException("Unknown abstract trigger block type!");
        }
    }
}
