package toraylife.mappetextras.modules.scripting.scripts.user.blocks;

import mchorse.blockbuster.common.tileentity.TileEntityModel;
import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.metamorph.api.morphs.AbstractMorph;

public interface IScriptModelTileEntity extends IScriptTileEntity {

    /**
     * Set given morph to model block.
     */
    void setMorph(AbstractMorph morph);

    /**
     * Get {@link AbstractMorph} from model block.
     */
    AbstractMorph getMorph();

    /**
     * Gets the light level value of this model tile entity.
     *
     * @return The light level, from 0 to 15
     */
    int getLight();

    /**
     * Sets the light level value for this model tile entity.
     *
     * @param light The light level, from 0 to 15
     */
    void setLight(int light);

    /**
     * Checks if shadows are enabled for this model tile entity.
     *
     * @return True if shadows are enabled, false otherwise
     */
    boolean isShadow();

    /**
     * Sets whether shadows should be enabled for this model tile entity.
     *
     * @param shadow True to enable shadows, false to disable
     */
    void setShadow(boolean shadow);

    /**
     * Checks if this model tile entity is set to render globally.
     *
     * @return True if global rendering is enabled, false otherwise
     */
    boolean isGlobal();

    /**
     * Sets whether this model tile entity should render globally.
     *
     * @param global True to enable global rendering, false to restrict to nearby players
     */
    void setGlobal(boolean global);

    /**
     * Enables or disables this model tile entity.
     *
     * @param enabled True to enable, false to disable
     */
    void setEnabled(boolean enabled);

    /**
     * Checks if this model tile entity is currently enabled.
     *
     * @return True if enabled, false if disabled
     */
    boolean isEnabled();

    /**
     * Sets whether this model tile entity's rendering should be prevented
     * from resetting.
     *
     * @param preventReset True to prevent resets, false to allow
     */
    void setPreventReset(boolean preventReset);

    /**
     * Checks if this model tile entity is prevented from having its rendering reset.
     *
     * @return True if resetting is prevented, false if allowed
     */
    boolean isPreventReset();

    /**
     * Sets whether this model tile entity should be rendered last.
     *
     * @param renderLast True to render last, false otherwise
     */
    void setRenderLast(boolean renderLast);

    /**
     * Checks if this model tile entity is set to be rendered last.
     *
     * @return True if set to render last, false otherwise
     */
    boolean isRenderLast();

    /**
     * Sets whether this model tile entity should always be rendered.
     *
     * @param renderAlways True to always render, false otherwise
     */
    void setRenderAlways(boolean renderAlways);

    /**
     * Checks if this model tile entity is set to always render.
     *
     * @return True if set to always render, false otherwise
     */
    boolean isRenderAlways();

    /**
     * Sets whether this model tile entity's block hitbox is enabled.
     *
     * @param enabled True to enable the hitbox, false to disable
     */
    void setEnabledBlockHitbox(boolean enabled);

    /**
     * Checks if this model tile entity's block hitbox is enabled.
     *
     * @return True if the hitbox is enabled, false if disabled
     */
    boolean isEnabledBlockHitbox();

    /**
     * Get Minecraft tile entity instance. <b>BEWARE:</b> you need to know the MCP
     * mappings in order to directly call methods on this instance!
     *
     * <p>See {@link IScriptTileEntity} for more usage info.</p>
     */
    @Override
    TileEntityModel getMinecraftTileEntity();

    /**
     * Used to send updated tile entity data to all clients.
     */
    void sendToAll();
}
