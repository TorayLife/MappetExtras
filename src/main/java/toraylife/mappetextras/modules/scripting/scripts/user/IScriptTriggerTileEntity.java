package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTrigger;

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
    boolean getCollidable();

    /**
     * Sets whether the object is collidable or not.
     *
     * @param collidable true if the object is collidable, false otherwise
     */
    void setCollidable(boolean collidable);
}
