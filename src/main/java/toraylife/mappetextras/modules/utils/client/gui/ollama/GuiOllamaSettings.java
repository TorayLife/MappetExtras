package toraylife.mappetextras.modules.utils.client.gui.ollama;

import mchorse.mappet.client.gui.utils.text.GuiMultiTextElement;
import mchorse.mclib.client.gui.framework.elements.GuiScrollElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiCirculateElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiToggleElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTextElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTrackpadElement;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.modules.utils.ai.OllamaConnection;
import toraylife.mappetextras.modules.utils.ai.OllamaConnectionType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GuiOllamaSettings extends GuiScrollElement {

	public GuiTextElement url;
	public GuiTextElement model;
	public GuiCirculateElement format;
	public GuiMultiTextElement system;
	public GuiMultiTextElement template;
	public GuiToggleElement stream;
	public GuiTrackpadElement timeout;

	OllamaConnection data;

	public GuiOllamaSettings(Minecraft mc) {
		super(mc);

		this.url = new GuiTextElement(mc, (text) -> this.data.url.set(text));
		this.model = new GuiTextElement(mc, (text) -> this.data.model.set(text));
		this.format = new GuiCirculateElement(mc, (c) -> this.data.format.set(OllamaConnectionType.values()[c.getValue()].toString()));
		for (OllamaConnectionType value : OllamaConnectionType.values()) {
			this.format.addLabel(IKey.str(value.name()));
		}
		this.system = new GuiMultiTextElement<>(mc, (text) -> this.data.systemPrompt.set(text)).background();
		this.template = new GuiMultiTextElement<>(mc, (text) -> this.data.template.set(text)).background();
		this.stream = new GuiToggleElement(mc, IKey.lang("mappetextras.utils.ollama.stream"), (b) -> this.data.stream.set(b.isToggled()));
		this.timeout = new GuiTrackpadElement(mc, (t) -> this.data.timeout.set(t.intValue())).integer().limit(1);

		this.system.flex().h(120);
		this.template.flex().h(120);

		this.add(
				Elements.label(IKey.lang("mappetextras.utils.ollama.url")).marginTop(12), this.url,
				Elements.label(IKey.lang("mappetextras.utils.ollama.model")).marginTop(12), this.model,
				Elements.label(IKey.lang("mappetextras.utils.ollama.format")).marginTop(12), this.format,
				Elements.label(IKey.lang("mappetextras.utils.ollama.system")).marginTop(12), this.system,
				Elements.label(IKey.lang("mappetextras.utils.ollama.template")).marginTop(12), this.template,
				this.stream,
				Elements.label(IKey.lang("mappetextras.utils.ollama.timeout")).marginTop(12), this.timeout
		);
		this.markContainer();
		this.flex().column(4).vertical().stretch().scroll().padding(10);
	}

	public void updateData(OllamaConnection data) {
		this.data = data;
		if (data == null) {
			return;
		}
		this.url.setText(data.url.get());
		this.model.setText(data.model.get());
		List<String> formats = Arrays.stream(OllamaConnectionType.values()).map(OllamaConnectionType::toString).collect(Collectors.toList());
		this.format.setValue(formats.indexOf(data.format.get()));
		this.system.setText(data.systemPrompt.get());
		this.template.setText(data.template.get());
		this.stream.toggled(data.stream.get());
		this.timeout.setValue(data.timeout.get());
	}
}
