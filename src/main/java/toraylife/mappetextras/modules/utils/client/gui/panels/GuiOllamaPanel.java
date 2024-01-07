package toraylife.mappetextras.modules.utils.client.gui.panels;

import mchorse.mappet.api.utils.IContentType;
import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.modules.utils.ContentType;
import toraylife.mappetextras.modules.utils.ai.OllamaConnection;

public class GuiOllamaPanel extends GuiMappetDashboardPanel<OllamaConnection> {


    public GuiOllamaPanel(Minecraft mc, GuiMappetDashboard dashboard) {
        super(mc, dashboard);
    }

    @Override
    public IContentType getType() {
        return ContentType.OLLAMA_CONNECTION;
    }

    @Override
    public String getTitle() {
        return "mappetextras.todo";
    }


}
