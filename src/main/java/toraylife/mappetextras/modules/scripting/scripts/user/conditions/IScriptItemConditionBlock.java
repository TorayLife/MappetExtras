package toraylife.mappetextras.modules.scripting.scripts.user.conditions;

import mchorse.mappet.api.conditions.blocks.ItemConditionBlock;
import mchorse.mappet.api.scripts.user.items.IScriptItemStack;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.utils.IScriptTargetConditionBlock;

public interface IScriptItemConditionBlock extends IScriptTargetConditionBlock<ItemConditionBlock> {
    IScriptItemStack getItemStack();

    void setItemStack(IScriptItemStack stack);

    String getCheckType();

    void setCheckType(String type);
}
