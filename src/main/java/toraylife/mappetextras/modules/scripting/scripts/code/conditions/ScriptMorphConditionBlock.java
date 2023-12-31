package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.MorphConditionBlock;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptMorphConditionBlock;

public class ScriptMorphConditionBlock extends ScriptTargetConditionBlock<MorphConditionBlock> implements IScriptMorphConditionBlock {

    public ScriptMorphConditionBlock() {
        this(new MorphConditionBlock());
    }

    public ScriptMorphConditionBlock(MorphConditionBlock conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public boolean isOnlyName() {
        return this.getConditionBlock().onlyName;
    }

    public void setOnlyName(boolean onlyName) {
        this.getConditionBlock().onlyName = onlyName;
    }

    public AbstractMorph getMorph() {
        return MorphManager.INSTANCE.morphFromNBT(this.getConditionBlock().morph);
    }

    public void setMorph(AbstractMorph morph) {
        this.getConditionBlock().morph = morph.toNBT();
    }
}
