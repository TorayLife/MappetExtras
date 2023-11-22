package toraylife.mappetextras.modules.scripting.mixins.late.ignored;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.scripts.code.ScriptEvent;
import mchorse.mappet.api.scripts.user.IScriptEvent;
import mchorse.mappet.api.ui.UIContext;
import mchorse.mappet.api.ui.components.UIComponent;
import mchorse.mappet.api.utils.DataContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.scripting.mixins.utils.UIContextWithCallback;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = UIContext.class, remap = false)
public abstract class MixinUIContext implements UIContextWithCallback {

    @Shadow
    public EntityPlayer player;
    @Shadow
    private String last;
    @Shadow
    private String context;

    @Shadow
    public abstract UIComponent getById(String id);

    @Shadow
    private String script;
    @Shadow
    private String function;

    @Shadow
    public abstract void sendToPlayer();

    public Map<String, ScriptObjectMirror> callbacks = new HashMap<>();

    public void registerCallback(String name, ScriptObjectMirror callback) {
        this.callbacks.put(name, callback);
    }

    @Override
    public ScriptObjectMirror getCallback(String name) {
        return this.callbacks.get(name);
    }

    @Inject(method = "handleNewData", at = @At(
            value = "INVOKE",
            target = "Lmchorse/mappet/api/ui/UIContext;handleScript(Lnet/minecraft/entity/player/EntityPlayer;)Z"
    ), remap = false)
    public void handleNewData(NBTTagCompound data, CallbackInfo ci) {
        if (this.script.isEmpty() && this.function.isEmpty()) {
            this.handleCallbacks(new ScriptEvent(new DataContext(this.player), null, null));
            this.sendToPlayer();
        }
    }

    public void handleCallbacks(IScriptEvent event) {
        String last = this.last.isEmpty() ? this.context : this.last;
        if (last.isEmpty()) {
            return;
        }

        ScriptObjectMirror callback = this.getCallback(last);
        if (callback == null) {
            return;
        }

        callback.call(null, event, this.last.isEmpty() ? null : this.getById(last), event.getPlayer().getUIContext(), last);
    }
}
