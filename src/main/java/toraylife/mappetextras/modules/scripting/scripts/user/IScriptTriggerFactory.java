package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.CommandTriggerBlock;
import mchorse.mappet.api.triggers.blocks.DialogueTriggerBlock;
import mchorse.mappet.api.triggers.blocks.EventTriggerBlock;
import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock;
import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.triggers.blocks.StateTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptCommandTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptDialogueTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptEventTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptMorphTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptSoundTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptStateTriggerBlock;

public interface IScriptTriggerFactory {

    IScriptTrigger createTrigger();

    IScriptTrigger createTrigger(Trigger mappetTrigger) ;

    // Trigger blocks
    IScriptCommandTriggerBlock createCommandTriggerBlock();

    IScriptCommandTriggerBlock createCommandTriggerBlock(CommandTriggerBlock triggerBlock);

    IScriptDialogueTriggerBlock createDialogueTriggerBlock();

    IScriptDialogueTriggerBlock createDialogueTriggerBlock(DialogueTriggerBlock triggerBlock);

    IScriptEventTriggerBlock createEventTriggerBlock();

    IScriptEventTriggerBlock createEventTriggerBlock(EventTriggerBlock triggerBlock);

    IScriptMorphTriggerBlock createMorphTriggerBlock();

    IScriptMorphTriggerBlock createMorphTriggerBlock(MorphTriggerBlock triggerBlock);

    IScriptScriptTriggerBlock createScriptTriggerBlock();

    IScriptScriptTriggerBlock createScriptTriggerBlock(ScriptTriggerBlock triggerBlock);

    IScriptSoundTriggerBlock createSoundTriggerBlock();

    IScriptSoundTriggerBlock createSoundTriggerBlock(SoundTriggerBlock triggerBlock);

    IScriptStateTriggerBlock createStateTriggerBlock();

    IScriptStateTriggerBlock createStateTriggerBlock(StateTriggerBlock triggerBlock);
}
