package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.scripts.code.ScriptServer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptTrigger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(value = ScriptServer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptServer")
public abstract class MixinScriptServer {

    @Shadow
    protected MinecraftServer server;

    /**
     * Checks if this is a single player.
     *
     * @return True if single player, false if multiplayer
     */
    public boolean isSinglePlayer() {
        return this.server.isSinglePlayer();
    }

    /**
     * Checks if this server is a dedicated server.
     *
     * @return True if dedicated server, false otherwise
     */
    public boolean isDedicatedServer() {
        return this.server.isDedicatedServer();
    }

    /**
     * Gets the opped player names on this server.
     *
     * @return List of opped player names
     */
    public List<String> getOppedPlayerNames() {
        return Arrays.asList(this.server.getPlayerList().getOppedPlayerNames());
    }

    /**
     * Get the difficulty of this server as a string.
     *
     * @return Difficulty as string (peaceful, easy, etc).
     */
    public String getDifficulty(){
        return this.server.getDifficulty().toString();
    }

    /**
     * Stop this server.
     */
    public void stopServer(){
        this.server.stopServer();
    }

    /**
     * Check if the server is in online mode.
     */
    public boolean isServerInOnlineMode() {
        return this.server.isServerInOnlineMode();
    }

    /**
     * Returns a global trigger by name. Available: §7block_break
     *
     * §7block_place
     *
     * §7block_interact
     *
     * §7block_break
     *
     * §7block_place
     *
     * §7block_interact
     *
     * §7block_click
     *
     * §7entity_damaged§r
     *
     * §7entity_attacked§r
     *
     * §7entity_death§r
     *
     * §7server_load§r
     *
     * §7server_tick§r
     *
     * §7player_chat
     *
     * §7player_login
     *
     * §7player_logout
     *
     * §7player_lmb
     *
     * §7player_rmb
     *
     * §7player_respawn
     *
     * §7player_death
     *
     * §7player_item_pickup
     *
     * §7player_item_toss
     *
     * §7player_item_interact
     *
     * §7player_entity_interact
     *
     * §7player_close_container
     *
     * §7player_open_container
     *
     * §7player_journal
     *
     * §7player_entity_leash
     *
     * §7player_open_gui
     *
     * §7player_close_gui
     *
     * §7player_eat
     *
     * §7player_drink
     *
     * §7player_tick
     *
     * §7player_walking
     *
     * §7player_dimension_change
     *
     * §7living_knockback
     *
     * §7living_equipment_change
     *
     * §7living_jump
     *
     * §7living_fall
     *
     * §7projectile_impact
     *
     * §7state_changed
     *
     * @return {@link IScriptTrigger}, global trigger.
     */
    public IScriptTrigger getGlobalTrigger(String type) {
        return new ScriptTrigger(Mappet.settings.registered.get(type));
    }

    /**
     * Returns {@link List<Integer>} of all registered dimensions
     * Useful for {@link mchorse.mappet.api.scripts.user.IScriptServer#getWorld(int)} method.
     */
    public List<Integer> getRegisteredDimensions() {
        return DimensionManager.getRegisteredDimensions().entrySet().stream().flatMapToInt(e -> Arrays.stream(e.getValue().toIntArray())).boxed().collect(Collectors.toList());
    }
}
