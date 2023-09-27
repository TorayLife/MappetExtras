package toraylife.mappetextras.modules.scripting.scripts.user.triggers;

import mchorse.mappet.api.triggers.blocks.ScriptTriggerBlock;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptDataTriggerBlock;

public interface IScriptScriptTriggerBlock extends IScriptDataTriggerBlock<ScriptTriggerBlock> {

    String getFunction();

    void setFunction(String function);

    String getInlineCode();

    void setInlineCode(String code);

    boolean isInline();

    void setInline(boolean inline);
}
