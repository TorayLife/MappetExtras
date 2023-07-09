package toraylife.mappetextras;

import mchorse.mclib.McLib;
import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.ConfigManager;
import mchorse.mclib.events.RegisterConfigEvent;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import toraylife.mappetextras.modules.utils.UtilsModule;
import toraylife.mappetextras.modules.IModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(
        modid = MappetExtras.MOD_ID,
        version = MappetExtras.VERSION,
        dependencies =
                "required-after:mclib@[@MCLIB@,);" +
                        "required-after:mappet@[@MAPPET@,);"
)
public class MappetExtras {
    public static final String MOD_ID = "mappetextras";

    public static final String VERSION = "@VERSION@";

    public static final Logger logger = LogManager.getLogger(MOD_ID);

    public ConfigManager configs;

    @Mod.Instance
    public static MappetExtras instance;

    @SidedProxy(serverSide = "toraylife.mappetextras.CommonProxy", clientSide = "toraylife.mappetextras.ClientProxy")
    public static CommonProxy proxy;

    public static final List<IModule> modules = new ArrayList<>();

    public MappetExtras() {
        modules.add(UtilsModule.getInstance());
    }

    @SubscribeEvent
    public void onConfigRegister(RegisterConfigEvent event) {
        ConfigBuilder builder = event.createBuilder(MOD_ID);

        modules.forEach(module -> module.addConfigOptions(builder));
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        McLib.EVENT_BUS.register(this);

        this.configs = new ConfigManager();

        File configFolder = Loader.instance().getConfigDir();
        configFolder.mkdir();

        this.configs.register(configFolder);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
