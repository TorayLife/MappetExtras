package toraylife.mappetextras.capabilities.minecraftHUD;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MinecraftHUD implements IMinecraftHUD{
    private EntityPlayer player;
    public Map<String, NBTTagCompound> HUDs = this.createHUDs();

    public String name;

    private Map<String, NBTTagCompound> createHUDs(){
        Map<String, NBTTagCompound> map = new HashMap<>();

        map.put("ALL", this.createDefaultNBT());
        map.put("HELMET", this.createDefaultNBT());
        map.put("PORTAL", this.createDefaultNBT());
        map.put("CROSSHAIRS", this.createDefaultNBT());
        map.put("BOSSHEALTH", this.createDefaultNBT());
        map.put("BOSSINFO", this.createDefaultNBT());
        map.put("ARMOR", this.createDefaultNBT());
        map.put("HEALTH", this.createDefaultNBT());
        map.put("FOOD", this.createDefaultNBT());
        map.put("AIR", this.createDefaultNBT());
        map.put("HOTBAR", this.createDefaultNBT());
        map.put("EXPERIENCE", this.createDefaultNBT());
        map.put("TEXT", this.createDefaultNBT());
        map.put("HEALTHMOUNT", this.createDefaultNBT());
        map.put("JUMPBAR", this.createDefaultNBT());
        map.put("CHAT", this.createDefaultNBT());
        map.put("PLAYER_LIST", this.createDefaultNBT());
        map.put("DEBUG", this.createDefaultNBT());
        map.put("POTION_ICONS", this.createDefaultNBT());
        map.put("SUBTITLES", this.createDefaultNBT());
        map.put("FPS_GRAPH", this.createDefaultNBT());
        map.put("VIGNETTE", this.createDefaultNBT());

        return map;
    }

    private NBTTagCompound createDefaultNBT(){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        NBTTagCompound scale = new NBTTagCompound();
        scale.setDouble("x", 1);
        scale.setDouble("y", 1);
        scale.setDouble("z", 1);

        NBTTagCompound position = new NBTTagCompound();
        position.setDouble("x", 0);
        position.setDouble("y", 0);
        position.setDouble("z", 0);

        NBTTagCompound rotate = new NBTTagCompound();
        rotate.setDouble("angle", 0);
        rotate.setDouble("x", 0);
        rotate.setDouble("y", 0);
        rotate.setDouble("z", 0);

        nbtTagCompound.setTag("scale", scale);
        nbtTagCompound.setTag("position", position);
        nbtTagCompound.setTag("rotate", rotate);

        nbtTagCompound.setBoolean("render", true);

        return nbtTagCompound;
    }

    public static MinecraftHUD get(EntityPlayer player)
    {
        IMinecraftHUD minecraftHUDCapability = player == null ? null : player.getCapability(MinecraftHUDProvider.MINECRAFTHUD, null);

        if (minecraftHUDCapability instanceof MinecraftHUD)
        {
            MinecraftHUD profile = (MinecraftHUD) minecraftHUDCapability;
            profile.player = player;

            return profile;
        }

        return null;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public ScriptVector getScale(){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        NBTTagCompound scale = hud.getCompoundTag("scale");
        return new ScriptVector(scale.getDouble("x"), scale.getDouble("y"), 0);
    }

    @Override
    public void setScale(double x, double y){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        NBTTagCompound scale = hud.getCompoundTag("scale");

        scale.setDouble("x", x);
        scale.setDouble("y", y);
    }

    @Override
    public ScriptVector getPosition(){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        NBTTagCompound pos = hud.getCompoundTag("position");

        return new ScriptVector(pos.getDouble("x"), pos.getDouble("y"), 0);
    }

    @Override
    public void setPosition(double x, double y){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        NBTTagCompound pos = hud.getCompoundTag("position");

        pos.setDouble("x", x);
        pos.setDouble("y", y);
    }

    @Override
    public ScriptVectorAngle getRotate(){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        NBTTagCompound rotate = hud.getCompoundTag("rotate");

        return new ScriptVectorAngle(rotate.getDouble("angle"), rotate.getDouble("x"), rotate.getDouble("y"), rotate.getDouble("z"));
    }

    @Override
    public void setRotate(double angle, double x, double y, double z){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        NBTTagCompound rotate = hud.getCompoundTag("rotate");

        rotate.setDouble("angle", angle);
        rotate.setDouble("x", x);
        rotate.setDouble("y", y);
        rotate.setDouble("z", z);
    }

    @Override
    public boolean isRender(){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        return hud.getBoolean("render");
    }

    @Override
    public void setRender(boolean render){
        NBTTagCompound hud = this.HUDs.get(this.getName());

        hud.setBoolean("render", render);
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();

        tag.setTag("ALL", this.HUDs.get("ALL"));
        tag.setTag("HELMET", this.HUDs.get("HELMET"));
        tag.setTag("PORTAL", this.HUDs.get("PORTAL"));
        tag.setTag("CROSSHAIRS", this.HUDs.get("CROSSHAIRS"));
        tag.setTag("BOSSHEALTH", this.HUDs.get("BOSSHEALTH"));
        tag.setTag("BOSSINFO", this.HUDs.get("BOSSINFO"));
        tag.setTag("ARMOR", this.HUDs.get("ARMOR"));
        tag.setTag("HEALTH", this.HUDs.get("HEALTH"));
        tag.setTag("FOOD", this.HUDs.get("FOOD"));
        tag.setTag("AIR", this.HUDs.get("AIR"));
        tag.setTag("HOTBAR", this.HUDs.get("HOTBAR"));
        tag.setTag("EXPERIENCE", this.HUDs.get("EXPERIENCE"));
        tag.setTag("TEXT", this.HUDs.get("TEXT"));
        tag.setTag("HEALTHMOUNT", this.HUDs.get("HEALTHMOUNT"));
        tag.setTag("JUMPBAR", this.HUDs.get("JUMPBAR"));
        tag.setTag("CHAT", this.HUDs.get("CHAT"));
        tag.setTag("PLAYER_LIST", this.HUDs.get("PLAYER_LIST"));
        tag.setTag("DEBUG", this.HUDs.get("DEBUG"));
        tag.setTag("POTION_ICONS", this.HUDs.get("POTION_ICONS"));
        tag.setTag("SUBTITLES", this.HUDs.get("SUBTITLES"));
        tag.setTag("FPS_GRAPH", this.HUDs.get("FPS_GRAPH"));
        tag.setTag("VIGNETTE", this.HUDs.get("VIGNETTE"));

        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag)
    {
        if (tag.hasKey("ALL")) {
            this.HUDs.put("ALL", tag.getCompoundTag("ALL"));
        }
        if (tag.hasKey("HELMET")) {
            this.HUDs.put("HELMET", tag.getCompoundTag("HELMET"));
        }
        if (tag.hasKey("PORTAL")) {
            this.HUDs.put("PORTAL", tag.getCompoundTag("PORTAL"));
        }
        if (tag.hasKey("CROSSHAIRS")) {
            this.HUDs.put("CROSSHAIRS", tag.getCompoundTag("ALL"));
        }
        if (tag.hasKey("BOSSHEALTH")) {
            this.HUDs.put("BOSSHEALTH", tag.getCompoundTag("BOSSHEALTH"));
        }
        if (tag.hasKey("BOSSINFO")) {
            this.HUDs.put("BOSSINFO", tag.getCompoundTag("BOSSINFO"));
        }
        if (tag.hasKey("ARMOR")) {
            this.HUDs.put("ARMOR", tag.getCompoundTag("ARMOR"));
        }
        if (tag.hasKey("HEALTH")) {
            this.HUDs.put("HEALTH", tag.getCompoundTag("HEALTH"));
        }
        if (tag.hasKey("FOOD")) {
            this.HUDs.put("FOOD", tag.getCompoundTag("FOOD"));
        }
        if (tag.hasKey("HEALTH")) {
            this.HUDs.put("HEALTH", tag.getCompoundTag("HEALTH"));
        }
        if (tag.hasKey("AIR")) {
            this.HUDs.put("AIR", tag.getCompoundTag("AIR"));
        }
        if (tag.hasKey("HOTBAR")) {
            this.HUDs.put("HOTBAR", tag.getCompoundTag("HOTBAR"));
        }
        if (tag.hasKey("EXPERIENCE")) {
            this.HUDs.put("EXPERIENCE", tag.getCompoundTag("EXPERIENCE"));
        }
        if (tag.hasKey("TEXT")) {
            this.HUDs.put("TEXT", tag.getCompoundTag("TEXT"));
        }
        if (tag.hasKey("HEALTHMOUNT")) {
            this.HUDs.put("HEALTHMOUNT", tag.getCompoundTag("HEALTHMOUNT"));
        }
        if (tag.hasKey("JUMPBAR")) {
            this.HUDs.put("JUMPBAR", tag.getCompoundTag("JUMPBAR"));
        }
        if (tag.hasKey("CHAT")) {
            this.HUDs.put("CHAT", tag.getCompoundTag("CHAT"));
        }
        if (tag.hasKey("PLAYER_LIST")) {
            this.HUDs.put("PLAYER_LIST", tag.getCompoundTag("PLAYER_LIST"));
        }
        if (tag.hasKey("DEBUG")) {
            this.HUDs.put("DEBUG", tag.getCompoundTag("DEBUG"));
        }
        if (tag.hasKey("POTION_ICONS")) {
            this.HUDs.put("POTION_ICONS", tag.getCompoundTag("POTION_ICONS"));
        }
        if (tag.hasKey("PLAYER_LIST")) {
            this.HUDs.put("PLAYER_LIST", tag.getCompoundTag("PLAYER_LIST"));
        }
        if (tag.hasKey("SUBTITLES")) {
            this.HUDs.put("SUBTITLES", tag.getCompoundTag("SUBTITLES"));
        }
        if (tag.hasKey("FPS_GRAPH")) {
            this.HUDs.put("FPS_GRAPH", tag.getCompoundTag("FPS_GRAPH"));
        }
        if (tag.hasKey("VIGNETTE")) {
            this.HUDs.put("VIGNETTE", tag.getCompoundTag("VIGNETTE"));
        }
    }
}
