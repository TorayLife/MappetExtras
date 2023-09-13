package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.CommandTriggerBlock;
import mchorse.mappet.api.triggers.blocks.DialogueTriggerBlock;
import mchorse.mappet.api.triggers.blocks.EventTriggerBlock;
import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock;
import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.triggers.blocks.StateTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.MPECommandTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.MPEDialogueTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.MPEEventTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.MPEMorphTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.MPEScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.MPESoundTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.MPEStateTriggerBlock;

public class MPETriggerFactory {

    public MPETrigger createTrigger() {
        return this.createTrigger(new Trigger());
    }

    public MPETrigger createTrigger(Trigger mappetTrigger) {
        return new MPETrigger(mappetTrigger);
    }

    // Trigger blocks
    public MPECommandTriggerBlock createCommandTriggerBlock() {
        return new MPECommandTriggerBlock();
    }

    public MPECommandTriggerBlock createCommandTriggerBlock(CommandTriggerBlock triggerBlock) {
        return new MPECommandTriggerBlock(triggerBlock);
    }

    public MPEDialogueTriggerBlock createDialogueTriggerBlock() {
        return new MPEDialogueTriggerBlock();
    }

    public MPEDialogueTriggerBlock createDialogueTriggerBlock(DialogueTriggerBlock triggerBlock) {
        return new MPEDialogueTriggerBlock(triggerBlock);
    }

    public MPEEventTriggerBlock createEventTriggerBlock() {
        return new MPEEventTriggerBlock();
    }

    public MPEEventTriggerBlock createEventTriggerBlock(EventTriggerBlock triggerBlock) {
        return new MPEEventTriggerBlock(triggerBlock);
    }

    public MPEMorphTriggerBlock createMorphTriggerBlock() {
        return new MPEMorphTriggerBlock();
    }

    public MPEMorphTriggerBlock createMorphTriggerBlock(MorphTriggerBlock triggerBlock) {
        return new MPEMorphTriggerBlock(triggerBlock);
    }

    public MPEScriptTriggerBlock createScriptTriggerBlock() {
        return new MPEScriptTriggerBlock();
    }

    public MPEScriptTriggerBlock createScriptTriggerBlock(ScriptTriggerBlock triggerBlock) {
        return new MPEScriptTriggerBlock(triggerBlock);
    }

    public MPESoundTriggerBlock createSoundTriggerBlock() {
        return new MPESoundTriggerBlock();
    }

    public MPESoundTriggerBlock createSoundTriggerBlock(SoundTriggerBlock triggerBlock) {
        return new MPESoundTriggerBlock(triggerBlock);
    }

    public MPEStateTriggerBlock createStateTriggerBlock() {
        return new MPEStateTriggerBlock();
    }

    public MPEStateTriggerBlock createStateTriggerBlock(StateTriggerBlock triggerBlock) {
        return new MPEStateTriggerBlock(triggerBlock);
    }


}
