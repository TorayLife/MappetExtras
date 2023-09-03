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
        try {
            Field language = this.getMinecraftPlayer().getClass().getDeclaredField("field_71148_cg");
            language.setAccessible(true);

            return (String) language.get(this.getMinecraftPlayer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSleeping(){
        return this.getMinecraftPlayer().isPlayerSleeping();
    }

    public int getPing(){
        return this.getMinecraftPlayer().ping;
    }

    public int getRespawnInvulnerability(){
        try {
            Field respawnInvulnerabilityTicks = this.getMinecraftPlayer().getClass().getDeclaredField("field_147101_bU");
            respawnInvulnerabilityTicks.setAccessible(true);

            return (int) respawnInvulnerabilityTicks.get(this.getMinecraftPlayer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}