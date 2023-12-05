package toraylife.mappetextras.modules.client.scripts.user;

import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IScriptCameraShake {
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

    /**
     * Sets the scale.
     *
     * @param scale The scale factor
     */
    void setScale(double scale);

    /**
     * Gets the current scale.
     *
     * @return The scale factor
     */
    double getScale();

    /**
     * Sets the minus value.
     *
     * @param minus The minus value
     */
    void setMinus(double minus);

    /**
     * Gets the current minus value.
     *
     * @return The minus value
     */
    double getMinus();

    /**
     * Sets the plus value.
     *
     * @param plus The plus value
     */
    void setPlus(double plus);

    /**
     * Gets the current plus value.
     *
     * @return The plus value
     */
    double getPlus();
}
