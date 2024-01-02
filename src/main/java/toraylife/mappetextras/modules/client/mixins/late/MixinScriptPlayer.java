package toraylife.mappetextras.modules.client.mixins.late;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.ClientData;
import toraylife.mappetextras.modules.client.network.PacketClientData;
import toraylife.mappetextras.modules.client.scripts.code.minecraft.MinecraftArmRender;
import toraylife.mappetextras.modules.client.scripts.code.minecraft.MinecraftCamera;
import toraylife.mappetextras.modules.client.scripts.code.minecraft.MinecraftHUD;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftCamera;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftHUD;
import toraylife.mappetextras.modules.client.scripts.user.minecraft.IMinecraftArmRender;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.utils.tasks.Task;
import toraylife.mappetextras.modules.utils.tasks.TaskLoop;
import toraylife.mappetextras.network.Dispatcher;

import java.util.UUID;
import java.util.function.Consumer;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinScriptPlayer{
    @Shadow public abstract EntityPlayerMP getMinecraftPlayer();

    /**
     * Set the perspective
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.setPerspective(2)
     *    }
     *  }</pre>
     *  /**
     */
    public void setPerspective(Integer perspective){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger(ClientData.PESPECTIVE.toString(), perspective);

        Dispatcher.sendTo(new PacketClientData(ClientData.PESPECTIVE, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * Gets the player's current perspective.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.getPerspective(function (perspective){
     *            c.send(perspective)
     *        });
     *    }
     * }</pre>
     */
    public void getPerspective(Consumer<Object> callBack){
        UUID uniqueId = UUID.randomUUID();
        PacketClientData.callbacks.put(uniqueId, callBack);

        Dispatcher.sendTo(new PacketClientData(ClientData.PESPECTIVE, AccessType.GET, uniqueId), this.getMinecraftPlayer());
    }

    /**
     * Sets the player's clipboard text.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.setClipboard("Hello World!");
     *    }
     * }</pre>
     */
    public void setClipboard(String text){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.CLIPBOARD.toString(), text);

        Dispatcher.sendTo(new PacketClientData(ClientData.CLIPBOARD, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * Opens the web link at the given address.
     *
     * @param address The web address (URL) to open.
     */
    public void openWebLink(String address){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.WEB_LINK.toString(), address);

        Dispatcher.sendTo(new PacketClientData(ClientData.WEB_LINK, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * Gets the text currently on the player's clipboard.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.getClipboard(function (clipboard) {
     *            c.send(clipboard)
     *        });
     *    }
     * }</pre>
     */
    public void getClipboard(Consumer<Object> callback){
        UUID uniqueId = UUID.randomUUID();
        PacketClientData.callbacks.put(uniqueId, callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.CLIPBOARD, AccessType.GET, uniqueId), this.getMinecraftPlayer());
    }

    /**
     * Sets the player's mouse position.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.setMousePosition(100, 200, false);
     *    }
     * }</pre>
     */
    public void setMousePosition(int x, int y, boolean isInsideWindow){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagCompound object = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.MOUSEPOSITION.toString(), object);
        object.setInteger("x", x);
        object.setInteger("y", y);
        nbtTagCompound.setBoolean("isInsideWindow", isInsideWindow);

        Dispatcher.sendTo(new PacketClientData(ClientData.MOUSEPOSITION, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * Gets the player's current mouse position.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.getMousePosition(function (mouse) {
     *            c.send(mouse.x)
     *            c.send(mouse.y)
     *        }, false);
     *    }
     * }</pre>
     */
    public void getMousePosition(Consumer<Object> callback, boolean isInsideWindow){

        NBTTagCompound data = new NBTTagCompound();

        data.setBoolean("isInsideWindow", isInsideWindow);

        UUID uniqueId = UUID.randomUUID();
        PacketClientData.callbacks.put(uniqueId, callback);

        Dispatcher.sendTo(new PacketClientData(data, ClientData.MOUSEPOSITION, AccessType.GET_WITH_DATA, uniqueId), this.getMinecraftPlayer());
    }

    /**
     * Sets a client setting.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.setSetting("fovSetting", 0);
     *    }
     * }</pre>
     */
    public void setSetting(String key, Object value) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setString("key", key);
        nbtTagCompound.setString("value", value.toString());

        Dispatcher.sendTo(new PacketClientData(ClientData.SETTING, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * Gets a client setting.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.getSetting("fovSetting")
     *            .then(function(fov) {
     *                c.send(fov)
     *            });
     *    }
     * }</pre>
     */
    public Task<Void, Object> getSetting(String key) {
	    return TaskLoop.getInstance()
			    .first(t -> {
				    NBTTagCompound data = new NBTTagCompound();
				    data.setString("key", key);

				    UUID uniqueId = UUID.randomUUID();
				    PacketClientData.taskResolves.put(uniqueId, t);

				    Dispatcher.sendTo(new PacketClientData(data, ClientData.SETTING, AccessType.GET_WITH_DATA, uniqueId), this.getMinecraftPlayer());

				    return t.waitForResolve();
			    });
    }


	/**
     * Gets the player's screen resolution.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.getResolution(function(res) {
     *            c.send("x: "+res.x)
     *            c.send("y: "+res.y)
     *        });
     *    }
     * }</pre>
     */
    public void getResolution(Consumer<Object> callback) {
        UUID uniqueId = UUID.randomUUID();
        PacketClientData.callbacks.put(uniqueId, callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.RESOLUTION, AccessType.GET, uniqueId), this.getMinecraftPlayer());
    }

    /**
     * Gets a {@link IMinecraftArmRender} main or off - hand; 0 - main; off - 1;.
     *
     */
    public IMinecraftArmRender getArmRender(int hand){
        return new MinecraftArmRender(this.getMinecraftPlayer(), hand);
    }

    /**
     * Gets a {@link IMinecraftHUD} hud.
     *
     * §7HELMET,
     * §7PORTAL,
     * §7CROSSHAIRS,
     * §7BOSSHEALTH,
     * §7BOSSINFO,
     * §7ARMOR,
     * §7HEALTH,
     * §7FOOD,
     * §7AIR,
     * §7EXPERIENCE,
     * §7TEXT,
     * §7HEALTHMOUNT,
     * §7JUMPBAR,
     * §7CHAT,
     * §7PLAYER_LIST,
     * §7DEBUG,
     * §7POTION_ICONS,
     * §7SUBTITLES,
     * §7FPS_GRAPH,
     * §7VIGNETTE.
     */
    public MinecraftHUD getMinecraftHUD(String hud){
        return new MinecraftHUD(this.getMinecraftPlayer(), hud.toUpperCase());
    }


    /**
     * Resets all default Minecraft HUDs to their default state.
     */
    public void resetAllMinecraftHUDs(){
        String[] huds = new String[]{
            "HELMET", "PORTAL", "CROSSHAIRS", "BOSSHEALTH",
            "BOSSINFO", "ARMOR", "HEALTH", "AIR",
            "EXPERIENCE", "TEXT", "HEALTHMOUNT", "JUMPBAR", "CHAT",
            "PLAYER_LIST", "DEBUG", "POTION_ICONS", "SUBTITLES",
            "FPS_GRAPH", "VIGNETTE"
        };

        for(String hud : huds){
            MinecraftHUD minecraftHUD = this.getMinecraftHUD(hud);

            minecraftHUD.setPosition(0, 0);
            minecraftHUD.setRotate(0, 0, 0, 0);
        }
    }

    /**
     * Gets a ScriptCamera instance for the player.
     *
     * @return ScriptCamera instance
     */
    public IMinecraftCamera getCamera(){
        return new MinecraftCamera(this.getMinecraftPlayer());
    }
}