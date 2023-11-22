package toraylife.mappetextras.modules.scripting.mixins.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.ui.components.UIComponent;

import java.util.HashMap;

public interface CallbackableComponent<T extends UIComponent> {

    T callback(ScriptObjectMirror consumer);

    T callback(String action, ScriptObjectMirror consumer);

    ScriptObjectMirror getCallback();

    HashMap<String, ScriptObjectMirror> getContextCallbacks();

    boolean hasCallback();

    boolean hasContextCallbacks();
}
