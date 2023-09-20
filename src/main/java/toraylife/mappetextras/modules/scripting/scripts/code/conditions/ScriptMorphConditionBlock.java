package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.MorphConditionBlock;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;

public class ScriptMorphConditionBlock extends ScriptTargetConditionBlock<MorphConditionBlock> {

    public boolean isOnlyName() {
        return this.conditionBlock.onlyName;
    }

    public void setOnlyName(boolean onlyName) {
        this.conditionBlock.onlyName = onlyName;
    }

    public AbstractMorph getMorph() {
        return MorphManager.INSTANCE.morphFromNBT(this.conditionBlock.morph);
    }

    public void setMorph(AbstractMorph morph) {
        this.conditionBlock.morph = morph.toNBT();
    }
}
