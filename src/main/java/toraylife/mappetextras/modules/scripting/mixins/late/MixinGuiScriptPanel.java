package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.api.scripts.Script;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.scripting.ScriptingModule;

@Mixin(value = GuiScriptPanel.class, remap = false)
public abstract class MixinGuiScriptPanel {
    @Inject(method = "fillDefaultData(Lmchorse/mappet/api/scripts/Script;)V", at = @At(value = "TAIL"), remap = false)
    public void onfillDefaultData(Script data, CallbackInfo ci) {
        data.code = ((ScriptingModule) ScriptingModule.getInstance()).defaultTextScript.get();
    }
}