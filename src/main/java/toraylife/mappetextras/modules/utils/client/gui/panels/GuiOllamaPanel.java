package toraylife.mappetextras.modules.utils.client.gui.panels;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.list.GuiListElement;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.modules.utils.DataType;
import toraylife.mappetextras.modules.utils.ai.OllamaConnection;
import toraylife.mappetextras.modules.utils.network.DataInteractionType;
import toraylife.mappetextras.modules.utils.network.PacketData;
import toraylife.mappetextras.network.Dispatcher;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class GuiOllamaPanel extends BaseDataPanel<OllamaConnection> {

    public GuiElement iconBar;
    public GuiIconElement toggleSidebar;
    public GuiElement sidebar;

    public GuiElement buttons;
    public GuiIconElement add;
    public GuiIconElement dupe;
    public GuiIconElement rename;
    public GuiIconElement remove;

    public GuiOllamaConnectionListElement list;

    public GuiOllamaPanel(Minecraft mc, GuiMappetDashboard dashboard) {
        super(mc, dashboard);
    }

    @Override
    public DataType getType() {
        return DataType.OLLAMA_CONNECTION;
    }

    @Override
    public GuiListElement<OllamaConnection> initiateList() {
        return new GuiOllamaConnectionListElement(mc, (list) -> this.fill(list.get(0)));
    }

    @Override
    public String getTitle() {
        return "mappetextras.gui.overlays.ollama";
    }

    @Override
    public void fill(OllamaConnection data) {

    }

    @Override
    public void appear() {
        Dispatcher.sendToServer(new PacketData(DataInteractionType.FETCH, this.getType(), new HashSet<>()));
    }

    @Override
    public void disappear() {

    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    public static class GuiOllamaConnectionListElement extends GuiListElement<OllamaConnection> {
        public GuiOllamaConnectionListElement(Minecraft mc, Consumer<List<OllamaConnection>> callback) {
            super(mc, callback);
        }

        @Override
        protected String elementToString(OllamaConnection element) {
            return element.model + "(" + element.url + ")";
        }
    }
}
