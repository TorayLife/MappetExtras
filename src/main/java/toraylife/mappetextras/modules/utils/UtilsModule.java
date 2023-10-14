package toraylife.mappetextras.modules.utils;

import mchorse.mclib.config.ConfigBuilder;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.IModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UtilsModule implements IModule {

    private static UtilsModule instance;

    public static IModule getInstance() {
        if (UtilsModule.instance == null) {
            UtilsModule.instance = new UtilsModule();
        }
        return UtilsModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("utils_module");
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
    public List<String> getMixinConfigs() {
        return new ArrayList<>(Collections.singletonList(
                "mixins/mixins." + this.getModuleId() + ".json"
        ));
    }

    @Override
    public String getModuleId() {
        return "utils";
    }
}
