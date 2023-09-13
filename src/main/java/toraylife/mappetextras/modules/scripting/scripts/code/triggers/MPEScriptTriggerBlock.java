package toraylife.mappetextras.modules.scripting.scripts.code.triggers;

import mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock;
import scala.inline;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils.MPEDataTriggerBlock;

public class MPEScriptTriggerBlock extends MPEDataTriggerBlock<ScriptTriggerBlock> {

    public String getFunction() {
        return this.triggerBlock.function;
    }

    public void setFunction(String function) {
        this.triggerBlock.function = function;
    }

    public String getInlineCode() {
        return this.triggerBlock.code;
    }

    public void setInlineCode(String code) {
        this.triggerBlock.code = code;
    }

    public boolean isInline() {
        return this.triggerBlock.inline;
    }

    public void setInline(boolean inline) {
        this.triggerBlock.inline = inline;
    }

    public MPEScriptTriggerBlock() {
        this(new ScriptTriggerBlock());
    }

    public MPEScriptTriggerBlock(ScriptTriggerBlock triggerBlock) {
        this.triggerBlock = triggerBlock;
    }
}
