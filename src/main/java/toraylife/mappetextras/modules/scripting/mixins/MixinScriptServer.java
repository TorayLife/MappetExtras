package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

@Mixin(value = ScriptServer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptServer")
public abstract class MixinScriptServer {
    protected MinecraftServer server;

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
