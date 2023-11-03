package toraylife.mappetextras.modules.utils.client.gui.codeEditor;

import mchorse.mappet.client.gui.utils.text.GuiMultiTextElement;
import mchorse.mclib.utils.undo.UndoManager;

import java.util.regex.Pattern;

public interface GuiTextEditorSearchable {

    void setSearching(boolean searching);

    boolean isSearching();

    Pattern getPattern();

    void setPattern(Pattern pattern);

    UndoManager<GuiMultiTextElement> getUndo();
}
