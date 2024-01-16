package toraylife.mappetextras.modules.utils.client.gui.ollama;

import mchorse.mappet.client.gui.utils.text.GuiText;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.utils.ai.IResponse;
import toraylife.mappetextras.modules.utils.ai.OllamaConnection;
import toraylife.mappetextras.modules.utils.ai.OllamaModelListResponse;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class GuiOllamaResponseLine extends GuiText {
	public GuiOllamaResponseLine(Minecraft mc, OllamaConnection data, IResponse response) {
		this(mc);
		this.setResponseLine(data.getId(), response.toString().trim(), LineSource.OLLAMA);
		this.tooltip(IKey.str("Eval duration: " + new DecimalFormat("#.00").format(TimeUnit.MILLISECONDS.convert(response.getEvalDuration(), TimeUnit.NANOSECONDS)) + "ms"));
	}

	public GuiOllamaResponseLine(Minecraft mc, OllamaConnection data, OllamaModelListResponse response) {
		this(mc);
		this.setResponseLine(data.url.get(), response.toString().trim(), LineSource.SYSTEM);
	}

	public GuiOllamaResponseLine(Minecraft mc, String prompt) {
		this(mc);
		this.setResponseLine(mc.player.getName(), prompt, LineSource.USER);
	}

	public GuiOllamaResponseLine(Minecraft mc) {
		super(mc);
	}

	public void setResponseLine(String sender, String message, LineSource source) {
		this.text("§6" + sender + "§r: " + message);
		if (source != LineSource.USER) {
			this.context(() -> new GuiSimpleContextMenu(mc)
					.action(Icons.COPY, IKey.lang("mappetextras.utils.ollama.copy"), () -> GuiScreen.setClipboardString(message), MappetExtras.mainColor)
			);
		}

	}
}

enum LineSource {
	USER,
	OLLAMA,
	SYSTEM
}
