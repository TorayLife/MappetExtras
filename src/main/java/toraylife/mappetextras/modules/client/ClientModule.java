package toraylife.mappetextras.modules.client;

import mchorse.mclib.config.ConfigBuilder;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.IModule;

public class ClientModule implements IModule {
    private static ClientModule instance;

    public static IModule getInstance() {
        if (ClientModule.instance == null) {
            ClientModule.instance = new ClientModule();
        }
        return ClientModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras client module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras client module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras client module postInit!");
    }

    @Override
    public String getModuleId() {
        return "client";
    }
}
