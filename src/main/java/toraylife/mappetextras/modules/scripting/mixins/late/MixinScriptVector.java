package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;

@Mixin(value = ScriptVector.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.data.ScriptVector")
public abstract class MixinScriptVector {
    @Shadow
    public double x;

    @Shadow
    public double y;

    @Shadow
    public double z;

    /**
     * Computes dot product with another vector. 
     *
     * @param vector The other vector
     * @return Dot product value
     */
    public double dotProduct(ScriptVector vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    /**
     * Computes cross product with another vector.
     *
     * @param vector The other vector
     * @return The cross product vector
     */
    public ScriptVector crossProduct(ScriptVector vector) {
        return new ScriptVector(
                this.y * vector.z - this.z * vector.y,
                this.z * vector.x - this.x * vector.z,
                this.x * vector.y - this.y * vector.x
        );
    }

    /**
     * Converts this vector to pitch and yaw angles to the given vector.
     *
     * @param vector The target vector
     * @return Vector containing pitch, yaw, 0
     */
    public ScriptVector toAngle(ScriptVector vector) {
        ScriptVector subtractVector = new ScriptVector(this.x, this.y, this.z).subtract(vector);

        double hypotenuse = Math.sqrt(Math.pow(subtractVector.x, 2) + Math.pow(subtractVector.z, 2));

        double pitch = Math.atan2(hypotenuse, subtractVector.y) * 180 / Math.PI;
        double yaw = -Math.atan2(subtractVector.x, subtractVector.z) * 180 / Math.PI;

        return new ScriptVector(pitch, yaw, 0);
    }

    /**
     * Rotates this vector by given pitch and yaw angles.
     *
     * @param pitch Pitch angle in degrees
     * @param yaw Yaw angle in degrees
     * @return The rotated vector
     */
    public ScriptVector rotate(double pitch, double yaw) {
        ScriptVector normalizeVector = new ScriptVector(this.x, this.y, this.z).normalize();

        double radiansPitch = Math.toRadians(-pitch);
        double radiansYaw = Math.toRadians(-yaw);

        double x = normalizeVector.x;
        double y = normalizeVector.y * Math.cos(radiansPitch) - normalizeVector.z * Math.sin(radiansPitch);
        double z = normalizeVector.y * Math.sin(radiansPitch) + normalizeVector.z * Math.cos(radiansPitch);

        x = x * Math.cos(radiansYaw) + z * Math.sin(radiansYaw);
        z = x * -Math.sin(radiansYaw) + z * Math.cos(radiansYaw);

        return new ScriptVector(x, y, z);
    }

    /**
     * Linearly interpolates between this and the given vector.
     *
     * @param vector Target vector
     * @param coefficient Interpolation coefficient
     * @return The interpolated vector
     */
    ScriptVector interpolation(ScriptVector vector, double coefficient) {
        if (coefficient < 0 || coefficient > 1) {
            return null;
        }

        return new ScriptVector(
                this.x + (vector.x - this.x) * coefficient,
                this.y + (vector.y - this.y) * coefficient,
                this.z + (vector.z - this.z) * coefficient
        );
    }

    /**
     * Multiplies this vector by components of given vector.
     *
     * @param vector Vector to multiply
     * @return Product vector
     */
    public ScriptVector vectorMultiply(ScriptVector vector) {
        return new ScriptVector(this.x * vector.x, this.y * vector.y, this.z * vector.z);
    }

    /**
     * Divides this vector by components of given vector.
     *
     * @param vector Vector to divide by
     * @return Result vector
     */
    public ScriptVector divide(ScriptVector vector) {
        return new ScriptVector(this.x / vector.x, this.y / vector.y, this.z / vector.z);
    }

    /**
     * Creates a copy of this vector.
     *
     * @return Copy of this vector
     */
    public ScriptVector copy() {
        return new ScriptVector(this.x, this.y, this.z);
    }

    /**
     * Checks if this vector equals another vector.
     *
     * @param vector The other vector
     * @return true if equal, false if not
     */
    public boolean equals(ScriptVector vector) {
        return (this.x == vector.x) && (this.y == vector.y) && (this.z == vector.z);
    }

    /**
     * Computes distance between this and another vector.
     *
     * @param vector The other vector
     * @return The distance
     */
    public double distance(ScriptVector vector) {
        double dx = this.x - vector.x;
        double dy = this.y - vector.y;
        double dz = this.z - vector.z;

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
    }
}