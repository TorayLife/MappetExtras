package toraylife.mappetextras.modules.client.scripts.user.minecraft;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

/**
 * CameraShake.
 *
 * This interface represents shake camera
 *
 * It allows you to make shake, turn, "earthquake" by controlling the camera.
 *
 * <pre>{@code
 *     function main(c)
 *     {
 *         const shake = c.player.camera.shake;
 *
 *         shake.setActive(true)
 *         shake.setSpeed(0.1, 0.1)
 *         shake.setRotation(10)
 *         shake.setZoom(0)
 *         shake.setRotate(0.5, 0, 0, 1)
 *     }
 * }</pre>
 */
public interface IMinecraftCameraShake {
    /**
     * Sets if this component is active.
     *
     * @param active True if active, false if not
     */
    void setActive(boolean active);

    /**
     * Gets if this component is active.
     *
     * @return True if active, false if not
     */
    boolean isActive();

    /**
     * Sets the rotation of this component.
     *
     * @param angle Angle of rotation
     * @param x X axis direction
     * @param y Y axis direction
     * @param z Z axis direction
     */
    void setRotate(double angle, double x, double y, double z);

    /**
     * Gets the rotation as a ScriptVectorAngle.
     *
     * @return The rotation
     */
    ScriptVectorAngle getRotate();

    /**
     * Sets the rotation angle.
     *
     * @param rot The rotation angle
     */
    void setRotation(double rot);

    /**
     * Gets the rotation angle.
     *
     * @return The rotation angle
     */
    double getRotation();

    void setZoom(double zoom);

    double getZoom();

    void setSpeed(double minus, double plus);

    void setSpeed(double speed);

    ScriptVector getSpeed();

    void reset();
}
