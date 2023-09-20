package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.CommandTriggerBlock;
import mchorse.mappet.api.triggers.blocks.DialogueTriggerBlock;
import mchorse.mappet.api.triggers.blocks.EventTriggerBlock;
import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock;
import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.triggers.blocks.StateTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptCommandTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptDialogueTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptEventTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptMorphTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptSoundTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptStateTriggerBlock;

public class ScriptTriggerFactory {

    public ScriptTrigger createTrigger() {
        return this.createTrigger(new Trigger());
    }

    public ScriptTrigger createTrigger(Trigger mappetTrigger) {
        return new ScriptTrigger(mappetTrigger);
    }

    // Trigger blocks
    public ScriptCommandTriggerBlock createCommandTriggerBlock() {
        return new ScriptCommandTriggerBlock();
    }

    public ScriptCommandTriggerBlock createCommandTriggerBlock(CommandTriggerBlock triggerBlock) {
        return new ScriptCommandTriggerBlock(triggerBlock);
    }

    public ScriptDialogueTriggerBlock createDialogueTriggerBlock() {
        return new ScriptDialogueTriggerBlock();
    }

    public ScriptDialogueTriggerBlock createDialogueTriggerBlock(DialogueTriggerBlock triggerBlock) {
        return new ScriptDialogueTriggerBlock(triggerBlock);
    }

    public ScriptEventTriggerBlock createEventTriggerBlock() {
        return new ScriptEventTriggerBlock();
    }

    public ScriptEventTriggerBlock createEventTriggerBlock(EventTriggerBlock triggerBlock) {
        return new ScriptEventTriggerBlock(triggerBlock);
    }

    public ScriptMorphTriggerBlock createMorphTriggerBlock() {
        return new ScriptMorphTriggerBlock();
    }

    public ScriptMorphTriggerBlock createMorphTriggerBlock(MorphTriggerBlock triggerBlock) {
        return new ScriptMorphTriggerBlock(triggerBlock);
    }

    public ScriptScriptTriggerBlock createScriptTriggerBlock() {
        return new ScriptScriptTriggerBlock();
    }

    public ScriptScriptTriggerBlock createScriptTriggerBlock(ScriptTriggerBlock triggerBlock) {
        return new ScriptScriptTriggerBlock(triggerBlock);
    }

    public ScriptSoundTriggerBlock createSoundTriggerBlock() {
        return new ScriptSoundTriggerBlock();
    }

    public ScriptSoundTriggerBlock createSoundTriggerBlock(SoundTriggerBlock triggerBlock) {
        return new ScriptSoundTriggerBlock(triggerBlock);
    }

    public ScriptStateTriggerBlock createStateTriggerBlock() {
        return new ScriptStateTriggerBlock();
    }

    public ScriptStateTriggerBlock createStateTriggerBlock(StateTriggerBlock triggerBlock) {
        return new ScriptStateTriggerBlock(triggerBlock);
    }


}
