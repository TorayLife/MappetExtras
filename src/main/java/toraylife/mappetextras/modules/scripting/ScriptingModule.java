package toraylife.mappetextras.modules.scripting;

import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueString;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.IModule;
import toraylife.mappetextras.modules.scripting.mixins.utils.ValueTextEditor;
import toraylife.mappetextras.modules.scripting.utils.ValueTextbox;

public class ScriptingModule implements IModule {
    public ValueTextbox scriptsFolderPath;
    public ValueTextEditor defaultTextScript;
    private static ScriptingModule instance;

    public static IModule getInstance() {
        if (ScriptingModule.instance == null) {
            ScriptingModule.instance = new ScriptingModule();
        }
        return ScriptingModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        this.defaultTextScript = (ValueTextEditor) new ValueTextEditor("defaultTextScript", "function main(c)\n{\n    // Code...\n    var s = c.getSubject();\n}").clientSide();
        this.scriptsFolderPath = new ValueTextbox("scriptsFolderPath", "");
        builder.category("scripts").register(this.defaultTextScript).register(this.scriptsFolderPath);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras scripting module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras scripting module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras scripting module postInit!");
    }

    @Override
    public String getModuleId() {
        return "scripting";
    }
}
