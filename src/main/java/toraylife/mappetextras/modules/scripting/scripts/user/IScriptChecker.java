package toraylife.mappetextras.modules.scripting.scripts.user;

import toraylife.mappetextras.modules.scripting.scripts.code.ScriptCondition;

public interface IScriptChecker {

    /**
     * Returns the mode of the checker.
     */
    String getMode();

    /**
     * Sets the mode of the checker.
     */
    void setMode(String mode);

    /**
     * Returns the expression of the checker.
     */
    String getExpression();

    /**
     * Sets the expression of the checker.
     */
    void setExpression(String expression);

    /**
     * Returns the condition of the checker.
     */
    ScriptCondition getCondition();

    /**
     * Sets the condition of the checker.
     */
    void setCondition(ScriptCondition condition);
}
