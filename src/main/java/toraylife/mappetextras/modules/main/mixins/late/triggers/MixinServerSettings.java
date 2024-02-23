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
    public Trigger playerCloseGui;
    public Trigger playerEat;
    public Trigger playerDrink;
    public Trigger playerInventoryEdit;
    public Trigger playerDimensionChange;

    public Trigger getPlayerOpenGui(){
        return this.playerOpenGui;
    }
    public Trigger getPlayerCloseGui(){
        return this.playerCloseGui;
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

    public Trigger getPlayerEat(){
        return this.playerEat;
    }

    public Trigger getPlayerDrink(){
        return this.playerDrink;
    }

    public Trigger getPlayerInventoryEdit(){
        return this.playerInventoryEdit;
    }

    public Trigger getPlayerDimensionChange(){
        return this.playerDimensionChange;
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    public void constructor(File file, CallbackInfo ci) {
        this.playerTick = this.register("player_tick", "player_tick", new Trigger());
        this.playerWalking = this.register("player_walking", "player_walking", new Trigger());
        this.livingJump = this.register("living_jump", "living_jump", new Trigger());
        this.livingFall = this.register("living_fall", "living_fall", new Trigger());
        this.playerOpenGui = this.register("player_open_gui", "player_open_gui", new Trigger());
        this.playerCloseGui = this.register("player_close_gui", "player_close_gui", new Trigger());
        this.playerEat = this.register("player_eat", "player_eat", new Trigger());
        this.playerDrink = this.register("player_drink", "player_drink", new Trigger());
        this.playerDimensionChange = this.register("player_dimension_change", "player_dimension_change", new Trigger());

        Mappet.EVENT_BUS.post(new RegisterServerTriggerEvent((ServerSettings) (Object) this));
    }
}
