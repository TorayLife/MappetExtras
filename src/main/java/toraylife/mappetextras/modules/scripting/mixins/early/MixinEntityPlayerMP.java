package toraylife.mappetextras.modules.scripting.mixins.early;

import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.scripting.mixins.utils.EntityPlayerMPAccessor;

@Mixin(value = EntityPlayerMP.class)
public class MixinEntityPlayerMP implements EntityPlayerMPAccessor {
    @Shadow(remap = true)
    private String language;

    @Shadow(remap = true)
    private int respawnInvulnerabilityTicks;

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public int getRespawnInvulnerabilityTicks() {
        return respawnInvulnerabilityTicks;
    }
}
