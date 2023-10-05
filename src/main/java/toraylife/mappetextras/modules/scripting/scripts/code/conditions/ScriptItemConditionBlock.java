package toraylife.mappetextras.modules.scripting.scripts.code.conditions;

import mchorse.mappet.api.conditions.blocks.ItemConditionBlock;
import mchorse.mappet.api.scripts.code.items.ScriptItemStack;
import mchorse.mappet.api.scripts.user.items.IScriptItemStack;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.utils.ScriptTargetConditionBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptItemConditionBlock;

public class ScriptItemConditionBlock extends ScriptTargetConditionBlock<ItemConditionBlock> implements IScriptItemConditionBlock {

    public ScriptItemConditionBlock() {
        this(new ItemConditionBlock());
    }

    public ScriptItemConditionBlock(ItemConditionBlock conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public IScriptItemStack getItemStack() {
        return ScriptItemStack.create(this.getConditionBlock().stack);
    }

    public void setItemStack(IScriptItemStack stack) {
        this.getConditionBlock().stack = stack.getMinecraftItemStack();
    }

    public String getCheckType() {
        return this.getConditionBlock().check.name();
    }

    public void setCheckType(String type) {
        this.getConditionBlock().check = ItemConditionBlock.ItemCheck.valueOf(type.toUpperCase());
    }

}
