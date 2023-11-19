package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.api.scripts.code.items.ScriptInventory;
import mchorse.mappet.api.scripts.user.IScriptServer;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.api.scripts.user.items.IScriptInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptFile;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptMath;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptConditionFactory;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptTriggerFactory;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptFile;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptMath;

import java.nio.file.Paths;

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
}