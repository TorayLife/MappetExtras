package toraylife.mappetextras.modules.utils.client;

import mchorse.mappet.utils.ScriptUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Beautifier {

    public ScriptEngine engine;

    public Beautifier() {
        try {
            this.engine = ScriptUtils.getEngineByExtension("js");
            // this is needed to make self invoking function modules work
            // otherwise you won't be able to invoke your function
            engine.eval("var global = this;");
            engine.eval(new InputStreamReader(Objects.requireNonNull(Beautifier.class.getResourceAsStream("/assets/mappetextras/jslibraries/beautify.js"))));
        } catch (Exception ignored) {
        }
    }

    public String beautify(String javascriptCode, BeautifierOptions beautifierOptions) throws ScriptException, NoSuchMethodException {
        engine.eval("var beautifierOptions = " + beautifierOptions.toString());
        return (String) ((Invocable) engine).invokeFunction("js_beautify", javascriptCode, engine.get("beautifierOptions"));
    }

    public static BeautifierOptions getDefaultOptions() {
        return BeautifierOptions.DEFAULT_BEAUTIFIER_OPTIONS;
    }

    public static BeautifierOptions getOptions(boolean indent_empty_lines, boolean unindent_chained_methods, boolean break_chained_methods) {
        return new BeautifierOptions(indent_empty_lines, unindent_chained_methods, break_chained_methods);
    }
}

