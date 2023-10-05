package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.conditions.Checker;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptChecker;

public class ScriptChecker implements IScriptChecker {
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

    public String getExpression() {
        return this.checker.expression;
    }
    public void setExpression(String expression) {
        this.checker.expression = expression;
    }

    public ScriptCondition getCondition() {
        return new ScriptCondition(this.checker.condition);
    }
    public void setCondition(ScriptCondition condition) {
        this.checker.condition = condition.condition;
    }
}
