package toraylife.mappetextras.modules.client.mixins;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.ClientData;
import toraylife.mappetextras.modules.client.network.PacketClientData;
import toraylife.mappetextras.modules.client.providers.*;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;
import toraylife.mappetextras.network.Dispatcher;

import java.util.function.Consumer;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinScriptPlayer{
    @Shadow public abstract EntityPlayerMP getMinecraftPlayer();

    public void setPerspective(Integer perspective){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger(ClientData.PESPECTIVE.toString(), perspective);

        Dispatcher.sendTo(new PacketClientData(ClientData.PESPECTIVE, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    public void getPerspective(Consumer<Object> callBack){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger( ClientData.PESPECTIVE.toString(), new PerspectiveProvider().getData().getInteger( ClientData.PESPECTIVE.toString() ) );

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callBack);

        Dispatcher.sendTo(new PacketClientData(ClientData.PESPECTIVE, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());
    }

    public void setClipboard(String text){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.CLIPBOARD.toString(), text);

        Dispatcher.sendTo(new PacketClientData(ClientData.CLIPBOARD, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    public void getClipboard(Consumer<Object> callback){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString( ClientData.CLIPBOARD.toString(), new ClipboardProvider().getData().getString( ClientData.CLIPBOARD.toString() ) );

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.CLIPBOARD, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());
    }

    public void setMousePosition(int x, int y, boolean isInsideWindow){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagCompound object = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.MOUSEPOSITION.toString(), object);
        object.setInteger("x", x);
        object.setInteger("y", y);
        nbtTagCompound.setBoolean("isInsideWindow", isInsideWindow);

        Dispatcher.sendTo(new PacketClientData(ClientData.MOUSEPOSITION, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

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

    public void getSetting(String key, Consumer<Object> callback) throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound data = new NBTTagCompound();
        data.setString("key", key);

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SETTING.toString(), new SettingProvider().getData(data).getString(ClientData.SETTING.toString()));

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.SETTING, AccessType.GET_WITH_DATA, nbtTagCompound, data), this.getMinecraftPlayer());
    }

    public void setSetting(String key, Object value) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setString("key", key);
        nbtTagCompound.setString("value", value.toString());

        Dispatcher.sendTo(new PacketClientData(ClientData.SETTING, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    public void getResolution(Consumer<Object> callback) {
        NBTTagCompound object = new NBTTagCompound();

        object.setInteger("x", new ResolutionProvider().getData().getInteger("x"));
        object.setInteger("y", new ResolutionProvider().getData().getInteger("y"));

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.RESOLUTION.toString(), object);

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.RESOLUTION, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());
    }
}