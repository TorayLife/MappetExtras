package toraylife.mappetextras.modules.scripting.mixins.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.scripts.user.IScriptEvent;

public interface UIContextWithCallback {

    void registerCallback(String name, ScriptObjectMirror callback);

    ScriptObjectMirror getCallback(String name);

    void handleCallbacks(IScriptEvent event);
}
