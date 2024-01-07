package toraylife.mappetextras.modules.scripting.scripts.user.triggers;

import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptTriggerBlock;

public interface IScriptMorphTriggerBlock extends IScriptTriggerBlock<MorphTriggerBlock> {

    AbstractMorph getMorph();

    void setMorph(AbstractMorph morph);

    String getTarget();
    void setTarget(String target);

    String getSelector();
    void setSelector(String selector);
}
