package toraylife.mappetextras.modules.scripting.scripts.user;

import toraylife.mappetextras.modules.scripting.scripts.code.ScriptCondition;

public interface IScriptChecker {

    String getMode();

    void setMode(String mode);

    String getExpression();

    void setExpression(String expression);

    ScriptCondition getCondition();

    void setCondition(ScriptCondition condition);
}
