package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.utils.ConditionModel;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptChecker;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptConditionModel;

public class ScriptConditionModel implements IScriptConditionModel {

    public ConditionModel conditionModel;

    public ScriptConditionModel(ConditionModel conditionModel) {
        this.conditionModel = conditionModel;
    }

    @Override
    public ConditionModel getConditionModel() {
        return this.conditionModel;
    }

    @Override
    public AbstractMorph getMorph() {
        return this.conditionModel.morph;
    }

    @Override
    public void setMorph(AbstractMorph morph) {
        this.conditionModel.morph = morph;
    }

    @Override
    public IScriptChecker getChecker() {
        return new ScriptChecker(this.conditionModel.checker);
    }

    @Override
    public void setChecker(IScriptChecker checker) {
        this.conditionModel.checker = ((ScriptChecker) checker).checker;
    }
}
