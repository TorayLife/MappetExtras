package toraylife.mappetextras.modules.scripting.mixins.late.ignored;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.code.mappet.MappetUIBuilder;
import mchorse.mappet.api.scripts.user.mappet.IMappetUIBuilder;
import mchorse.mappet.api.ui.UI;
import mchorse.mappet.api.ui.UIContext;
import mchorse.mappet.api.ui.components.UIComponent;
import mchorse.mappet.api.ui.utils.DiscardMethod;
import mchorse.mappet.capabilities.character.ICharacter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.mixins.utils.CallbackableComponent;
import toraylife.mappetextras.modules.scripting.mixins.utils.UIContextWithCallback;

import java.util.HashMap;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptPlayer")
public class MixinScriptPlayer {

    @Inject(method = "openUI", at = @At(
            value = "INVOKE",
            target = "Lmchorse/mappet/capabilities/character/ICharacter;setUIContext(Lmchorse/mappet/api/ui/UIContext;)V"
    ), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    @DiscardMethod
    public void openUI(IMappetUIBuilder in, boolean defaultData, CallbackInfoReturnable<Boolean> cir, MappetUIBuilder builder, ICharacter character, boolean noContext, UI ui, UIContext context) {
        for (UIComponent component : builder.getCurrent().getChildComponents()) {
            if (!(component instanceof CallbackableComponent)) {
                continue;
            }

            CallbackableComponent callbackableComponent = ((CallbackableComponent) component);
            if (callbackableComponent.hasCallback()) {
                ((UIContextWithCallback) context).registerCallback(component.id, callbackableComponent.getCallback());
            }
            if (callbackableComponent.hasContextCallbacks()) {
                HashMap<String, ScriptObjectMirror> contextCallbacks = callbackableComponent.getContextCallbacks();
                for (String key : contextCallbacks.keySet()) {
                    ((UIContextWithCallback) context).registerCallback(key, contextCallbacks.get(key));
                }
            }
        }
    }
}
