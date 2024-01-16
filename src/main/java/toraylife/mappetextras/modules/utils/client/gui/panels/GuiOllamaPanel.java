package toraylife.mappetextras.modules.utils.client.gui.panels;

import mchorse.mappet.api.utils.IContentType;
import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mappet.utils.MPIcons;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.framework.elements.input.GuiTextElement;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.utils.Direction;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.modules.utils.ContentType;
import toraylife.mappetextras.modules.utils.MPEIcons;
import toraylife.mappetextras.modules.utils.ai.OllamaConnection;
import toraylife.mappetextras.modules.utils.client.gui.ollama.GuiOllamaRepl;
import toraylife.mappetextras.modules.utils.client.gui.ollama.GuiOllamaSettings;

public class GuiOllamaPanel extends GuiMappetDashboardPanel<OllamaConnection> {

	GuiOllamaSettings settings;

	GuiOllamaRepl repl;

	GuiIconElement toggleRepl;
	GuiIconElement fetchModels;


	public GuiOllamaPanel(Minecraft mc, GuiMappetDashboard dashboard) {
		super(mc, dashboard);

		this.settings = new GuiOllamaSettings(mc);
		this.settings.flex().relative(this.editor).wh(0.25F, 1F);

		this.repl = new GuiOllamaRepl(mc);
		this.repl.setVisible(false);
		this.repl.flex().relative(this.editor).wh(1F, 1F);

		this.toggleRepl = new GuiIconElement(mc, MPIcons.REPL, (b) -> this.repl.setVisible(!this.repl.isVisible()));
		this.toggleRepl.tooltip(IKey.lang("mappetextras.utils.ollama.repl"), Direction.LEFT);
		this.iconBar.add(this.toggleRepl);

		this.fetchModels = new GuiIconElement(mc, MPEIcons.DEVICE_ANTENNA, (iconElement) -> {
			this.repl.fetchModels(iconElement);
			this.setModelsContextMenu(settings.model);
		});
		this.fetchModels.tooltip(IKey.lang("mappetextras.utils.ollama.repl"), Direction.LEFT);
		this.iconBar.add(this.fetchModels);

		this.editor.add(this.settings, this.repl);
		this.fill(null);
	}

	@Override
	public IContentType getType() {
		return ContentType.OLLAMA_CONNECTION;
	}

	@Override
	public String getTitle() {
		return "mappetextras.todo";
	}

	@Override
	public void fill(OllamaConnection data, boolean allowed) {
		super.fill(data, allowed);
		this.editor.setVisible(data != null);
		this.repl.clear();
		this.repl.updateData(data);
		this.settings.updateData(data);
	}

	public void setModelsContextMenu(GuiTextElement textElement) {
		textElement.context(() -> {
			GuiSimpleContextMenu menu = new GuiSimpleContextMenu(this.mc);
			this.data.getModelList().models.forEach((k) -> {
				menu.action(IKey.str(k.name), () -> textElement.setText(k.name));
			});
			return menu;
		});


	}
}
