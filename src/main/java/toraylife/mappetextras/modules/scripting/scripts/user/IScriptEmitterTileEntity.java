package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;

public interface IScriptEmitterTileEntity extends IScriptTileEntity {

    /**
     * Retrieves the script checker.
     */
    IScriptChecker getChecker();

    /**
     * Sets the given checker for emitter.
     */
    void setChecker(IScriptChecker checker);

    /**
     * Retrieves the radius in blocks.
     */
    float getRadius();

    /**
     * Sets the radius of the emitter.
     */
    void setRadius(float radius);

    /**
     * Retrieves the update frequency value.
     */
    int getUpdate();

    /**
     * Sets the update frequency value.
     */
    void setUpdate(int update);

    /**
     * Retrieves true, if "Auto-Disable" option is enabled.
     */
    boolean getDisable();

    /**
     * Sets the value of the "Auto-Disable" option.
     */
    void setDisable(boolean disable);
}
