package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Field;

@Mixin(value = ScriptPlayer.class, remap = false)
public abstract class MixinScriptPlayer {
    @Shadow public abstract EntityPlayerMP getMinecraftPlayer();

    public void setSpectating(ScriptEntity entity){
        this.getMinecraftPlayer().setSpectatingEntity(entity.getMinecraftEntity());
    }

    public String getLanguage(){
        try {
            Field field = this.getMinecraftPlayer().getClass().getDeclaredField("language");
            field.setAccessible(true);

            return (String) field.get(this.getMinecraftPlayer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
