package toraylife.mappetextras.modules.scripting.utils;

import javax.vecmath.Vector4d;

public class ScriptVectorAngle {
    public double x;
    public double y;
    public double z;
    public double angle;

    public ScriptVectorAngle(double angle, double x, double y, double z){
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ScriptVectorAngle(Vector4d vector){
        this.angle = vector.w;
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public String toString() {
        return "ScriptVector(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public String toArrayString() {
        return "[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
}
