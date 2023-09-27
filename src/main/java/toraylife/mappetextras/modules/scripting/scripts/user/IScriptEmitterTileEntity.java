package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptChecker;

public interface IScriptEmitterTileEntity extends IScriptTileEntity {
    ScriptChecker getChecker();

    void setChecker(ScriptChecker checker);

    float getRadius();

    void setRadius(float radius);

    int getUpdate();

    void setUpdate(int update);

    boolean getDisable();

    void setDisable(boolean disable);
}
