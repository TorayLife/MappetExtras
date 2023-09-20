package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptPath;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptPath;

import java.nio.file.Paths;

@Mixin(value = ScriptFactory.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptFactory")
public abstract class MixinScriptFactory{

    /**
     *  Returns the directory path of the current world.
     *
     *  <p>Read {@link IScriptPath} for more information about work with files.</p>
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

    public IScriptPath getWorldDir() {
        return new ScriptPath(DimensionManager.getCurrentSaveRootDirectory().toPath());
    }

    /**
     *  Returns the directory path of the minecraft directory (if singleplayer), or server directory (if multiplayer).
     *
     *  <p>Read {@link IScriptPath} for more information about work with files.</p>
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
    public IScriptPath getMinecraftDir() {
        return new ScriptPath(Paths.get("."));
    }

    /**
     *  Returns the path of given string path.
     *
     *  <p>Read {@link IScriptPath} for more information about work with files.</p>
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *        var file = mappet.createPath("./config/mappet/test.txt");
     *        file.write("Hello World!");
     *    }
     * }</pre>
     */

    public IScriptPath createPath(String path) {
        return new ScriptPath(Paths.get(path));
    }
}