package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.api.scripts.code.items.ScriptInventory;
import mchorse.mappet.api.scripts.code.items.ScriptItemStack;
import mchorse.mappet.api.scripts.user.IScriptServer;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.api.scripts.user.items.IScriptInventory;
import mchorse.mappet.api.scripts.user.items.IScriptItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptFile;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptMath;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptConditionFactory;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptTriggerFactory;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptFile;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptMath;
import toraylife.mappetextras.modules.scripting.utils.Constants;

import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Mixin(value = ScriptFactory.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptFactory")
public abstract class MixinScriptFactory{

    /**
     * Retrieves the trigger factory.
     */
    public ScriptTriggerFactory getTriggerFactory() {
        return new ScriptTriggerFactory();
    }

    /**
     * Get the condition factory.
     */
    public ScriptConditionFactory getConditionFactory() {
        return new ScriptConditionFactory();
    }

    /**
     * Returns the directory path of the current world.
     *
     * <p>Read {@link IScriptFile} for more information about work with files.</p>
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        var worldDir = mappet.getWorldDir();
     *        var file = worldDir.resolve("testFile.txt");
     *        file.write("Hello World!");
     *    }
     * }</pre>
     */

    public IScriptFile getWorldDir() {
        return new ScriptFile(DimensionManager.getCurrentSaveRootDirectory().toPath());
    }

    /**
     * Returns the directory path of the minecraft directory (if singleplayer), or server directory (if multiplayer).
     *
     * <p>Read {@link IScriptFile} for more information about work with files.</p>
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        var minecraftDir = mappet.getMinecraftDir();
     *        var file = minecraftDir.resolve("testFile.txt");
     *        file.write("Hello World!");
     *    }
     * }</pre>
     */

    public IScriptFile getMinecraftDir() {
        return new ScriptFile(Paths.get("."));
    }

    /**
     * Returns the path of given string path.
     *
     * <p>Read {@link IScriptFile} for more information about work with files.</p>
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        var file = mappet.createPath("./config/mappet/test.txt");
     *        file.write("Hello World!");
     *    }
     * }</pre>
     */

    public IScriptFile createPath(String path) {
        return new ScriptFile(Paths.get(path));
    }

    public IScriptWorld getMappetWorld(World minecraftWorld) {
        return new ScriptWorld(minecraftWorld);
    }

    public IScriptServer getMappetServer(MinecraftServer minecraftServer) {
        return new ScriptServer(minecraftServer);
    }

    public IScriptTileEntity getMappetTileEntity(TileEntity minecraftTileEntity) {
        return new ScriptTileEntity(minecraftTileEntity);
    }

    public IScriptInventory getMappetInventory(IInventory minecraftInventory) {
        return new ScriptInventory(minecraftInventory);
    }

    public String getClassName(Object value) {
        String classes = value.getClass().toString();
        int beginIndex = classes.lastIndexOf(".") + 1;

        return classes.substring(beginIndex);
    }

    /**
     * Returns the {@link IScriptMath} instance.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        c.send(mappet.getMath().factorial(5)); // 120
     *    }
     * }</pre>
     */

    public IScriptMath getMath() {
        return new ScriptMath();
    }

    /**
     * Creates a unique ID string.
     *
     * @return A randomly generated UUID string
     */
    public String createUniqueId(){
        return UUID.randomUUID().toString();
    }

    /**
     * Returns all constants
     *
     * <p>
     * §7MAIN
     * §7OFF
     * §7ADVANCEMENTS
     * §7ATTACK
     * §7BACK
     * §7CHAT
     * §7COMMAND
     * §7DROP
     * §7FORWARD
     * §7FULLSCREEN
     * §7INVENTORY
     * §7JUMP
     * §7LEFT
     * §7LOAD_TOOLBAR
     * §7PICK_BLOCK
     * §7PLAYER_LIST
     * §7RIGHT
     * §7SAVE_TOOLBAR
     * §7SCREENSHOT
     * §7HOTBAR
     * §7SMOOTH_CAMERA
     * §7SNEAK
     * §7SPECTATOR_OUTLINES
     * §7SPRINT
     * §7SWAP_HANDS
     * §7PERSPECTIVE
     * §7USE_ITEM
     * §7PERSON_VIEW
     * §7ITEM_TOOLTIPS
     * §7AMBIENT_OCCLUSION
     * §7ANAGLYPH
     * §7ATTACK_INDICATOR
     * §7AUTO_JUMP
     * §7CHAT_COLOURS
     * §7CHAT_HEIGHT_UNFOCUSED
     * §7CHAT_LINKS
     * §7CHAT_LINKS_PROMPT
     * §7CHAT_OPACITY
     * §7CHAT_SCALE
     * §7CHAT_VISIBLITY
     * §7CHAT_WIDTH
     * §7CLOUDS
     * §7DEBUG_CAM
     * §7DIFFICULTY
     * §7VSYNC
     * §7WEAK_ATTACKS
     * §7ENTITY_SHADOWS
     * §7FANCY_GRAPHICS
     * §7FBO
     * §7FORCE_UNICODE_FONT
     * §7FOV
     * §7FULL_SCREEN
     * §7GAMMA
     * §7GUI_SCALE
     * §7HELD_ITEM_TOOLTIPS
     * §7HIDE_GUI
     * §7HIDE_SERVER_ADDRESS
     * §7INCOMPATIBLE_RESOURCE_PACKS
     * §7INVERT_MOUSE
     * §7LANGUAGE
     * §7LAST_SERVER
     * §7LIMIT_FRAMERATE
     * §7MAIN_HAND
     * §7MIPMAP_LEVELS
     * §7MOUSE_SENSITIVITY
     * §7NARRATOR
     * §7OVERRIDE_HEIGHT
     * §7OVERRIDE_WIDTH
     * §7PARTICLE
     * §7PAUSE_ON_LOST_FOCUS
     * §7REALMS
     * §7REDUCES_DEBUG_INFO
     * §7RENDER_DISTANCE_CHUNKS
     * §7RESOURCE_PACKS
     * §7SATURATION
     * §7DEBUG_INFO
     * §7DEBUG_PROFILER_CHART
     * §7LAGOMETER
     * §7SUBTITLES
     * §7CAMERA
     * §7SNOOPER
     * §7TOUCH_SCREEN
     * §7TUTORIAL_STEP
     * §7USE_NATIVE_TRANSPORT
     * §7USE_VBO
     * §7VIEW_BOBBING
     * </p>
     */
    public Constants getConstants(){
        return new Constants();
    }

    /**
     * get all huds Java array.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        var huds = Java.from(mappet.getAllMinecraftHUDs());
     *
     *        for(var i in huds){
     *            c.send(huds[i])
     *        }
     *    }
     *  }</pre>
     */
    public Set<String> getAllMinecraftHUDs(){
        return new MinecraftHUD().HUDs.keySet();
    }
}