package toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils;

import mchorse.mappet.api.triggers.blocks.DataTriggerBlock;

public interface IScriptDataTriggerBlock<T extends DataTriggerBlock> extends IScriptStringTriggerBlock<T> {

    String getData();

    void setData(String string);
}
