package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.client.ClientData;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SettingProvider implements IClientDataProvider{
    boolean dev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    String[] keys = {"keyBindAdvancements", "keyBindAttack", "keyBindBack", "keyBindChat", "keyBindCommand", "keyBindDrop", "keyBindForward", "keyBindFullscreen", "keyBindInventory", "keyBindJump", "keyBindLeft", "keyBindLoadToolbar", "keyBindPickBlock", "keyBindPlayerList", "keyBindRight", "keyBindSaveToolbar", "keyBindScreenshot", "keyBindsHotbar", "keyBindSmoothCamera", "keyBindSneak", "keyBindSpectatorOutlines", "keyBindSprint", "keyBindSwapHands", "keyBindTogglePerspective", "keyBindUseItem", "thirdPersonView", "advancedItemTooltips", "ambientOcclusion", "anaglyph", "attackIndicator", "autoJump", "chatColours", "chatHeightUnfocused", "chatLinks", "chatLinksPromp", "chatOpacity", "chatScale", "chatVisibility", "chatWidth", "clouds", "debugCamEnable", "difficulty", "enableVsync", "enableWeakAttacks", "entityShadows", "fancyGraphics", "fboEnable", "forceUnicodeFont", "fovSetting", "fullScreen", "gammaSetting", "guiScale", "heldItemTooltips", "hideGUI", "hideServerAddress", "incompatibleResourcePacks", "invertMouse", "language", "lastServer", "limitFramerate", "mainHand", "mipmapLevels", "mouseSensitivity", "narrator", "overrideHeight", "overrideWidth", "particleSetting", "pauseOnLostFocus", "realmsNotifications", "reducedDebugInfo", "renderDistanceChunks", "resourcePacks", "saturation", "showDebugInfo", "showDebugProfilerChart", "showLagometer", "showSubtitles", "smoothCamera", "snooperEnabled", "touchscreen", "tutorialStep", "useNativeTransport", "useVbo", "viewBobbing"};
    String[] fields = {"field_194146_ao", "field_74312_F", "field_74368_y", "field_74310_D", "field_74323_J", "field_74316_C", "field_74351_w", "field_152395_am", "field_151445_Q", "field_74314_A", "field_74370_x", "field_193630_aq", "field_74322_I", "field_74321_H", "field_74366_z", "field_193629_ap", "field_151447_Z", "field_151456_ac", "field_151458_ab", "field_74311_E", "field_178883_an", "field_151444_V", "field_186718_X", "field_151457_aa", "field_74313_G", "field_74320_O", "field_82882_x", "field_74348_k", "field_74337_g", "field_186716_M", "field_189989_R", "field_74344_o", "field_96693_G", "field_74359_p", "field_74358_q", "field_74357_r", "field_96691_E", "field_74343_n", "field_96692_F", "field_74345_l", "field_74325_U", "field_74318_M", "field_74352_v", "field_189422_N", "field_181151_V", "field_74347_j", "field_151448_g", "field_151455_aw", "field_74334_X", "field_74353_u", "field_74333_Y", "field_74335_Z", "field_92117_D", "field_74319_N", "field_80005_w", "field_183018_l", "field_74338_d", "field_74363_ab", "field_74332_R", "field_74350_i", "field_186715_A", "field_151442_I", "field_74341_c", "field_192571_R", "field_92119_C", "field_92118_B", "field_74362_aa", "field_82881_y", "field_183509_X", "field_178879_v", "field_151451_c", "field_151453_l", "field_151452_as", "field_74330_P", "field_74329_Q", "field_181657_aC", "field_186717_N", "field_74326_T", "field_74355_t", "field_85185_A", "field_193631_S", "field_181150_U", "field_178881_t", "field_74336_f"};

    public NBTTagCompound getData(NBTTagCompound data) throws NoSuchFieldException, IllegalAccessException {
        String key = data.getString("key");

        boolean isKeyBind;

        if(key.indexOf("keyBind") == 0){
            isKeyBind = true;
        }else{
            isKeyBind = false;
        }

        if(!dev){
            for(int i = 0; i < fields.length; i++){
                if(key.equals(keys[i])){
                    key = fields[i];
                }
            }
        }

        Field field = Minecraft.getMinecraft().gameSettings.getClass().getDeclaredField(key);
        field.setAccessible(true);
        Object setting;

        if(!isKeyBind){
            setting = field.get(Minecraft.getMinecraft().gameSettings);
        }else{
            KeyBinding keyBind = (KeyBinding) field.get(Minecraft.getMinecraft().gameSettings);
            setting = keyBind.getKeyCode();
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        nbtTagCompound.setString(ClientData.SETTING.toString(), setting.toString());

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {
        try{
            String key = data.getString("key");
            
            boolean isKeyBind;

            if(key.indexOf("keyBind") == 0){
                isKeyBind = true;
            }else{
                isKeyBind = false;
            }
            
            Object value = converter(data.getString("value"));

            if(!dev){
                for(int i = 0; i < fields.length; i++){
                    if(key.equals(keys[i])){
                        key = fields[i];
                    }
                }
            }

            Field field = Minecraft.getMinecraft().gameSettings.getClass().getDeclaredField(key);

            if(!isKeyBind){
                field.set(Minecraft.getMinecraft().gameSettings, value);
            }else{
                KeyBinding keyBind = (KeyBinding) field.get(Minecraft.getMinecraft().gameSettings);
                keyBind.setKeyCode((Integer) value);
            }

        }catch(NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    private Object converter(String setting){
        try {
            return Integer.parseInt(setting);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(setting);
            } catch (NumberFormatException g) {
                try {
                    return Float.parseFloat(setting);
                } catch (NumberFormatException f) {
                    try {
                        return Boolean.parseBoolean(setting);
                    } catch (NumberFormatException x) {
                        return setting;
                    }
                }
            }
        }
    }
}
