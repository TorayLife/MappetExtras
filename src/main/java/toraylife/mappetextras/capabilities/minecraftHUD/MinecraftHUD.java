package toraylife.mappetextras.capabilities.minecraftHUD;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;

import java.lang.reflect.Field;

public class MinecraftHUD implements IMinecraftHUD{
    private EntityPlayer player;
    public NBTTagCompound ALL = this.createDefaultNBT();
    public NBTTagCompound HELMET = this.createDefaultNBT();
    public NBTTagCompound PORTAL = this.createDefaultNBT();
    public NBTTagCompound CROSSHAIRS = this.createDefaultNBT();
    public NBTTagCompound BOSSHEALTH = this.createDefaultNBT();
    public NBTTagCompound BOSSINFO = this.createDefaultNBT();
    public NBTTagCompound ARMOR = this.createDefaultNBT();
    public NBTTagCompound HEALTH = this.createDefaultNBT();
    public NBTTagCompound FOOD = this.createDefaultNBT();
    public NBTTagCompound AIR = this.createDefaultNBT();
    public NBTTagCompound HOTBAR = this.createDefaultNBT();
    public NBTTagCompound EXPERIENCE = this.createDefaultNBT();
    public NBTTagCompound TEXT = this.createDefaultNBT();
    public NBTTagCompound HEALTHMOUNT = this.createDefaultNBT();
    public NBTTagCompound JUMPBAR = this.createDefaultNBT();
    public NBTTagCompound CHAT = this.createDefaultNBT();
    public NBTTagCompound PLAYER_LIST = this.createDefaultNBT();
    public NBTTagCompound DEBUG = this.createDefaultNBT();
    public NBTTagCompound POTION_ICONS = this.createDefaultNBT();
    public NBTTagCompound SUBTITLES = this.createDefaultNBT();
    public NBTTagCompound FPS_GRAPH = this.createDefaultNBT();
    public NBTTagCompound VIGNETTE = this.createDefaultNBT();
    public String name;

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

    public NBTTagCompound getHUDName(String name) throws IllegalAccessException, NoSuchFieldException {
        Field field = this.getClass().getDeclaredField(name);
        return (NBTTagCompound) field.get(this);
    }

