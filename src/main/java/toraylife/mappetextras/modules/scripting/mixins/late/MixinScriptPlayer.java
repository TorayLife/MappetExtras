package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.ClientData;
import toraylife.mappetextras.modules.client.network.PacketClientData;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.mixins.utils.EntityPlayerMPAccessor;
import toraylife.mappetextras.network.Dispatcher;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinScriptPlayer {
    @Shadow
    public abstract EntityPlayerMP getMinecraftPlayer();
    
    private EntityPlayerMP player = this.getMinecraftPlayer();


    /**
     * Inspires the player into the entity. First set the player's gamemode to 3.
     *
     * @param entity
     */
    public void setSpectating(ScriptEntity entity) {
        if (this.player.interactionManager.getGameType().getID() == 3) {
            this.player.setSpectatingEntity(entity.getMinecraftEntity());
        }
    }

    /**
     * Gets the language that this player has set.
     *
     * @return the player's language code
     */
    public String getLanguage() {
        return ((EntityPlayerMPAccessor) this.player).getLanguage();
    }

    /**
     * Checks if this player is sleeping.
     *
     * @return true if the player is sleeping, false otherwise
     */
    public boolean isSleeping() {
        return this.player.isPlayerSleeping();
    }

    /**
     * Gets the player's ping time in milliseconds.
     *
     * @return the player's ping time
     */
    public int getPing() {
        return this.player.ping;
    }

    /**
     * Gets the number of ticks of respawn invulnerability left for the player.
     *
     * @return the ticks of invulnerability remaining
     */
    public int getRespawnInvulnerability() {
        return ((EntityPlayerMPAccessor) this.player).getRespawnInvulnerabilityTicks();
    }

    /**
     * Loads the given resource pack for this player.
     *
     * @param url  the URL of the resource pack
     * @param hash the hash of the resource pack
     */
    public void loadResourcePack(String url, String hash) {
        this.player.loadResourcePack(url, hash);
    }

    /**
     * Gets the ScriptServer instance for this player's server.
     *
     * @return the server instance
     */
    public ScriptServer getServer() {
        return new ScriptServer(this.player.mcServer);
    }

    /**
     * Checks if the player is currently eating food.
     *
     * @return True if the player is eating, false otherwise
     */
    public boolean isEat(){
        EntityPlayerMP player = this.player;
        ItemStack item = player.getActiveItemStack();

        return player.getItemInUseCount() > 0 && item.getItem() instanceof ItemFood;
    }

    /**
     * Checks if the player is drinking a potion.
     *
     * @return True if the player is drinking a potion, false otherwise
     */
    public boolean isDrink(){
        ItemStack item = this.player.getActiveItemStack();

        return this.player.getItemInUseCount() > 0 && item.getItem() instanceof ItemPotion;
    }

    /**
     * Sends a packet to update sounds for this player.
     */
    public void soundUpdate(){
        Dispatcher.sendTo(new PacketClientData(ClientData.SOUND, AccessType.UPDATE, new NBTTagCompound()), this.player);
    }

    /**
     * Sends a packet to update textures for this player.
     */
    public void textureUpdate(){
        Dispatcher.sendTo(new PacketClientData(ClientData.TEXTURE, AccessType.UPDATE, new NBTTagCompound()), this.player);
    }

    /**
     * Checks if this player is currently walking.
     *
     * @return True if walking, false otherwise.
     */
    public boolean isWalking(){
        return this.player.prevDistanceWalkedModified
                - this.player.distanceWalkedModified != 0;
    }

    /**
     * Disconnects this player with the given reason.
     * Only works in single player.
     *
     * @param reason Disconnect reason
     */
    public void disconnect(String reason){
        if(!this.player.mcServer.isDedicatedServer() && this.player.mcServer.isSinglePlayer()){
            this.player.connection.disconnect(new TextComponentString(reason));
        }
    }
}