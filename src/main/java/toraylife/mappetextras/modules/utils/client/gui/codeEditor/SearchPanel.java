package toraylife.mappetextras.modules.utils.client.gui.codeEditor;

import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mappet.client.gui.utils.text.undo.TextEditUndo;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiToggleElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTextElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiContext;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.modules.utils.MPEIcons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPanel extends GuiElement {

    public GuiTextElement search;

    public GuiIconElement searchIcon;
    public GuiTextElement replace;
    public GuiIconElement replaceIcon;


    public String searchString;
    public String replaceString;

    public GuiTextEditor code;

    public boolean regex = false;
    public boolean replaceAll = false;
    public boolean ignoreCase = false;

    public GuiToggleElement toggleRegex;
    public GuiToggleElement toggleReplaceAll;
    public GuiToggleElement toggleIgnoreCase;

    public SearchPanel(Minecraft mc, GuiTextEditor code) {
        super(mc);

        this.code = code;

        GuiIconElement closeIcon = new GuiIconElement(mc, Icons.CLOSE, (b -> this.toggleSearch()));
        closeIcon.flex().relative(this).anchor(1F, 0F).x(1F, -5).y(5);
        closeIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.close"));

        GuiIconElement regexIcon = new GuiIconElement(mc, MPEIcons.EMOJI_STUCKOUTTONGUE, (b) -> {
            this.toggleRegexIcon(b);
            this.toggleRegex.toggled(!this.toggleRegex.isToggled());
        }).iconColor(0xFF888888).hoverColor(0xFF888888).hovered(Icons.GRAPH);
        regexIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.regex"));

        this.toggleReplaceAll = new GuiToggleElement(mc, IKey.lang("mappetextras.utils.codesearch.replace_all"), (b) -> {
            this.replaceAll = b.isToggled();
        });
        this.toggleReplaceAll.flex().h(20);

        this.toggleRegex = new GuiToggleElement(mc, IKey.lang("mappetextras.utils.codesearch.regex"), (b) -> {
            this.toggleRegexIcon(regexIcon);
            this.regex = b.isToggled();
        });
        this.toggleRegex.flex().h(20);

        this.toggleIgnoreCase = new GuiToggleElement(mc, IKey.lang("mappetextras.utils.codesearch.ignore_case"), (b) -> {
            this.ignoreCase = b.isToggled();
        });
        this.toggleIgnoreCase.flex().h(20);


        GuiElement rowIcons = Elements.row(mc, 2, Elements.column(mc, 2, Elements.row(mc, 2, regexIcon, this.toggleRegex), toggleReplaceAll, toggleIgnoreCase), closeIcon);


        this.search = new GuiTextElement(mc, s -> this.searchString = s);

        this.searchIcon = new GuiIconElement(mc, Icons.SEARCH, (b -> search(this.code)));
        searchIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.search"));

        GuiElement searchLabel = Elements.label(IKey.lang("mappetextras.utils.codesearch.search")).marginTop(6);
        searchLabel.flex().relative(this).w(0.25F);

        GuiElement rowSearch = Elements.row(mc, 2, searchLabel, this.search, this.searchIcon);
        rowSearch.flex().relative(this).x(5).y(0.33F, 5).w(1F, -10).h(0.33F, -10);

        this.replace = new GuiTextElement(mc, s -> this.replaceString = s);

        this.replaceIcon = new GuiIconElement(mc, Icons.REVERSE, (b -> this.replace(this.code)));
        replaceIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.replace"));

        GuiElement replaceLabel = Elements.label(IKey.lang("mappetextras.utils.codesearch.replace")).marginTop(6);
        replaceLabel.flex().relative(this).w(0.25F);

        GuiElement rowReplace = Elements.row(mc, 2, replaceLabel, this.replace, this.replaceIcon);
        rowReplace.flex().relative(this).x(5).y(0.66F, 5).w(1F, -10).h(0.33F, -10);

        GuiElement column = Elements.column(mc, 5, 10, rowIcons, rowSearch, rowReplace);
        column.flex().relative(this).wh(1F, 1F);

        this.add(column);
    }

    @Override
    public void draw(GuiContext context) {
        this.area.draw(0xCC000000);

        super.draw(context);
    }

    public void search(GuiTextEditor code) {
        String codeText = code.getText();
        int caseSensitive = ignoreCase ? Pattern.CASE_INSENSITIVE : 0;
        Pattern pattern = Pattern.compile(searchString, caseSensitive + (regex ? Pattern.MULTILINE : Pattern.LITERAL));
        ((GuiTextEditorSearchable) code).setPattern(pattern);
    }

    public void replace(GuiTextEditor code) {
        String codeText = code.getText();
        int caseSensitive = ignoreCase ? Pattern.CASE_INSENSITIVE : 0;
        Pattern pattern = Pattern.compile(searchString, caseSensitive + (regex ? Pattern.MULTILINE : Pattern.LITERAL));
        Matcher matcher = pattern.matcher(codeText);

        this.code.selectAll();
        TextEditUndo undo = new TextEditUndo(this.code);
        this.code.deleteSelection();

        code.setText(this.replaceAll ? matcher.replaceAll(replaceString) : matcher.replaceFirst(replaceString));
        undo.ready().post(code.getText(), this.code.cursor, this.code.selection);
        ((GuiTextEditorSearchable) this.code).getUndo().pushUndo(undo);
    }

    public void toggleSearch() {
        this.setVisible(!this.isVisible());
        ((GuiTextEditorSearchable) this.code).setSearching(this.search.isVisible());
    }

    public void toggleRegexIcon(GuiIconElement icon) {
        icon.iconColor(icon.iconColor == 0xFF00FF00 ? 0xFF888888 : 0xFF00FF00);
        icon.hoverColor(icon.hoverColor == 0xFF00FF00 ? 0xFF888888 : 0xFF00FF00);
        icon.icon(icon.icon == MPEIcons.EMOJI_STUCKOUTTONGUE ? MPEIcons.EMOJI_CONFUSED : MPEIcons.EMOJI_STUCKOUTTONGUE);
    }
}