    @Override
    public ScriptVector getScale() throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        NBTTagCompound scale = hud.getCompoundTag("scale");
        return new ScriptVector(scale.getDouble("x"), scale.getDouble("y"), 0);
    }

    @Override
    public void setScale(double x, double y) throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        NBTTagCompound scale = hud.getCompoundTag("scale");

        scale.setDouble("x", x);
        scale.setDouble("y", y);
    }

    @Override
    public ScriptVector getPosition() throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        NBTTagCompound pos = hud.getCompoundTag("position");

        return new ScriptVector(pos.getDouble("x"), pos.getDouble("y"), 0);
    }

    @Override
    public void setPosition(double x, double y) throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        NBTTagCompound pos = hud.getCompoundTag("position");

        pos.setDouble("x", x);
        pos.setDouble("y", y);
    }

    @Override
    public ScriptVectorAngle getRotate() throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        NBTTagCompound rotate = hud.getCompoundTag("rotate");

        return new ScriptVectorAngle(rotate.getDouble("angle"), rotate.getDouble("x"), rotate.getDouble("y"), rotate.getDouble("z"));
    }

    @Override
    public void setRotate(double angle, double x, double y, double z) throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        NBTTagCompound rotate = hud.getCompoundTag("rotate");

        rotate.setDouble("angle", angle);
        rotate.setDouble("x", x);
        rotate.setDouble("y", y);
        rotate.setDouble("z", z);
    }

    @Override
    public boolean isRender() throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        return hud.getBoolean("render");
    }

    @Override
    public void setRender(boolean render) throws NoSuchFieldException, IllegalAccessException {
        NBTTagCompound hud = this.getHUDName(this.name);

        hud.setBoolean("render", render);
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();

        tag.setTag("ALL", this.ALL);
        tag.setTag("HELMET", this.HELMET);
        tag.setTag("PORTAL", this.PORTAL);
        tag.setTag("CROSSHAIRS", this.CROSSHAIRS);
        tag.setTag("BOSSHEALTH", this.BOSSHEALTH);
        tag.setTag("BOSSINFO", this.BOSSINFO);
        tag.setTag("ARMOR", this.ARMOR);
        tag.setTag("HEALTH", this.HEALTH);
        tag.setTag("FOOD", this.FOOD);
        tag.setTag("AIR", this.AIR);
        tag.setTag("HOTBAR", this.HOTBAR);
        tag.setTag("EXPERIENCE", this.EXPERIENCE);
        tag.setTag("TEXT", this.TEXT);
        tag.setTag("HEALTHMOUNT", this.HEALTHMOUNT);
        tag.setTag("JUMPBAR", this.JUMPBAR);
        tag.setTag("CHAT", this.CHAT);
        tag.setTag("PLAYER_LIST", this.PLAYER_LIST);
        tag.setTag("DEBUG", this.DEBUG);
        tag.setTag("POTION_ICONS", this.POTION_ICONS);
        tag.setTag("SUBTITLES", this.SUBTITLES);
        tag.setTag("FPS_GRAPH", this.FPS_GRAPH);
        tag.setTag("VIGNETTE", this.VIGNETTE);

        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag)
    {
        if (tag.hasKey("ALL")) {
            this.ALL = tag.getCompoundTag("ALL");
        }
        if (tag.hasKey("HELMET")) {
            this.HELMET = tag.getCompoundTag("HELMET");
        }
        if (tag.hasKey("PORTAL")) {
            this.PORTAL = tag.getCompoundTag("PORTAL");
        }
        if (tag.hasKey("CROSSHAIRS")) {
            this.CROSSHAIRS = tag.getCompoundTag("CROSSHAIRS");
        }
        if (tag.hasKey("BOSSHEALTH")) {
            this.BOSSHEALTH = tag.getCompoundTag("BOSSHEALTH");
        }
        if (tag.hasKey("BOSSINFO")) {
            this.BOSSINFO = tag.getCompoundTag("BOSSINFO");
        }
        if (tag.hasKey("ARMOR")) {
            this.ARMOR = tag.getCompoundTag("ARMOR");
        }
        if (tag.hasKey("HEALTH")) {
            this.HEALTH = tag.getCompoundTag("HEALTH");
        }
        if (tag.hasKey("FOOD")) {
            this.FOOD = tag.getCompoundTag("FOOD");
        }
        if (tag.hasKey("HEALTH")) {
            this.ARMOR = tag.getCompoundTag("HEALTH");
        }
        if (tag.hasKey("AIR")) {
            this.AIR = tag.getCompoundTag("AIR");
        }
        if (tag.hasKey("HOTBAR")) {
            this.HOTBAR = tag.getCompoundTag("HOTBAR");
        }
        if (tag.hasKey("EXPERIENCE")) {
            this.EXPERIENCE = tag.getCompoundTag("EXPERIENCE");
        }
        if (tag.hasKey("TEXT")) {
            this.TEXT = tag.getCompoundTag("TEXT");
        }
        if (tag.hasKey("HEALTHMOUNT")) {
            this.HEALTHMOUNT = tag.getCompoundTag("HEALTHMOUNT");
        }
        if (tag.hasKey("JUMPBAR")) {
            this.JUMPBAR = tag.getCompoundTag("JUMPBAR");
        }
        if (tag.hasKey("CHAT")) {
            this.CHAT = tag.getCompoundTag("CHAT");
        }
        if (tag.hasKey("PLAYER_LIST")) {
            this.PLAYER_LIST = tag.getCompoundTag("PLAYER_LIST");
        }
        if (tag.hasKey("DEBUG")) {
            this.DEBUG = tag.getCompoundTag("DEBUG");
        }
        if (tag.hasKey("POTION_ICONS")) {
            this.POTION_ICONS = tag.getCompoundTag("POTION_ICONS");
        }
        if (tag.hasKey("PLAYER_LIST")) {
            this.PLAYER_LIST = tag.getCompoundTag("PLAYER_LIST");
        }
        if (tag.hasKey("SUBTITLES")) {
            this.SUBTITLES = tag.getCompoundTag("SUBTITLES");
        }
        if (tag.hasKey("FPS_GRAPH")) {
            this.FPS_GRAPH = tag.getCompoundTag("FPS_GRAPH");
        }
        if (tag.hasKey("VIGNETTE")) {
            this.VIGNETTE = tag.getCompoundTag("VIGNETTE");
        }
    }
}
