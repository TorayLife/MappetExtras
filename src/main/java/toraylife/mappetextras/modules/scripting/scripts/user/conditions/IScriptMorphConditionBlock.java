package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.blocks.MorphConditionBlock;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptTargetConditionBlock;

public interface IScriptMorphConditionBlock extends IScriptTargetConditionBlock<MorphConditionBlock> {

    boolean isOnlyName();

    void setOnlyName(boolean onlyName);

    AbstractMorph getMorph();

    void setMorph(AbstractMorph morph);
}
