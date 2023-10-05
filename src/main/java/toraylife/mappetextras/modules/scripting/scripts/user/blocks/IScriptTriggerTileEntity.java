package toraylife.mappetextras.modules.scripting.scripts.user.blocks;

import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.tile.TileTrigger;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptTrigger;

public interface IScriptTriggerTileEntity extends IScriptTileEntity {

    /**
     * Returns trigger for left click.
     */
    IScriptTrigger getLeft();

    /**
     * Sets trigger for left click.
     */
    void setLeft(ScriptTrigger trigger);

    /**
     * Returns trigger for right click.
     */
    IScriptTrigger getRight();

    /**
     * Sets trigger for right click.
     */
    void setRight(ScriptTrigger trigger);

    /**
     * Returns a boolean value indicating whether the trigger is collidable.
     *
     * @return true if the object is collidable, false otherwise
     */
    boolean isCollidable();

    /**
     * Sets whether the object is collidable or not.
     *
     * @param collidable true if the object is collidable, false otherwise
     */
    void setCollidable(boolean collidable);

    /**
     * Get Minecraft tile entity instance. <b>BEWARE:</b> you need to know the MCP
     * mappings in order to directly call methods on this instance!
     *
     * <p>See {@link IScriptTileEntity} for more usage info.</p>
     */
    @Override
    TileTrigger getMinecraftTileEntity();
}
