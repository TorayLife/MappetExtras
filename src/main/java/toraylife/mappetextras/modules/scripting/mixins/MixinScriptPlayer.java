package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

import java.lang.reflect.Field;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinScriptPlayer {
    @Shadow
    public abstract EntityPlayerMP getMinecraftPlayer();

    /**
     * Sets the player to spectate the given entity.
     *
     * @param entity Entity for the player to spectate
     */
    public void setSpectating(ScriptEntity entity) {
        this.getMinecraftPlayer().setSpectatingEntity(entity.getMinecraftEntity());
    }

    /**
     * Gets the language code this player is using.
     *
     * @return The language code
     */
    public String getLanguage() {
        try {
            Field field = this.getMinecraftPlayer().getClass().getDeclaredField("language");
            field.setAccessible(true);

            return (String) field.get(this.getMinecraftPlayer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
