package toraylife.mappetextras.modules.client.scripts.user.minecraft;

import mchorse.mappet.api.scripts.code.entities.ScriptEntity;

public interface IMinecraftCamera {
    /**
     * Get the camera shake controller for this player.
     * Can be used to shake the player's camera.
     *
     * @return The ScriptCameraShake instance
     */
    IMinecraftCameraShake getShake();

    /**
     * Sets the yaw rotation of this script.
     *
     * @param yaw the yaw in degrees
     */
    void setYaw(float yaw);

    /**
     * Gets the yaw rotation of this script.
     *
     * @return the yaw in degrees
     */
    float getYaw();

    /**
     * Sets the pitch rotation of this script.
     *
     * @param pitch the pitch in degrees
     */
    void setPitch(float pitch);

    /**
     * Gets the pitch rotation of this script.
     *
     * @return the pitch in degrees
     */
    float getPitch();

    /**
     * Sets the roll rotation of this script.
     *
     * @param roll the roll in degrees
     */
    void setRoll(float roll);

    /**
     * Gets the roll rotation of this script.
     *
     * @return the roll in degrees
     */
    float getRoll();

    /**
     * Sets whether this script is enabled.
     *
     * @param enabled true if enabled, false if disabled
     */
    void setEnabled(boolean enabled);

    /**
     * Checks if this script is enabled.
     *
     * @return true if enabled, false if disabled
     */
    boolean isEnabled();

    /**
     * Sets the entity to render this script with.
     *
     * @param entity the entity to render with
     */
    void setRenderWithEntity(ScriptEntity entity);

    /**
     * Rotates arm to target orientation.
     *
     * @param interpolation name of interpolation function
     * @param durationTicks duration of rotation in ticks
     */
    void rotateTo(String interpolation, int durationTicks, double pitch, double yaw, double roll);

    /**
     * Set all values to default
     */
    void reset();
}
