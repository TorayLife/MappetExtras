package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinScriptPlayer {
    @Shadow public abstract EntityPlayerMP getMinecraftPlayer();
    /**
     *Inspires the player into the entity. First set the player's gamemode to 3.
     *
     * @param entity
     */
    public void setSpectating(ScriptEntity entity){
        if(getMinecraftPlayer().interactionManager.getGameType().getID() == 3){
            this.getMinecraftPlayer().setSpectatingEntity(entity.getMinecraftEntity());
        }
    }

    public String getLanguage(){
        return ((MixinEntityPlayerMPInterface)this.getMinecraftPlayer()).getLanguage();
    }

    public boolean isSleeping(){
        return this.getMinecraftPlayer().isPlayerSleeping();
    }

    public int getPing(){
        return this.getMinecraftPlayer().ping;
    }

    public int getRespawnInvulnerability(){
        return ((MixinEntityPlayerMPInterface) this.getMinecraftPlayer()).getRespawnInvulnerabilityTicks();
    }

    public void loadResourcePack(String url, String hash) {
        this.getMinecraftPlayer().loadResourcePack(url, hash);
    }

    public ScriptServer getServer(){
        return new ScriptServer(this.getMinecraftPlayer().mcServer);
    }

    public ScriptWorld getWorld(){
        return new ScriptWorld(this.getMinecraftPlayer().world);
    }
}