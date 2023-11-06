package toraylife.mappetextras.modules.utils.client;

import jdk.nashorn.api.scripting.AbstractJSObject;

public class BeautifierOptions extends AbstractJSObject {

    public boolean indent_empty_lines;
    public boolean unindent_chained_methods;
    public boolean break_chained_methods;


    static final BeautifierOptions DEFAULT_BEAUTIFIER_OPTIONS = new BeautifierOptions(false, false, false);

    public BeautifierOptions(boolean indent_empty_lines, boolean unindent_chained_methods, boolean break_chained_methods) {
        this.indent_empty_lines = indent_empty_lines;
        this.unindent_chained_methods = unindent_chained_methods;
        this.break_chained_methods = break_chained_methods;
    }

    @Override
    public String toString() {
        return "{" +
                "indent_empty_lines:" + indent_empty_lines +
                ", unindent_chained_methods:" + unindent_chained_methods +
                ", break_chained_methods:" + break_chained_methods +
                '}';
    }
}
