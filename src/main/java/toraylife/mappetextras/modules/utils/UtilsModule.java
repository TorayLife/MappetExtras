package toraylife.mappetextras.modules.utils;

import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueBoolean;
import mchorse.mclib.config.values.ValueInt;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.IModule;
import toraylife.mappetextras.modules.utils.client.Beautifier;

import javax.script.ScriptException;

public class UtilsModule implements IModule {

    public ValueInt codeSearchColor;
    public ValueInt codeSearchBackgroundColor;
    public ValueBoolean codeSearchOnTop;

    public Beautifier beautifier;
    public ValueBoolean beautifierIndentEmptyLines;
    public ValueBoolean beautifierUnindentChainedMethods;
    public ValueBoolean beautifierBreakChainedMethods;
  
    private static UtilsModule instance;

    public static UtilsModule getInstance() {
        if (UtilsModule.instance == null) {
            UtilsModule.instance = new UtilsModule();
        }
        return UtilsModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("utils_module");
      
        builder.category("utils_module.code_search");
        this.codeSearchColor = (ValueInt) builder.getInt("code_search_color", 0x22FFFFAA).colorAlpha().clientSide();
        this.codeSearchBackgroundColor = (ValueInt) builder.getInt("code_search_background_color", 0xCC000000).colorAlpha().clientSide();
        this.codeSearchOnTop = (ValueBoolean) builder.getBoolean("code_search_on_top", false).clientSide();

        builder.category("utils_module.beautifier");
        this.beautifierIndentEmptyLines = (ValueBoolean) builder.getBoolean("indent_empty_lines", false).clientSide();
        this.beautifierUnindentChainedMethods = (ValueBoolean) builder.getBoolean("unindent_chained_methods", false).clientSide();
        this.beautifierBreakChainedMethods = (ValueBoolean) builder.getBoolean("break_chained_methods", false).clientSide();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras utils module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras utils module init!");

        MPEIcons.register();

        try {
            UtilsModule.getInstance().beautifier = new Beautifier();
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras utils module postInit!");
    }

    @Override
    public String getModuleId() {
        return "utils";
    }
}
