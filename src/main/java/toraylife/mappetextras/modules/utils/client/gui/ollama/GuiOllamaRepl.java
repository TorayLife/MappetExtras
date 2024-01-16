package toraylife.mappetextras.modules.utils.client.gui.ollama;

import mchorse.mappet.client.gui.utils.GuiScrollLogsElement;
import mchorse.mappet.client.gui.utils.text.GuiMultiTextElement;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiButtonElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiContext;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.modules.utils.MPEIcons;
import toraylife.mappetextras.modules.utils.ai.*;

import java.util.ArrayList;

public class GuiOllamaRepl extends GuiElement {

	public GuiMultiTextElement prompt;
	public GuiScrollLogsElement response;
	public GuiButtonElement sendRequest;
	public GuiButtonElement sendChatRequest;

	public GuiIconElement clear;
	public String promptText;

	public ArrayList<Integer> context = new ArrayList<>();
	public ArrayList<Message> messages = new ArrayList<>();

	public OllamaConnection data;


	public GuiOllamaRepl(Minecraft mc) {
		super(mc);
		this.prompt = new GuiMultiTextElement<>(mc, (text) -> promptText = text).background();
		this.prompt.padding(5);
		this.prompt.flex().relative(this).w(0.85F).h(60).y(1F, -60);
		this.clear = new GuiIconElement(mc, MPEIcons.PAINT_ERASER, (b) -> this.clearResponse());
		GuiElement iconBar = Elements.row(mc, 4, this.clear);
		iconBar.flex().relative(this).w(0.15F).h(20).x(0.85f).y(1F, -60);
		this.sendRequest = new GuiButtonElement(mc, IKey.lang("mappetextras.utils.ollama.send"), this::sendRequest);
		this.sendRequest.flex().relative(this).w(0.15f).h(20).x(0.85f).y(1F, -20);
		this.sendChatRequest = new GuiButtonElement(mc, IKey.lang("mappetextras.utils.ollama.sendChat"), this::sendChatRequest);
		this.sendChatRequest.flex().relative(this).w(0.15f).h(20).x(0.85f).y(1F, -40);

		this.response = new GuiScrollLogsElement(mc).background();
		this.response.background().flex().relative(this).w(1F).h(1F, -60)
				.column(5).vertical().stretch().scroll().padding(10);

		this.add(this.prompt, iconBar, this.sendRequest, this.sendChatRequest, this.response);
	}

	private void clearResponse() {
		this.response.removeAll();
		this.messages.clear();
		this.context.clear();
	}

	public void sendRequest(GuiButtonElement button) {
		if (this.data == null) {
			GuiOllamaResponseLine error = new GuiOllamaResponseLine(this.mc);
			error.setResponseLine("System", IKey.format("mappetextras.error", "Data is null").toString(), LineSource.SYSTEM);
			this.response.add(error);
		} else {
			this.response.add(new GuiOllamaResponseLine(this.mc, promptText));
			OllamaResponse response = data.getResponse(this.promptText, context);
			context = response.context;
			this.response.add(new GuiOllamaResponseLine(this.mc, data, response));
		}
		this.response.resize();
	}

	public void sendChatRequest(GuiButtonElement button) {
		if (this.data == null) {
			GuiOllamaResponseLine error = new GuiOllamaResponseLine(this.mc);
			error.setResponseLine("System", IKey.format("mappetextras.error", "Data is null").toString(), LineSource.SYSTEM);
			this.response.add(error);
		} else {
			this.response.add(new GuiOllamaResponseLine(this.mc, promptText));
			OllamaChatResponse response = data.getChatResponse(this.promptText, messages);
			if (response.message != null) {
				messages.add(response.message);
			}
			this.response.add(new GuiOllamaResponseLine(this.mc, data, response));
		}
		this.response.resize();
	}

	public void fetchModels(GuiIconElement button) {
		OllamaModelListResponse response = data.getModelList();
		this.response.add(new GuiOllamaResponseLine(this.mc, data, response));
		this.response.resize();
	}

	public void clear() {
		this.response.removeAll();
		this.response.resize();
		this.messages.clear();
	}

	public void updateData(OllamaConnection data) {
		this.data = data;
	}

	@Override
	public void draw(GuiContext context) {
		this.area.draw(0xFF282923);
		super.draw(context);
	}
}
