package toraylife.mappetextras.modules.scripting.scripts.user.blocks;

import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.tile.TileEmitter;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptChecker;

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
    boolean isDisable();

    /**
     * Sets the value of the "Auto-Disable" option.
     */
    void setDisable(boolean disable);

    /**
     * Get Minecraft tile entity instance. <b>BEWARE:</b> you need to know the MCP
     * mappings in order to directly call methods on this instance!
     *
     * <p>See {@link IScriptTileEntity} for more usage info.</p>
     */
    @Override
    TileEmitter getMinecraftTileEntity();
}
