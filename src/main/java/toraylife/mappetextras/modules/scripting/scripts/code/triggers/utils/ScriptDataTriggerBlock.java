package toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils;

import mchorse.mappet.api.triggers.blocks.DataTriggerBlock;

public abstract class ScriptDataTriggerBlock<T extends DataTriggerBlock> extends ScriptStringTriggerBlock<T> {

    public String getData() {
        return this.getTriggerBlock().customData;
    }

    public void setData(String string) {
        this.getTriggerBlock().customData = string;
    }
}
