package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.IScriptServer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.scripting.mixins.helpers.MixinScriptFactoryHelper;

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
