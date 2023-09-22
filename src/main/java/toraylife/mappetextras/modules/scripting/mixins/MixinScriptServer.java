package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.user.IScriptServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ScriptServer.class, remap = false)
public abstract class MixinScriptServer {
    protected MinecraftServer server;

    protected IScriptServer create(MinecraftServer server) {
        return new ScriptServer(server);
    }

    public boolean isSinglePlayer(){
        return this.server.isSinglePlayer();
    }

    public boolean isDedicatedServer(){
        return this.server.isDedicatedServer();
    }

    public String[] getOppedPlayers(){
        if(this.server.isDedicatedServer()) {
            return this.server.getPlayerList().getOppedPlayerNames();
        }else{
            return null;
        }
    }
}
