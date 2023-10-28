package toraylife.mappetextras.modules;

import mchorse.mclib.config.ConfigBuilder;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface IModule {
    void addConfigOptions(ConfigBuilder builder);

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    default List<String> getMixinConfigs() {
        return new ArrayList<>(Collections.singletonList(
                "mixins/mixins." + this.getModuleId() + ".json"
        ));
    }

    default List<String> getEarlyMixinConfigs() {
        return new ArrayList<>(Collections.singletonList(
                "mixins/mixins.early." + this.getModuleId() + ".json"
        ));
    }

    String getModuleId();
}
