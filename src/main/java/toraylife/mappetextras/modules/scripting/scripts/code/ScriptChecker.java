package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.conditions.Checker;

public class ScriptChecker {
    public Checker checker;

    public ScriptChecker() {
        this(new Checker());
    }

    public ScriptChecker(Checker checker) {
        this.checker = checker;
    }

    public String getMode() {
        return this.checker.mode.name();
    }
    public void setMode(String mode) {
        this.checker.mode = Checker.Mode.valueOf(mode.toUpperCase());
    }
}
