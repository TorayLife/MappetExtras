package toraylife.mappetextras.modules.scripting.mixins.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.ui.components.UIComponent;

public interface CallbackableComponent<T extends UIComponent> {

    T callback(ScriptObjectMirror consumer);

    ScriptObjectMirror getCallback();

    boolean hasCallback();
}
