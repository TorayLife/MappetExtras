package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

@Mixin(value = ScriptVector.class, remap = false)
@MixinTargetName("toraylife.mappetextras.modules.scripting.mixins.MixinScriptVector")
public abstract class MixinScriptVector {
    @Shadow
    public abstract ScriptVector multiply(double scalar);

    protected ScriptVector vector;

    public double dotProduct(ScriptVector vector3) {
        return this.vector.x * vector3.x + this.vector.y * vector3.y + this.vector.z * vector3.z;
    }

    public ScriptVector crossProduct(ScriptVector vector3) {
        return new ScriptVector(
                this.vector.y * vector3.z - this.vector.z * vector3.y,
                this.vector.z * vector3.x - this.vector.x * vector3.z,
                this.vector.x * vector3.y - this.vector.y * vector3.x
        );
    }

    public ScriptVector toAngle(ScriptVector vector3) {
        ScriptVector subtractVector = this.vector.subtract(vector3);

        double hypotenuse = Math.sqrt(Math.pow(subtractVector.x, 2) + Math.pow(subtractVector.z, 2));

        double pitch = Math.atan2(hypotenuse, subtractVector.y) * 180 / Math.PI;
        double yaw = -Math.atan2(subtractVector.x, subtractVector.z) * 180 / Math.PI;

        return new ScriptVector(pitch, yaw, 0);
    }

    public ScriptVector rotate(double pitch, double yaw) {
        ScriptVector normalizeVector = this.vector.normalize();

        double radiansPitch = Math.toRadians(-pitch);
        double radiansYaw = Math.toRadians(-yaw);

        double x = normalizeVector.x;
        double y = normalizeVector.y * Math.cos(radiansPitch) - normalizeVector.z * Math.sin(radiansPitch);
        double z = normalizeVector.y * Math.sin(radiansPitch) + normalizeVector.z * Math.cos(radiansPitch);

        x = x * Math.cos(radiansYaw) + z * Math.sin(radiansYaw);
        z = x * -Math.sin(radiansYaw) + z * Math.cos(radiansYaw);

        return new ScriptVector(x, y, z);
    }

    ScriptVector interpolation(ScriptVector vector3, double coefficient) {
        if (coefficient < 0 || coefficient > 1) {
            return null;
        }

        return new ScriptVector(
                this.vector.x + (vector3.x - this.vector.x) * coefficient,
                this.vector.y + (vector3.y - this.vector.y) * coefficient,
                this.vector.z + (vector3.z - this.vector.z) * coefficient
        );
    }

    public ScriptVector vectorMultiply(ScriptVector vector3) {
        return new ScriptVector(this.vector.x * vector3.x, this.vector.y * vector3.y, this.vector.z * vector3.z);
    }

    public ScriptVector divide(ScriptVector vector3) {
        return new ScriptVector(this.vector.x / vector3.x, this.vector.y / vector3.y, this.vector.z / vector3.z);
    }

    public ScriptVector copy() {
        return this.vector;
    }

    public boolean equals(ScriptVector vector3) {
        return (this.vector.x == vector3.x) && (this.vector.y == vector3.y) && (this.vector.z == vector3.z);
    }

    public double distance(ScriptVector vector3) {
        double dx = this.vector.x - vector3.x;
        double dy = this.vector.y - vector3.y;
        double dz = this.vector.z - vector3.z;

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
    }
}