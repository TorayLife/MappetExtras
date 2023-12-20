package toraylife.mappetextras.modules.client.scripts.user.minecraft;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IMinecraftArmRender {
    /**
     * Rotates the arm (main or off). angle - angle in degrees (positive angle = counterclockwise rotation), and x, y and z - vector around which the rotation is performed.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *        armRender.setRotate(-90, 0, 1, 0); // Rotate 90 degrees clockwise around the vertical axis
     *    }
     *  }</pre>
     */
    void setRotate(double angle, double x, double y, double z);
    /**
     * Return rotate arm.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *        c.send(armRender.angle); // returns angle arm
     *        c.send(armRender.x); // returns x arm
     *        c.send(armRender.y); // returns y arm
     *        c.send(armRender.z); // returns z arm
     *    }
     *  }</pre>
     */
    ScriptVectorAngle getRotate();

    /**
     * Moves the arm through the coordinates
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *        armRender.setPosition(0.3, 0, 0);
     *    }
     *  }</pre>
     */
    void setPosition(double x, double y, double z);

    /**
     * Sets the pitch, yaw, and yawHead rotations.
     *
     * @param pitch the pitch in degrees
     * @param yaw the yaw in degrees
     * @param yawHead the head yaw in degrees
     */
    void setRotations(float pitch, float yaw, float yawHead);

    ScriptVector getRotations();

    /**
     * Moves the arm through the coordinates
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *        armRender.setPosition(mappet.vector(0.3, 0, 0));
     *    }
     *  }</pre>
     */
    void setPosition(ScriptVector pos);
    /**
     * Return coordinates arm
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *        const position = armRender.getPosition();
     *
     *        c.send("x: "+position.x+", y:"+position.y+", z:"+position.z) // coordinates
     *    }
     *  }</pre>
     *  /**
     */
    ScriptVector getPosition();

    /**
     * Returns whether hand rendering is enabled
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *        c.send(armRender.isRender()) // render: boolean
     *    }
     *  }</pre>
     */
    boolean isRender();
    /**
     * Enable arm rendering
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *        armRender.setRender(false)
     *    }
     *  }</pre>
     */
    void setRender(boolean render);

    /**
     * Moves arm to target position.
     *
     * @param interpolation name of interpolation function to use
     * @param durationTicks duration of movement in ticks
     * @param x target X coordinate
     * @param y target Y coordinate
     * @param z target Z coordinate
     */
    void moveTo(String interpolation, int durationTicks, double x, double y, double z);

    /**
     * Rotates arm to target orientation.
     *
     * @param interpolation name of interpolation function
     * @param durationTicks duration of rotation in ticks
     * @param angle target rotation angle in degrees
     * @param x x coordinate of rotation axis vector
     * @param y y coordinate of rotation axis vector
     * @param z z coordinate of rotation axis vector
     */
    void rotateTo(String interpolation, int durationTicks, double angle, double x, double y, double z);

    void reset();
}