package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.ItemConditionBlock;
import mchorse.mappet.api.scripts.code.items.ScriptItemStack;
import mchorse.mappet.api.scripts.user.items.IScriptItemStack;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;

public class ScriptItemConditionBlock extends ScriptTargetConditionBlock<ItemConditionBlock> {

    public IScriptItemStack getItemStack() {
        return ScriptItemStack.create(this.conditionBlock.stack);
    }

    public void setItemStack(IScriptItemStack stack) {
        this.conditionBlock.stack = stack.getMinecraftItemStack();
    }

    public String getCheckType() {
        return this.conditionBlock.check.name();
    }

    public void setCheckType(String type) {
        this.conditionBlock.check = ItemConditionBlock.ItemCheck.valueOf(type.toUpperCase());
    }

}