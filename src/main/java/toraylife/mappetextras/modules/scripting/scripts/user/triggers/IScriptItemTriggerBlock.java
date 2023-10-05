package toraylife.mappetextras.modules.scripting.scripts.user.triggers;

import mchorse.mappet.api.triggers.blocks.ItemTriggerBlock;
import mchorse.mappet.api.utils.TargetMode;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.utils.IScriptTriggerBlock;

public interface IScriptItemTriggerBlock extends IScriptTriggerBlock<ItemTriggerBlock> {

    String getTarget();
    void setTarget(String target);

    String getMode();
    void setMode(String mode);
}
