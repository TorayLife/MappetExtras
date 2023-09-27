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
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptCommandTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptDialogueTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptEventTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptMorphTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptSoundTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptStateTriggerBlock;

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
}
