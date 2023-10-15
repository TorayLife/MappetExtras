package toraylife.mappetextras.modules.scripting.scripts.user;

import mchorse.mappet.utils.ConditionModel;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptChecker;

public interface IScriptConditionModel {
    ConditionModel getConditionModel();

    AbstractMorph getMorph();

    void setMorph(AbstractMorph morph);

    IScriptChecker getChecker();

    void setChecker(IScriptChecker checker);
}
