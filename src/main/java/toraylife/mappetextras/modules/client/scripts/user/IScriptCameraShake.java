package toraylife.mappetextras.modules.client.scripts.user;

import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

public interface IScriptCameraShake {
    //ACTIVE
    void setActive(boolean active);
    boolean isActive();

    //ROTATE
    void setRotate(double angle, double x, double y, double z);
    ScriptVectorAngle getRotate();

    //ROTATION
    void setRotation(double rot);
    double getRotation();

    //SCALE
    void setScale(double scale);
    double getScale();

    //MINUS
    void setMinus(double minus);
    double getMinus();

    //PLUS
    void setPlus(double plus);
    double getPlus();
}
