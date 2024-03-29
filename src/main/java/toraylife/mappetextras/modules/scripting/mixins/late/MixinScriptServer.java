package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.scripts.code.ScriptServer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptTrigger;

import java.util.ArrayList;
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
    public ScriptTrigger getGlobalTrigger(String type) {
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
