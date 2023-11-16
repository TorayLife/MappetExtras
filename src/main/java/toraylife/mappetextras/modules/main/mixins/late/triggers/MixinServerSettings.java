package toraylife.mappetextras.modules.main.mixins.late.triggers;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.misc.ServerSettings;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.events.RegisterServerTriggerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.main.triggers.TriggerAccessor;

import java.io.File;

@Mixin(value = ServerSettings.class, remap = false)
public abstract class MixinServerSettings implements TriggerAccessor {

    @Shadow
    public abstract Trigger register(String key, String alias, Trigger trigger);

    public Trigger playerTick;
    public Trigger playerWalking;
    public Trigger livingJump;
    public Trigger livingFall;
    public Trigger playerOpenGui;

    public Trigger getPlayerOpenGui(){
        return this.playerOpenGui;
    }

    public Trigger getPlayerTick() {
        return this.playerTick;
    }

    public Trigger getPlayerWalking() {
        return this.playerWalking;
    }

    public Trigger getEntityJumping() {
        return this.livingJump;
    }

    public Trigger getEntityFalling() {
        return this.livingFall;
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    public void constructor(File file, CallbackInfo ci) {
        this.playerTick = this.register("player_tick", "player_tick", new Trigger());
        this.playerWalking = this.register("player_walking", "player_walking", new Trigger());
        this.livingJump = this.register("living_jump", "living_jump", new Trigger());
        this.livingFall = this.register("living_fall", "living_fall", new Trigger());
        this.playerOpenGui = this.register("player_open_gui", "player_open_gui", new Trigger());

        Mappet.EVENT_BUS.post(new RegisterServerTriggerEvent((ServerSettings) (Object) this));
    }
}
