package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;
import toraylife.mappetextras.modules.scripting.EntityPlayerMPAccessor;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinScriptPlayer {
    @Shadow
    public abstract EntityPlayerMP getMinecraftPlayer();


    /**
     * Inspires the player into the entity. First set the player's gamemode to 3.
     *
     * @param entity
     */
    public void setSpectating(ScriptEntity entity) {
        if (getMinecraftPlayer().interactionManager.getGameType().getID() == 3) {
            this.getMinecraftPlayer().setSpectatingEntity(entity.getMinecraftEntity());
        }
    }

    /**
     * Gets the language that this player has set.
     *
     * @return the player's language code
     */
    public String getLanguage() {
        return ((EntityPlayerMPAccessor) this.getMinecraftPlayer()).getLanguage();
    }

    /**
     * Checks if this player is sleeping.
     *
     * @return true if the player is sleeping, false otherwise
     */
    public boolean isSleeping() {
        return this.getMinecraftPlayer().isPlayerSleeping();
    }

    /**
     * Gets the player's ping time in milliseconds.
     *
     * @return the player's ping time
     */
    public int getPing() {
        return this.getMinecraftPlayer().ping;
    }

    /**
     * Gets the number of ticks of respawn invulnerability left for the player.
     *
     * @return the ticks of invulnerability remaining
     */
    public int getRespawnInvulnerability() {
        return ((EntityPlayerMPAccessor) this.getMinecraftPlayer()).getRespawnInvulnerabilityTicks();
    }

    /**
     * Loads the given resource pack for this player.
     *
     * @param url  the URL of the resource pack
     * @param hash the hash of the resource pack
     */
    public void loadResourcePack(String url, String hash) {
        this.getMinecraftPlayer().loadResourcePack(url, hash);
    }

    /**
     * Gets the ScriptServer instance for this player's server.
     *
     * @return the server instance
     */
    public ScriptServer getServer() {
        return new ScriptServer(this.getMinecraftPlayer().mcServer);
    }
}