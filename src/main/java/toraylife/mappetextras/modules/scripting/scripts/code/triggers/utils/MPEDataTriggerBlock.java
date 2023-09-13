package toraylife.mappetextras.modules.scripting.scripts.code.triggers.utils;

import mchorse.mappet.api.triggers.blocks.DataTriggerBlock;

public abstract class MPEDataTriggerBlock<T extends DataTriggerBlock> extends MPEStringTriggerBlock<T> {

    public String getData() {
        return this.triggerBlock.customData;
    }

    public void setData(String string) {
        this.triggerBlock.customData = string;
    }
}
