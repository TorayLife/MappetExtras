package toraylife.mappetextras.modules.client.mixins;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.ClientData;
import toraylife.mappetextras.modules.client.providers.ClipboardProvider;
import toraylife.mappetextras.modules.client.providers.MousePositionProvider;
import toraylife.mappetextras.modules.client.providers.PerspectiveProvider;
import toraylife.mappetextras.modules.client.network.PacketClientData;
import toraylife.mappetextras.modules.client.providers.SettingProvider;
import toraylife.mappetextras.network.Dispatcher;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;

import java.util.function.Consumer;

@Mixin(value = ScriptPlayer.class, remap = false)
public abstract class MixinScriptPlayer{
    @Shadow public abstract EntityPlayerMP getMinecraftPlayer();

    public void setPerspective(Integer perspective){
        EntityPlayerMP player = this.getMinecraftPlayer();
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger(ClientData.PESPECTIVE.toString(), perspective);

        Dispatcher.sendTo(new PacketClientData(ClientData.PESPECTIVE, AccessType.SET, nbtTagCompound), player);
    }

    public void getPerspective(Consumer<Object> callBack){
        EntityPlayerMP player = this.getMinecraftPlayer();

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger( ClientData.PESPECTIVE.toString(), new PerspectiveProvider().getData().getInteger( ClientData.PESPECTIVE.toString() ) );

        PacketClientData.сallBack.put(player.getUniqueID(), callBack);

        Dispatcher.sendTo(new PacketClientData(ClientData.PESPECTIVE, AccessType.GET, nbtTagCompound), player);
    }

    public void setClipboard(String text){
        EntityPlayerMP player = this.getMinecraftPlayer();
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.CLIPBOARD.toString(), text);

        Dispatcher.sendTo(new PacketClientData(ClientData.CLIPBOARD, AccessType.SET, nbtTagCompound), player);
    }

    public void getClipboard(Consumer<Object> callback){
        EntityPlayerMP player = this.getMinecraftPlayer();

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString( ClientData.CLIPBOARD.toString(), new ClipboardProvider().getData().getString( ClientData.CLIPBOARD.toString() ) );

        PacketClientData.сallBack.put(player.getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.CLIPBOARD, AccessType.GET, nbtTagCompound), player);
    }

    public void setMousePosition(int x, int y, boolean isInsideWindow){
        EntityPlayerMP player = this.getMinecraftPlayer();
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagCompound object = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.MOUSEPOSITION.toString(), object);
        object.setInteger("x", x);
        object.setInteger("y", y);
        nbtTagCompound.setBoolean("isInsideWindow", isInsideWindow);

        Dispatcher.sendTo(new PacketClientData(ClientData.MOUSEPOSITION, AccessType.SET, nbtTagCompound), player);
    }

    public void getMousePosition(Consumer<Object> callback, boolean isInsideWindow){
        EntityPlayerMP player = this.getMinecraftPlayer();

        NBTTagCompound object = new NBTTagCompound();

        object.setInteger("x", new MousePositionProvider().getData().getInteger("x"));
        object.setInteger("y", new MousePositionProvider().getData().getInteger("y"));

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.MOUSEPOSITION.toString(), object);

        NBTTagCompound data = new NBTTagCompound();

        data.setBoolean("isInsideWindow", isInsideWindow);

        PacketClientData.сallBack.put(player.getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.MOUSEPOSITION, AccessType.GET_WITH_DATA, nbtTagCompound, data), player);
    }

    public void setSetting(String key, String value){
        EntityPlayerMP player = this.getMinecraftPlayer();

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagCompound object = new NBTTagCompound();

        nbtTagCompound.setTag(ClientData.MOUSEPOSITION.toString(), object);
        object.setString("key", key);
        object.setString("value", value);

        Dispatcher.sendTo(new PacketClientData(ClientData.SETTING, AccessType.SET, nbtTagCompound), player);
    }

    public void getSetting(String key, Consumer<Object> callback){
        EntityPlayerMP player = this.getMinecraftPlayer();

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setString(ClientData.SETTING.toString(), new SettingProvider().getData().getString("key"));

        NBTTagCompound data = new NBTTagCompound();

        data.setString("key", key);

        PacketClientData.сallBack.put(player.getUniqueID(), callback);

        Dispatcher.sendTo(new PacketClientData(ClientData.SETTING, AccessType.GET_WITH_DATA, nbtTagCompound, data), player);
    }
}