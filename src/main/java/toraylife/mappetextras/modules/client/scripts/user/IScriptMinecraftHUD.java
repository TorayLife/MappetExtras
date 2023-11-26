package toraylife.mappetextras.modules.client.scripts.user;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IScriptMinecraftHUD {
    /**
     * Moves the hud through the coordinates
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const minecraftHUD = c.player.getMinecraftHUD("FOOD");
     *
     *        minecraftHUD.setPosition(10, 0);
     *    }
     *  }</pre>
     */
    void setPosition(double x, double y);

    /**
     * Set scale for hud.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const minecraftHUD = c.player.getMinecraftHUD("FOOD");
     *
     *        minecraftHUD.setScale(10, 5);
     *    }
     *  }</pre>
     *
     * @param x the scale factor along the X axis
     * @param y the scale factor along the Y axis
     */
    void setScale(double x, double y);

    /**
     * Set rotation for hud.
     *
     * @param angle the angle of rotation in degrees
     * @param x coordinate of vector to rotate around
     * @param y coordinate of vector to rotate around
     * @param z coordinate of vector to rotate around
     */
    void setRotate(double angle, double x, double y, double z);

    /**
     * Enables or disables rendering of hud.
     *
     * @param render true to enable rendering, false to disable
     */
    void setRender(boolean render);

    /**
     * Return hud position as a {@link ScriptVector}.
     *
     * @return the current position {@link ScriptVector}
     */
    ScriptVector getPosition();

    /**
     * Get the current scale {@link ScriptVector}.
     *
     * @return a {@link ScriptVector} with current x, y scale factors
     */
    ScriptVector getScale();

    /**
     * Return current rotation as a {@link ScriptVectorAngle} object.
     *
     * @return the rotation {@link ScriptVectorAngle}
     */
    ScriptVectorAngle getRotate();

    /**
     * Check if hud rendering is enabled.
     *
     * @return true if rendering is on, false otherwise
     */
    public boolean isRender();

    void moveTo(String interpolation, int durationTicks, double x, double y);

    public String[] getAllHUDs();

    void rotateTo(String interpolation, int durationTicks, double angle, double x, double y, double z);
}
