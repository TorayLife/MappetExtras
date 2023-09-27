package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTrigger;

public interface IScriptTriggerTileEntity extends IScriptTileEntity {

    IScriptTrigger getLeft();

    void setLeft(ScriptTrigger trigger);

    IScriptTrigger getRight();

    void setRight(ScriptTrigger trigger);
}
