package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptConditionFactory;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptFile;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTriggerFactory;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptFile;

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
}