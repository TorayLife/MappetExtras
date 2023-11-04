package toraylife.mappetextras.modules.utils.client.gui.codeEditor;

import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mappet.client.gui.utils.text.undo.TextEditUndo;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiToggleElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTextElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiContext;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.Icon;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.modules.utils.MPEIcons;
import toraylife.mappetextras.modules.utils.UtilsModule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPanel extends GuiElement {

    public GuiTextElement search;

    public GuiIconElement searchIcon;
    public GuiTextElement replace;
    public GuiIconElement replaceIcon;


    public String searchString = "";
    public String replaceString = "";

    public GuiTextEditor code;

    public boolean regex = false;
    public boolean replaceAll = false;
    public boolean ignoreCase = false;

    public GuiToggleElement toggleRegex;
    public GuiToggleElement toggleReplaceAll;
    public GuiToggleElement toggleIgnoreCase;

    public final int COLOR_ON = 0xFF00FF00;
    public final int COLOR_OFF = 0xFF888888;

    public SearchPanel(Minecraft mc, GuiTextEditor code) {
        super(mc);

        this.code = code;

        GuiIconElement closeIcon = new GuiIconElement(mc, Icons.CLOSE, (b -> this.toggleSearch()));
        closeIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.close"));

        GuiIconElement regexIcon = new GuiIconElement(mc, MPEIcons.FOOD_POOP, (b) -> {
            this.toggleIcon(b, MPEIcons.EMOJI_COOL, MPEIcons.FOOD_POOP);
            this.regex = b.iconColor == COLOR_ON;
        }).iconColor(COLOR_OFF).hoverColor(COLOR_OFF).hovered(Icons.GRAPH);
        regexIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.regex"));

        GuiIconElement ignoreCaseIcon = new GuiIconElement(mc, MPEIcons.KEY_CAPSLOCK, (b) -> {
            this.toggleIcon(b, MPEIcons.KEY_CAPSLOCK, MPEIcons.KEY_CAPSLOCK);
            this.ignoreCase = b.iconColor == COLOR_ON;
        }).iconColor(COLOR_OFF).hoverColor(COLOR_OFF);
        ignoreCaseIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.ignore_case"));

        GuiIconElement replaceAllIcon = new GuiIconElement(mc, Icons.ALL_DIRECTIONS, (b) -> {
            this.toggleIcon(b, Icons.ALL_DIRECTIONS, Icons.ALL_DIRECTIONS);
            this.replaceAll = b.iconColor == COLOR_ON;
        }).iconColor(COLOR_OFF).hoverColor(COLOR_OFF);
        replaceAllIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.replace_all"));

        GuiElement rowIcons = Elements.row(mc, 0, regexIcon, ignoreCaseIcon, replaceAllIcon, closeIcon);

        this.search = new GuiTextElement(mc, s -> this.searchString = s);

        this.searchIcon = new GuiIconElement(mc, Icons.SEARCH, (b -> search(this.code)));
        searchIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.search"));

        GuiElement rowSearch = Elements.row(mc, 2, this.search, this.searchIcon);
        rowSearch.flex().relative(this).x(5).y(0.33F, 5).w(1F, -10).h(0.33F, -10);

        this.replace = new GuiTextElement(mc, s -> this.replaceString = s);

        this.replaceIcon = new GuiIconElement(mc, Icons.REVERSE, (b -> this.replace(this.code)));
        replaceIcon.tooltip(IKey.lang("mappetextras.utils.codesearch.replace"));

        GuiElement rowReplace = Elements.row(mc, 2, this.replace, this.replaceIcon);
        rowReplace.flex().relative(this).x(5).y(0.66F, 5).w(1F, -10).h(0.33F, -10);

        GuiElement column = Elements.column(mc, 5, 5, rowIcons, rowSearch, rowReplace);
        column.flex().relative(this).wh(1F, 1F);

        this.add(column);
    }

    @Override
    public void draw(GuiContext context) {
        this.area.draw(UtilsModule.getInstance().codeSearchBackgroundColor.get());

        super.draw(context);

        if (!this.search.field.isFocused() && this.search.field.getText().isEmpty()) {
            this.font.drawStringWithShadow(IKey.lang("mappetextras.utils.codesearch.search").get(), (float) (this.search.area.x + 5), (float) (this.search.area.y + 6), 8947848);
        }

        if (!this.replace.field.isFocused() && this.replace.field.getText().isEmpty()) {
            this.font.drawStringWithShadow(IKey.lang("mappetextras.utils.codesearch.replace").get(), (float) (this.replace.area.x + 5), (float) (this.replace.area.y + 6), 8947848);
        }
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
        ((GuiTextEditorSearchable) this.code).setSearching(this.isVisible());
    }

    public void toggleIcon(GuiIconElement icon, Icon iconOn, Icon iconOff) {
        icon.iconColor(icon.iconColor == COLOR_ON ? COLOR_OFF : COLOR_ON);
        icon.hoverColor(icon.hoverColor == COLOR_ON ? COLOR_OFF : COLOR_ON);
        icon.icon(icon.icon == iconOff ? iconOn : iconOff);
    }
}
