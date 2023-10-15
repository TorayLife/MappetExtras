package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.*;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.*;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptTriggerBlock;

public class ScriptTriggerFactory {

    public IScriptTrigger createTrigger() {
        return this.createTrigger(new Trigger());
    }

    public IScriptTrigger createTrigger(Trigger mappetTrigger) {
        return new ScriptTrigger(mappetTrigger);
    }

    // Trigger blocks
    public IScriptCommandTriggerBlock createCommandTriggerBlock() {
        return new ScriptCommandTriggerBlock();
    }

    public IScriptCommandTriggerBlock createCommandTriggerBlock(CommandTriggerBlock triggerBlock) {
        return new ScriptCommandTriggerBlock(triggerBlock);
    }

    public IScriptDialogueTriggerBlock createDialogueTriggerBlock() {
        return new ScriptDialogueTriggerBlock();
    }

    public IScriptDialogueTriggerBlock createDialogueTriggerBlock(DialogueTriggerBlock triggerBlock) {
        return new ScriptDialogueTriggerBlock(triggerBlock);
    }

    public IScriptEventTriggerBlock createEventTriggerBlock() {
        return new ScriptEventTriggerBlock();
    }

    public IScriptEventTriggerBlock createEventTriggerBlock(EventTriggerBlock triggerBlock) {
        return new ScriptEventTriggerBlock(triggerBlock);
    }

    public IScriptItemTriggerBlock createItemTriggerBlock() {
        return new ScriptItemTriggerBlock();
    }

    public IScriptItemTriggerBlock createItemTriggerBlock(ItemTriggerBlock triggerBlock) {
        return new ScriptItemTriggerBlock(triggerBlock);
    }

    public IScriptMorphTriggerBlock createMorphTriggerBlock() {
        return new ScriptMorphTriggerBlock();
    }

    public IScriptMorphTriggerBlock createMorphTriggerBlock(MorphTriggerBlock triggerBlock) {
        return new ScriptMorphTriggerBlock(triggerBlock);
    }

    public IScriptScriptTriggerBlock createScriptTriggerBlock() {
        return new ScriptScriptTriggerBlock();
    }

    public IScriptScriptTriggerBlock createScriptTriggerBlock(ScriptTriggerBlock triggerBlock) {
        return new ScriptScriptTriggerBlock(triggerBlock);
    }

    public IScriptSoundTriggerBlock createSoundTriggerBlock() {
        return new ScriptSoundTriggerBlock();
    }

    public IScriptSoundTriggerBlock createSoundTriggerBlock(SoundTriggerBlock triggerBlock) {
        return new ScriptSoundTriggerBlock(triggerBlock);
    }

    public IScriptStateTriggerBlock createStateTriggerBlock() {
        return new ScriptStateTriggerBlock();
    }

    public IScriptStateTriggerBlock createStateTriggerBlock(StateTriggerBlock triggerBlock) {
        return new ScriptStateTriggerBlock(triggerBlock);
    }

    public IScriptTriggerBlock<? extends AbstractTriggerBlock> createTriggerBlock(AbstractTriggerBlock abstractTriggerBlock) {
        return ScriptTrigger.createTriggerBlock(abstractTriggerBlock);
    }
}
