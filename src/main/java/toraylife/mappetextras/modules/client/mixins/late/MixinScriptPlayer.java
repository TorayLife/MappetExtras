package toraylife.mappetextras.modules.client.mixins.late;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.ClientData;
import toraylife.mappetextras.modules.client.network.PacketClientData;
import toraylife.mappetextras.modules.client.providers.ClipboardProvider;
import toraylife.mappetextras.modules.client.providers.MousePositionProvider;
import toraylife.mappetextras.modules.client.providers.PerspectiveProvider;
import toraylife.mappetextras.modules.client.providers.ResolutionProvider;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.network.Dispatcher;
import toraylife.mappetextras.modules.client.scripts.user.IScriptArmRender;
import toraylife.mappetextras.modules.client.scripts.code.ScriptArmRender;

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
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger( ClientData.PESPECTIVE.toString(), new PerspectiveProvider().getData().getInteger( ClientData.PESPECTIVE.toString() ) );

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callBack);

        Dispatcher.sendTo(new PacketClientData(ClientData.PESPECTIVE, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());
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
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString( ClientData.CLIPBOARD.toString(), new ClipboardProvider().getData().getString( ClientData.CLIPBOARD.toString() ) );

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.CLIPBOARD, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());
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
        NBTTagCompound object = new NBTTagCompound();

        object.setInteger("x", new MousePositionProvider().getData().getInteger("x"));
        object.setInteger("y", new MousePositionProvider().getData().getInteger("y"));

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.MOUSEPOSITION.toString(), object);

        NBTTagCompound data = new NBTTagCompound();

        data.setBoolean("isInsideWindow", isInsideWindow);

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.MOUSEPOSITION, AccessType.GET_WITH_DATA, nbtTagCompound, data), this.getMinecraftPlayer());
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
     *        c.player.getSetting("fovSetting", function(fov) {
     *            c.send(fov)
     *        });
     *    }
     * }</pre>
     */
    public void getSetting(String key, Consumer<Object> callback) throws NoSuchFieldException, IllegalAccessException{
        NBTTagCompound data = new NBTTagCompound();
        data.setString("key", key);

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SETTING.toString(), new SettingProvider().getData(data).getString(ClientData.SETTING.toString()));

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.SETTING, AccessType.GET_WITH_DATA, nbtTagCompound, data), this.getMinecraftPlayer());
    }

    /**
     * Gets the player's screen resolution.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.player.getResolution((res) => {
     *            c.send("x: "+res.x)
     *            c.send("y: "+res.y)
     *        });
     *    }
     * }</pre>
     */
    public void getResolution(Consumer<Object> callback) {
        NBTTagCompound object = new NBTTagCompound();

        object.setInteger("x", new ResolutionProvider().getData().getInteger("x"));
        object.setInteger("y", new ResolutionProvider().getData().getInteger("y"));

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.RESOLUTION.toString(), object);

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.RESOLUTION, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * Gets a {@link IScriptArmRender} main or off - hand; 0 - main; off - 1;.
     *
     */
    public ScriptArmRender getArmRender(int hand){
        return new ScriptArmRender(getMinecraftPlayer(), hand);
    }
}