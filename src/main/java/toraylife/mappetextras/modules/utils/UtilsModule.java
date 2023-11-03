package toraylife.mappetextras.modules.utils;

import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueInt;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.IModule;

public class UtilsModule implements IModule {

    public ValueInt codeSearchColor;

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
        this.codeSearchColor = builder.getInt("code_search_color", 0x22FFFFAA).colorAlpha();
        this.codeSearchColor.clientSide();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras utils module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras utils module init!");

        MPEIcons.register();
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
