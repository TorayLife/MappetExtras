package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.chameleon.animation.ActionPlayback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ActionPlayback.class, remap = false)
public interface ActionPlaybackAccessor {
    @Accessor
    public int getDuration();
}
