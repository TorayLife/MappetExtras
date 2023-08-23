package toraylife.mappetextras.modules.utils;

import mchorse.mclib.config.ConfigBuilder;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.AbstractModule;
import toraylife.mappetextras.modules.IModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilsModule extends AbstractModule {

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
        this.enabled = builder.getBoolean("enabled",true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras utils module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras utils module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras utils module postInit!");
    }

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(Arrays.asList(
                "mixins/mixins." + this.getModuleId() + ".json"
        ));
    }

    @Override
    public String getModuleId() {
        return "utils";
    }
}
