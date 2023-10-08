package toraylife.mappetextras.modules.scripting.mixins.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = EntityPlayerMP.class, remap = false)
public interface MixinEntityPlayerMPInterface {
    @Accessor("respawnInvulnerabilityTicks")
    int getRespawnInvulnerabilityTicks();

    @Accessor("language")
    String getLanguage();
}
