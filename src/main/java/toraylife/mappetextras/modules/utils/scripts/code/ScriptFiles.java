package toraylife.mappetextras.modules.utils.scripts.code;

import net.minecraftforge.common.DimensionManager;
import toraylife.mappetextras.modules.utils.scripts.user.IScriptFiles;

import java.io.File;

public class ScriptFiles implements IScriptFiles {

    @Override
    public File getWorldDir() {
        return DimensionManager.getCurrentSaveRootDirectory();
    }
}
