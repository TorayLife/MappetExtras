package toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils;

import mchorse.mappet.api.conditions.blocks.TargetConditionBlock;

public interface IScriptTargetConditionBlock<T extends TargetConditionBlock> extends IScriptConditionBlock<T> {
    String getId();

    void setId(String id);

    String getTarget();

    void setTarget(String target);
}
