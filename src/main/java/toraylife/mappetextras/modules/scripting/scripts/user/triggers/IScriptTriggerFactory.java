package toraylife.mappetextras.modules.scripting.scripts.user.triggers;

import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.triggers.blocks.*;

/**
 * Using for creating {@link IScriptScriptTriggerBlock} objects.
 *
 * <pre>{@code
 * function main(c) {
 *     var tile = c.world.getTileEntity(61, 80, -141);
 *     var triggerFactory = mappet.getTriggerFactory();
 *     var trigger = triggerFactory.createTrigger();
 *
 *     var block = triggerFactory.createCommandTriggerBlock();
 *     block.setString("/say 1");
 *     trigger.add(block);
 *
 *     var eventBlock = triggerFactory.createEventTriggerBlock();
 *     eventBlock.setString("ab")
 *     trigger.add(eventBlock)
 *
 *     var scriptBlock = triggerFactory.createScriptTriggerBlock();
 *     scriptBlock.setInline(true)
 *     scriptBlock.setInlineCode(test + "test(c)")
 *     trigger.add(scriptBlock)
 *
 *     tile.setRight(trigger)
 * }
 *
 * function test(c) {
 *     c.send(c.subject.name + " Гжегож Бженчешчикевич!")
 * }
 * }</pre>
 *
 * <p>You can also create trigger blocks from mappet objects. ({@link IScriptTrigger#getBlocks()})</p>
 *
 * <pre>{@code
 * function main(c) {
 *     var tile = c.world.getTileEntity(61, 80, -141);
 *     var triggerFactory = mappet.getTriggerFactory();
 *     var trigger = tile.getLeft();
 *     var commandBlock = triggerFactory.createCommandTriggerBlock(trigger.getBlocks()[0]);
 *     commandBlock.setString("/say try again");
 *     trigger.remove(0);
 *     trigger.add(commandBlock);
 *     tile.setLeft(trigger);
 * }
 * }</pre>
 */
public interface IScriptTriggerFactory {

    /**
     * Creates an instance of the IScriptTrigger.
     */
    IScriptTrigger createTrigger();

    /**
     * Creates a new IScriptTrigger based on the given mappet Trigger object (NOT A {@link IScriptTrigger}).
     */
    IScriptTrigger createTrigger(Trigger mappetTrigger);

    /**
     * Creates a new {@link IScriptCommandTriggerBlock}.
     */
    IScriptCommandTriggerBlock createCommandTriggerBlock();

    /**
     * Creates a new {@link IScriptCommandTriggerBlock} based on the given mappet {@link CommandTriggerBlock} object.
     */
    IScriptCommandTriggerBlock createCommandTriggerBlock(CommandTriggerBlock triggerBlock);

    /**
     * Creates a new {@link IScriptDialogueTriggerBlock}.
     */
    IScriptDialogueTriggerBlock createDialogueTriggerBlock();

    /**
     * Creates a new {@link IScriptDialogueTriggerBlock} based on the given mappet {@link DialogueTriggerBlock} object.
     */
    IScriptDialogueTriggerBlock createDialogueTriggerBlock(DialogueTriggerBlock triggerBlock);

    /**
     * Creates a new {@link IScriptEventTriggerBlock}.
     */
    IScriptEventTriggerBlock createEventTriggerBlock();

    /**
     * Creates a new {@link IScriptEventTriggerBlock} based on the given mappet {@link EventTriggerBlock} object.
     */
    IScriptEventTriggerBlock createEventTriggerBlock(EventTriggerBlock triggerBlock);

    /**
     * Creates a new {@link IScriptMorphTriggerBlock}.
     */
    IScriptMorphTriggerBlock createMorphTriggerBlock();

    /**
     * Creates a new {@link IScriptMorphTriggerBlock} based on the given mappet {@link MorphTriggerBlock} object.
     */
    IScriptMorphTriggerBlock createMorphTriggerBlock(MorphTriggerBlock triggerBlock);

    /**
     * Creates a new {@link IScriptScriptTriggerBlock}.
     */
    IScriptScriptTriggerBlock createScriptTriggerBlock();

    /**
     * Creates a new {@link IScriptScriptTriggerBlock} based on the given mappet {@link ScriptTriggerBlock} object.
     */
    IScriptScriptTriggerBlock createScriptTriggerBlock(ScriptTriggerBlock triggerBlock);

    /**
     * Creates a new {@link IScriptSoundTriggerBlock}.
     */
    IScriptSoundTriggerBlock createSoundTriggerBlock();

    /**
     * Creates a new {@link IScriptSoundTriggerBlock} based on the given mappet {@link SoundTriggerBlock} object.
     */
    IScriptSoundTriggerBlock createSoundTriggerBlock(SoundTriggerBlock triggerBlock);

    /**
     * Creates a new {@link IScriptStateTriggerBlock}.
     */
    IScriptStateTriggerBlock createStateTriggerBlock();

    /**
     * Creates a new {@link IScriptStateTriggerBlock} based on the given mappet {@link StateTriggerBlock} object.
     */
    IScriptStateTriggerBlock createStateTriggerBlock(StateTriggerBlock triggerBlock);
}
