package toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils;

import mchorse.mappet.api.triggers.blocks.StringTriggerBlock;

public interface IScriptStringTriggerBlock<T extends StringTriggerBlock> extends IScriptTriggerBlock<T> {

    String getString();

    void setString(String string);
}
