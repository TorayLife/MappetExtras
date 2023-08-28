package toraylife.mappetextras.modules.client;

import mchorse.mclib.config.ConfigBuilder;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.AbstractModule;
import toraylife.mappetextras.modules.IModule;
import toraylife.mappetextras.modules.utils.UtilsModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientModule extends AbstractModule {
    private static ClientModule instance;
    public static IModule getInstance() {
        if (ClientModule.instance == null) {
            ClientModule.instance = new ClientModule();
        }
        return ClientModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("client_module");
        this.enabled = builder.getBoolean("enabled",true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras client module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras client module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras client module postInit!");
    }

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(Arrays.asList(
                "mixins/mixins." + this.getModuleId() + ".json"
        ));
    }

    @Override
    public String getModuleId() {
        return "client";
    }
}
