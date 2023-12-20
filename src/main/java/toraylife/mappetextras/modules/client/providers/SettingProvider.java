package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.ArrayUtils;
import toraylife.mappetextras.modules.client.ClientData;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SettingProvider implements IClientDataProvider {
    boolean dev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    String[] keys = {"keyBindAdvancements", "keyBindAttack", "keyBindBack", "keyBindChat", "keyBindCommand", "keyBindDrop", "keyBindForward", "keyBindFullscreen", "keyBindInventory", "keyBindJump", "keyBindLeft", "keyBindLoadToolbar", "keyBindPickBlock", "keyBindPlayerList", "keyBindRight", "keyBindSaveToolbar", "keyBindScreenshot", "keyBindsHotbar", "keyBindSmoothCamera", "keyBindSneak", "keyBindSpectatorOutlines", "keyBindSprint", "keyBindSwapHands", "keyBindTogglePerspective", "keyBindUseItem", "thirdPersonView", "advancedItemTooltips", "ambientOcclusion", "anaglyph", "attackIndicator", "autoJump", "chatColours", "chatHeightUnfocused", "chatLinks", "chatLinksPromp", "chatOpacity", "chatScale", "chatVisibility", "chatWidth", "clouds", "debugCamEnable", "difficulty", "enableVsync", "enableWeakAttacks", "entityShadows", "fancyGraphics", "fboEnable", "forceUnicodeFont", "fovSetting", "fullScreen", "gammaSetting", "guiScale", "heldItemTooltips", "hideGUI", "hideServerAddress", "incompatibleResourcePacks", "invertMouse", "language", "lastServer", "limitFramerate", "mainHand", "mipmapLevels", "mouseSensitivity", "narrator", "overrideHeight", "overrideWidth", "particleSetting", "pauseOnLostFocus", "realmsNotifications", "reducedDebugInfo", "renderDistanceChunks", "resourcePacks", "saturation", "showDebugInfo", "showDebugProfilerChart", "showLagometer", "showSubtitles", "smoothCamera", "snooperEnabled", "touchscreen", "tutorialStep", "useNativeTransport", "useVbo", "viewBobbing"};
    String[] fields = {"field_194146_ao", "field_74312_F", "field_74368_y", "field_74310_D", "field_74323_J", "field_74316_C", "field_74351_w", "field_152395_am", "field_151445_Q", "field_74314_A", "field_74370_x", "field_193630_aq", "field_74322_I", "field_74321_H", "field_74366_z", "field_193629_ap", "field_151447_Z", "field_151456_ac", "field_151458_ab", "field_74311_E", "field_178883_an", "field_151444_V", "field_186718_X", "field_151457_aa", "field_74313_G", "field_74320_O", "field_82882_x", "field_74348_k", "field_74337_g", "field_186716_M", "field_189989_R", "field_74344_o", "field_96693_G", "field_74359_p", "field_74358_q", "field_74357_r", "field_96691_E", "field_74343_n", "field_96692_F", "field_74345_l", "field_74325_U", "field_74318_M", "field_74352_v", "field_189422_N", "field_181151_V", "field_74347_j", "field_151448_g", "field_151455_aw", "field_74334_X", "field_74353_u", "field_74333_Y", "field_74335_Z", "field_92117_D", "field_74319_N", "field_80005_w", "field_183018_l", "field_74338_d", "field_74363_ab", "field_74332_R", "field_74350_i", "field_186715_A", "field_151442_I", "field_74341_c", "field_192571_R", "field_92119_C", "field_92118_B", "field_74362_aa", "field_82881_y", "field_183509_X", "field_178879_v", "field_151451_c", "field_151453_l", "field_151452_as", "field_74330_P", "field_74329_Q", "field_181657_aC", "field_186717_N", "field_74326_T", "field_74355_t", "field_85185_A", "field_193631_S", "field_181150_U", "field_178881_t", "field_74336_f"};

    @Override
    public NBTTagCompound getData(NBTTagCompound data) {
        String key = data.getString("key");
        SettingType type = key.startsWith("keyBind") ? SettingType.KEYBIND : SettingType.NORMAL;
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
        Object value;

        if(type == SettingType.KEYBIND){
            String output = key.replace("keyBind", "");
            output = Character.toLowerCase(output.charAt(0)) + output.substring(1);

            value = gameSettings.keyBindings[this.getKeyBindIndex(gameSettings, "key."+output)].getKeyCode();
        }else{
            Map<String, Object> settingsMap = this.createMap(gameSettings);

            value = settingsMap.get(key);
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SETTING.toString(), value.toString());

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) throws Throwable {
        String key = data.getString("key");
        Object value = converter(data.getString("value"));
        SettingType type = key.startsWith("keyBind") ? SettingType.KEYBIND : SettingType.NORMAL;
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

        if(type == SettingType.KEYBIND){
            String output = key.replace("keyBind", "");
            output = Character.toLowerCase(output.charAt(0)) + output.substring(1);

            gameSettings.setOptionKeyBinding(gameSettings.keyBindings[this.getKeyBindIndex(gameSettings, "key."+output)], (int) value);
        }else{
            if(!dev){
                int indexField = indexOf(this.keys, key);
                key = fields[indexField];
            }

            Map<String, MethodHandle> setters = getSetters(gameSettings.getClass());
            setters.get(key).invoke(gameSettings, value);
        }


        gameSettings.saveOptions();
        gameSettings.loadOptions();
    }

    public static int indexOf(String[] array, String search) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(search)) {
                return i;
            }
        }
        return -1;
    }

    private int getKeyBindIndex(GameSettings gameSettings, String key) {
        int index = -1;
        KeyBinding[] array = gameSettings.keyBindings;

        for(int i = 0; i < array.length; i++) {
            KeyBinding setting = array[i];
            if(setting.getKeyDescription().equals(key)) {
                index = i;
                break;
            }
        }

        return index;
    }

    private static Map<String, MethodHandle> getSetters(Class<?> type) throws Exception {
        Map<String, MethodHandle> setters = new HashMap<>();
        while (type != null) {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                setters.put(field.getName(), MethodHandles
                        .lookup()
                        .unreflectSetter(field));
            }
            type = type.getSuperclass();
        }

        return setters;
    }

    public Map<String, Object> createMap(GameSettings gameSettings){
        Map<String, Object> settingsMap = new HashMap<>();

        settingsMap.put("thirdPersonView", gameSettings.thirdPersonView);
        settingsMap.put("advancedItemTooltips", gameSettings.advancedItemTooltips);
        settingsMap.put("ambientOcclusion", gameSettings.ambientOcclusion);
        settingsMap.put("anaglyph", gameSettings.anaglyph);
        settingsMap.put("attackIndicator", gameSettings.attackIndicator);
        settingsMap.put("autoJump", gameSettings.autoJump);
        settingsMap.put("chatColours", gameSettings.chatColours);
        settingsMap.put("chatHeightUnfocused", gameSettings.chatHeightUnfocused);
        settingsMap.put("chatLinks", gameSettings.chatLinks);
        settingsMap.put("chatLinksPrompt", gameSettings.chatLinksPrompt);
        settingsMap.put("chatOpacity", gameSettings.chatOpacity);
        settingsMap.put("chatScale", gameSettings.chatScale);
        settingsMap.put("chatVisibility", gameSettings.chatVisibility);
        settingsMap.put("chatWidth", gameSettings.chatWidth);
        settingsMap.put("clouds", gameSettings.clouds);
        settingsMap.put("debugCamEnable", gameSettings.debugCamEnable);
        settingsMap.put("difficulty", gameSettings.difficulty);
        settingsMap.put("enableVsync", gameSettings.enableVsync);
        settingsMap.put("enableWeakAttacks", gameSettings.enableWeakAttacks);
        settingsMap.put("entityShadows", gameSettings.entityShadows);
        settingsMap.put("fancyGraphics", gameSettings.fancyGraphics);
        settingsMap.put("fboEnable", gameSettings.fboEnable);
        settingsMap.put("forceUnicodeFont", gameSettings.forceUnicodeFont);
        settingsMap.put("fovSetting", gameSettings.fovSetting);
        settingsMap.put("fullScreen", gameSettings.fullScreen);
        settingsMap.put("gammaSetting", gameSettings.gammaSetting);
        settingsMap.put("guiScale", gameSettings.guiScale);
        settingsMap.put("heldItemTooltips", gameSettings.heldItemTooltips);
        settingsMap.put("hideGUI", gameSettings.hideGUI);
        settingsMap.put("hideServerAddress", gameSettings.hideServerAddress);
        settingsMap.put("incompatibleResourcePacks", gameSettings.incompatibleResourcePacks);
        settingsMap.put("invertMouse", gameSettings.invertMouse);
        settingsMap.put("language", gameSettings.language);
        settingsMap.put("lastServer", gameSettings.lastServer);
        settingsMap.put("limitFramerate", gameSettings.limitFramerate);
        settingsMap.put("mainHand", gameSettings.mainHand);
        settingsMap.put("mipmapLevels", gameSettings.mipmapLevels);
        settingsMap.put("mouseSensitivity", gameSettings.mouseSensitivity);
        settingsMap.put("narrator", gameSettings.narrator);
        settingsMap.put("overrideHeight", gameSettings.overrideHeight);
        settingsMap.put("overrideWidth", gameSettings.overrideWidth);
        settingsMap.put("particleSetting", gameSettings.particleSetting);
        settingsMap.put("pauseOnLostFocus", gameSettings.pauseOnLostFocus);
        settingsMap.put("realmsNotifications", gameSettings.realmsNotifications);
        settingsMap.put("reducedDebugInfo", gameSettings.reducedDebugInfo);
        settingsMap.put("renderDistanceChunks", gameSettings.renderDistanceChunks);
        settingsMap.put("resourcePacks", gameSettings.resourcePacks);
        settingsMap.put("saturation", gameSettings.saturation);
        settingsMap.put("showDebugInfo", gameSettings.showDebugInfo);
        settingsMap.put("showDebugProfilerChart", gameSettings.showDebugProfilerChart);
        settingsMap.put("showLagometer", gameSettings.showLagometer);
        settingsMap.put("showSubtitles", gameSettings.showSubtitles);
        settingsMap.put("smoothCamera", gameSettings.smoothCamera);
        settingsMap.put("snooperEnabled", gameSettings.snooperEnabled);
        settingsMap.put("touchscreen", gameSettings.touchscreen);
        settingsMap.put("tutorialStep", gameSettings.tutorialStep);
        settingsMap.put("useNativeTransport", gameSettings.useNativeTransport);
        settingsMap.put("useVbo", gameSettings.useVbo);
        settingsMap.put("viewBobbing", gameSettings.viewBobbing);

        return settingsMap;
    }

    public enum SettingType {
        NORMAL,
        KEYBIND
    }

    private Object converter(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {}

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {}

        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {}

        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {}

        return value;
    }
}
