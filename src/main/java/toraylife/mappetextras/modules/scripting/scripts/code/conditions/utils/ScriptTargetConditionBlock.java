package toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils;

import mchorse.mappet.api.conditions.blocks.TargetConditionBlock;
import mchorse.mappet.api.utils.TargetMode;

public abstract class ScriptTargetConditionBlock<T extends TargetConditionBlock> extends ScriptConditionBlock<T> {


    public String getId() {
        return this.conditionBlock.id;
    }

    public void setId(String id) {
        this.conditionBlock.id = id;
    }

    public String getTarget() {
        return this.conditionBlock.target.mode.name();
    }

    public void setTarget(String target) {
        this.conditionBlock.target.mode = TargetMode.valueOf(target.toUpperCase());
    }
}
