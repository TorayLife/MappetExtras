package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.chameleon.animation.ActionPlayback;
import mchorse.chameleon.animation.Animator;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.scripting.mixins.utils.MutableAnimator;

@Mixin(value = Animator.class, remap = false)
public abstract class MixinAnimator implements MutableAnimator {
    int muted = 0;

    @Inject(method = "controlActions", at = @At("HEAD"), remap = false, cancellable = true)
    protected void controlActions(EntityLivingBase target, CallbackInfo ci) {
        if (muted > 0) {
            muted--;
            ci.cancel();
        }
    }

    public void setMuted(int muted) {
        this.muted = muted;
    }

    /* Actions */
    @Shadow public ActionPlayback idle;
    @Shadow public ActionPlayback running;
    @Shadow public ActionPlayback sprinting;
    @Shadow public ActionPlayback crouching;
    @Shadow public ActionPlayback crouchingIdle;
    @Shadow public ActionPlayback swimming;
    @Shadow public ActionPlayback swimmingIdle;
    @Shadow public ActionPlayback flying;
    @Shadow public ActionPlayback flyingIdle;
    @Shadow public ActionPlayback riding;
    @Shadow public ActionPlayback ridingIdle;
    @Shadow public ActionPlayback dying;
    @Shadow public ActionPlayback falling;
    @Shadow public ActionPlayback sleeping;

    @Shadow public ActionPlayback jump;
    @Shadow public ActionPlayback swipe;
    @Shadow public ActionPlayback hurt;
    @Shadow public ActionPlayback land;
    @Shadow public ActionPlayback shoot;
    @Shadow public ActionPlayback consume;

    /* Syncable action */
    @Shadow public ActionPlayback animation;

    @Shadow public abstract void setActiveAction(ActionPlayback action);

    @Shadow public abstract void addAction(ActionPlayback action);

    public void setAnimationByName(String animationName) {
        switch (animationName) {
            case "idle":
                this.setActiveAction(idle);
                break;
            case "running":
                this.setActiveAction(running);
                break;
            case "sprinting":
                this.setActiveAction(sprinting);
                break;
            case "crouching":
                this.addAction(crouching);
                break;
            case "crouchingIdle":
                this.setActiveAction(crouchingIdle);
                break;
            case "swimming":
                this.setActiveAction(swimming);
                break;
            case "swimmingIdle":
                this.setActiveAction(swimmingIdle);
                break;
            case "flying":
                this.setActiveAction(flying);
                break;
            case "flyingIdle":
                this.setActiveAction(flyingIdle);
                break;
            case "riding":
                this.setActiveAction(riding);
                break;
            case "ridingIdle":
                this.setActiveAction(ridingIdle);
                break;
            case "dying":
                this.setActiveAction(dying);
                break;
            case "falling":
                this.setActiveAction(falling);
                break;
            case "sleeping":
                this.setActiveAction(sleeping);
                break;
            case "jump":
                this.addAction(jump);
                break;
            case "swipe":
                this.addAction(swipe);
                break;
            case "hurt":
                this.addAction(hurt);
                break;
            case "land":
                this.addAction(land);
                break;
            case "shoot":
                this.addAction(shoot);
                break;
            case "consume":
                this.addAction(consume);
                break;
            case "animation":
                this.addAction(animation);
                break;
        }
    }
}
