package toraylife.mappetextras.modules.utils;

import mchorse.mappet.api.utils.AbstractData;
import mchorse.mappet.api.utils.manager.IManager;
import mchorse.mappet.client.gui.GuiMappetDashboard;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.utils.ai.OllamaConnection;
import toraylife.mappetextras.modules.utils.client.gui.panels.BaseDataPanel;
import toraylife.mappetextras.modules.utils.client.gui.panels.GuiOllamaPanel;
import toraylife.mappetextras.modules.utils.mixins.utils.GuiMappetDashboardAccessor;

public enum DataType {
    OLLAMA_CONNECTION("OllamaConnection", MappetExtras.ollamaManager) {
        @Override
        public GuiOllamaPanel getPanel(GuiMappetDashboard dashboard) {
            return ((GuiMappetDashboardAccessor) dashboard).getOllamaPanel();
        }
    };

    public String name;

    public IManager<? extends AbstractData> manager;

    DataType(String name, IManager<? extends AbstractData> manager) {
        this.name = name;
        this.manager = manager;
    }

    public static AbstractData get(String name) {
        if (name.equals("OllamaConnection")) {
            return new OllamaConnection();
        }
        return null;
    }

    public String getTitle() {
        return this.name;
    }

    public BaseDataPanel<? extends AbstractData> getPanel(GuiMappetDashboard dashboard) {
        return null;
    }
}
