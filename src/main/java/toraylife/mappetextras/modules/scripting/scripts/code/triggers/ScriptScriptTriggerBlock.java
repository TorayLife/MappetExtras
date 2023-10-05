package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.ScriptDataTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptScriptTriggerBlock;

public class ScriptScriptTriggerBlock extends ScriptDataTriggerBlock<ScriptTriggerBlock> implements IScriptScriptTriggerBlock {

    public String getFunction() {
        return this.getTriggerBlock().function;
    }

    public void setFunction(String function) {
        this.getTriggerBlock().function = function;
    }

    public String getInlineCode() {
        return this.getTriggerBlock().code;
    }

    public void setInlineCode(String code) {
        this.getTriggerBlock().code = code;
    }

    public boolean isInline() {
        return this.getTriggerBlock().inline;
    }

    public void setInline(boolean inline) {
        this.getTriggerBlock().inline = inline;
    }

    public ScriptScriptTriggerBlock() {
        this(new ScriptTriggerBlock());
    }

    public ScriptScriptTriggerBlock(ScriptTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
