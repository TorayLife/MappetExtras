package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.MorphTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptMorphTriggerBlock;

public class ScriptMorphTriggerBlock extends ScriptTriggerBlock<MorphTriggerBlock> implements IScriptMorphTriggerBlock {

    public AbstractMorph getMorph() {
        return MorphManager.INSTANCE.morphFromNBT(this.getTriggerBlock().morph);
    }

    public void setMorph(AbstractMorph morph) {
        this.getTriggerBlock().morph = morph.toNBT();
    }

    public String getTarget() {
        return this.getTriggerBlock().target.mode.name();
    }
    public void setTarget(String target) {
        this.getTriggerBlock().target.mode = TargetMode.valueOf(target.toUpperCase());
    }

    public String getSelector() {
        return this.getTriggerBlock().target.selector;
    }
    public void setSelector(String selector) {
        this.getTriggerBlock().target.selector = selector;
    }

    public ScriptMorphTriggerBlock() {
        this(new MorphTriggerBlock());
    }

    public ScriptMorphTriggerBlock(MorphTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
