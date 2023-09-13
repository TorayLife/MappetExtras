package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.scripts.code.nbt.ScriptNBTCompound;
import mchorse.mappet.api.scripts.user.nbt.INBTCompound;
import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import mchorse.mappet.api.triggers.blocks.SoundTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import mchorse.metamorph.api.Morph;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEStringTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPETriggerBlock;

public class MPEMorphTriggerBlock extends MPETriggerBlock<MorphTriggerBlock> {

    public AbstractMorph getMorph() {
        return MorphManager.INSTANCE.morphFromNBT(this.triggerBlock.morph);
    }

    public void setMorph(AbstractMorph morph) {
        this.triggerBlock.morph = morph.toNBT();
    }

    public String getTarget() {
        return this.triggerBlock.target.mode.name();
    }
    public void setTarget(String target) {
        this.triggerBlock.target.mode = TargetMode.valueOf(target.toUpperCase());
    }

    public String getSelector() {
        return this.triggerBlock.target.selector;
    }
    public void setSelector(String selector) {
        this.triggerBlock.target.selector = selector;
    }

    public MPEMorphTriggerBlock() {
        this(new MorphTriggerBlock());
    }

    public MPEMorphTriggerBlock(MorphTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
