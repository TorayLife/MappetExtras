package toraylife.mappetextras.modules.scripting.mixins.late.UI;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.scripts.code.mappet.MappetUIContext;
import mchorse.mappet.api.scripts.user.IScriptEvent;
import mchorse.mappet.api.ui.UIContext;
import mchorse.mappet.api.ui.components.UIComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.mixins.utils.UIContextWithCallback;

@Mixin(value = MappetUIContext.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.mappet.IMappetUIContext")
public abstract class MixinMappetUIContext {

    @Shadow
    private UIContext context;

    @Shadow
    public abstract String getLast();

    @Shadow
    public abstract String getContext();

    @Shadow
    public abstract UIComponent get(String id);

    // Docstring says that here is an error, but it's bullshit.

    /**
     * Handles the callbacks of this ui.
     *
     * <p>See {@link UIComponent#callback(ScriptObjectMirror)} for more info.</p>
     */
    public void handleCallbacks(IScriptEvent event) {
        ((UIContextWithCallback) this.context).handleCallbacks(event);
    }
}
