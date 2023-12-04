package toraylife.mappetextras.modules.scripting;

public class Constants {
    public final int MAIN = 0;
    public final int OFF = 1;
    public final int FIRST_PERSON = 0;
    public final int THIRD_PERSON = 1;
    public final int THIRD_REVERSED_PERSON = 2;
    public final String KB_ADVANCEMENTS = "keyBindAdvancements";
    public final String KB_ATTACK = "keyBindAttack";
    public final String KB_BACK = "keyBindBack";
    public final String KB_CHAT = "keyBindChat";
    public final String KB_COMMAND = "keyBindCommand";
    public final String KB_DROP = "keyBindDrop";
    public final String KB_FORWARD = "keyBindForward";
    public final String KB_FULLSCREEN = "keyBindFullscreen";
    public final String KB_JUMP = "keyBindJump";
    public final String KB_LEFT = "keyBindLeft";
    public final String KB_LOAD_TOOLBAR = "keyBindLoadToolbar";
    public final String KB_PICK_BLOCK = "keyBindPickBlock";
    public final String KB_PLAYER_LIST = "keyBindPlayerList";
    public final String KB_RIGHT = "keyBindRight";
    public final String KB_SAVE_TOOLBAR = "keyBindSaveToolbar";
    public final String KB_SCREENSHOT = "keyBindScreenshot";
    public final String KB_HOTBAR = "keyBindsHotbar";
    public final String KB_SMOOTH_CAMERA = "keyBindSmoothCamera";
    public final String KB_SNEAK = "keyBindSneak";
    public final String KB_SPECTATOR_OUTLINES = "keyBindSpectatorOutlines";
    public final String KB_SPRINT = "keyBindSprint";
    public final String KB_SWAP_HANDS = "keyBindSwapHands";
    public final String KB_TOGGLE_PERSPECTIVE = "keyBindTogglePerspective";
    public final String KB_USE_ITEM = "keyBindUseItem";
    public final String PERSPECTIVE = "thirdPersonView";
    public final String ADVANCED_ITEM_TOOLTIPS = "advancedItemTooltips";
    public final String AMBIENT_OCCLUSION = "ambientOcclusion";
    public final String ANAGLYPH = "anaglyph";
    public final String ATTACK_INDICATOR = "attackIndicator";
    public final String AUTO_JUMP = "autoJump";
    public final String CHAT_COLOURS = "chatColours";
    public final String CHAT_HEIGHT_UNFOCUSED = "chatHeightUnfocused";
    public final String CHAT_LINKS = "chatLinks";
    public final String CHAT_LINKS_PROMP = "chatLinksPromp";
    public final String CHAT_OPACITY = "chatOpacity";
    public final String CHAT_SCALE = "chatScale";
    public final String CHAT_VISIBILITY = "chatVisibility";
    public final String CHAT_WIDTH = "chatWidth";
    public final String CLOUDS = "clouds";
    public final String DEBUG_CAM_ENABLE = "debugCamEnable";
    public final String DIFFICULTY = "difficulty";
    public final String ENABLE_VSYNC = "enableVsync";
    public final String ENABLE_WEAK_ATTACKS = "enableWeakAttacks";
    public final String ENTITY_SHADOWS = "entityShadows";
    public final String FANCY_GRAPHICS = "fancyGraphics";
    public final String FBO_ENABLE = "fboEnable";
    public final String FORCE_UNICODE_FONT = "forceUnicodeFont";
    public final String FOV = "fovSetting";
    public final String FULLSCREEN = "fullScreen";
    public final String GAMMA = "gammaSetting";
    public final String GUI_SCALE = "guiScale";
    public final String HELD_ITEM_TOOLTIPS = "heldItemTooltips";
    public final String GUI_HIDE = "hideGUI";
    public final String HIDE_SERVER_ADDRESS = "hideServerAddress";
    public final String INCOMPATIBLE_RESOURCE_PACKS = "incompatibleResourcePacks";
    public final String INVERT_MOUSE = "invertMouse";
    public final String LANGUAGE = "language";
    public final String LAST_SERVER = "lastServer";
    public final String LIMIT_FRAMERATE = "limitFramerate";
    public final String MAIN_HAND = "mainHand";
    public final String MIPMAP_LEVELS = "mipmapLevels";
    public final String MOUSE_SENSITIVITY = "mouseSensitivity";
    public final String NARRATOR = "narrator";
    public final String OVERRIDE_HEIGHT = "overrideHeight";
    public final String OVERRIDE_WIDTH = "overrideWidth";
    public final String PARTICLE = "particleSetting";
    public final String PAUSE_ON_LOST_FOCUS = "pauseOnLostFocus";
    public final String REALMS = "realmsNotifications";
    public final String REDUCED_DEBUG_INFO = "reducedDebugInfo";
    public final String RENDER_DISTANCE = "renderDistanceChunks";
    public final String RESOURCE_PACKS = "resourcePacks";
    public final String SATURATION = "saturation";
    public final String SHOW_DEBUG_INFO = "showDebugInfo";
    public final String SHOW_DEBUG_PROFILER = "showDebugProfilerChart";
    public final String SHOW_LAGOMETER = "showLagometer";
    public final String SHOW_SUBTITLES = "showSubtitles";
    public final String CAMERA_SMOOTH = "smoothCamera";
    public final String SNOOPER_ENABLED = "snooperEnabled";
    public final String TOUCHSCREEN = "touchscreen";
    public final String TUTORIAL_STEP = "tutorialStep";
    public final String USE_NATIVE_TRANSPORT = "useNativeTransport";
    public final String USE_VBO = "useVbo";
    public final String VIEW_BOBBING = "viewBobbing";


    public static Constants instance;

    public static Constants getInstance() {
        if(instance == null){
            return Constants.instance = new Constants();
        }else{
            return instance;
        }

    }
}