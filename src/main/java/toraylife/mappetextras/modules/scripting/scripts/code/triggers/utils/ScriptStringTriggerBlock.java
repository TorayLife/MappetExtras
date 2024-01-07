package toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils;

import mchorse.mappet.api.triggers.blocks.StringTriggerBlock;

public abstract class ScriptStringTriggerBlock<T extends StringTriggerBlock> extends ScriptTriggerBlock<T> {
    public String getString() {
        return this.getTriggerBlock().string;
    }

    public void setString(String string) {
        this.getTriggerBlock().string = string;
    }
}
