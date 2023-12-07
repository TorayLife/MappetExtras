package toraylife.mappetextras.modules.utils.mixins.late;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mclib.client.gui.mclib.GuiAbstractDashboard;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.utils.MPEIcons;
import toraylife.mappetextras.modules.utils.client.gui.panels.GuiOllamaPanel;
import toraylife.mappetextras.modules.utils.mixins.utils.GuiMappetDashboardAccessor;

@Mixin(value = GuiMappetDashboard.class, remap = false)
public abstract class MixinGuiMappetDashboard extends GuiAbstractDashboard implements GuiMappetDashboardAccessor {

	public GuiOllamaPanel ollamaPanel;

	public MixinGuiMappetDashboard(Minecraft mc) {
		super(mc);
	}

	@Inject(method = "registerPanels", at = @At("TAIL"), remap = false)
	public void registerPanels(Minecraft mc, CallbackInfo ci) {
		this.ollamaPanel = new GuiOllamaPanel(mc, (GuiMappetDashboard) (Object) this);
		//TODO fix name
		this.panels.registerPanel(this.ollamaPanel, IKey.lang("mappetextras.todo"), MPEIcons.COMPUTER_LIGHT);
	}

	@Override
	public GuiOllamaPanel getOllamaPanel() {
		return this.ollamaPanel;
	}
}
