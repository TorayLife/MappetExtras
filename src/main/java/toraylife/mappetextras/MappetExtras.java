package toraylife.mappetextras;

import mchorse.mclib.McLib;
import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.ConfigManager;
import mchorse.mclib.events.RegisterConfigEvent;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import toraylife.mappetextras.modules.IModule;
import toraylife.mappetextras.modules.client.ClientModule;
import toraylife.mappetextras.modules.main.MainModule;
import toraylife.mappetextras.modules.scripting.ScriptingModule;
import toraylife.mappetextras.modules.utils.UtilsModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod(
        modid = MappetExtras.MOD_ID,
        name = MappetExtras.NAME,
        version = MappetExtras.VERSION,
        dependencies = "required-after:mclib@[@MCLIB@,);" +
                "required-after:mixinbooter[@MIXINBOOTER@,);" +
                "required-after:mappet@[@MAPPET@,);",
        updateJSON = "https://raw.githubusercontent.com/TorayLife/MappetExtras/master/version.json"
)
public class MappetExtras {
    public MappetExtras() {
        MinecraftForge.EVENT_BUS.register(new RegisterHandler());
    }
    public static final String MOD_ID = "mappetextras";
    public static final String NAME = "MappetExtras";

    public static final String VERSION = "@VERSION@";

    public static final Logger logger = LogManager.getLogger(MOD_ID);

    public static Item npcPicker;
    public static Item npcSoulstoneEmpty;
    public static Item npcSoulstoneFilled;

    public static final int mainColor = 0xFFAA00;

    public ConfigManager configs;

    @Mod.Instance
    public static MappetExtras instance;

    @SidedProxy(serverSide = "toraylife.mappetextras.CommonProxy", clientSide = "toraylife.mappetextras.ClientProxy")
    public static CommonProxy proxy;

    public static final List<IModule> modules = new ArrayList<>(Arrays.asList(
        MainModule.getInstance(),
        UtilsModule.getInstance(),
        ClientModule.getInstance(),
        ScriptingModule.getInstance()
    ));

    @SubscribeEvent
    public void onConfigRegister(RegisterConfigEvent event) {
        ConfigBuilder builder = event.createBuilder(MOD_ID);

        modules.forEach(module -> module.addConfigOptions(builder));
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        String banner =
            "\n" +
                "███╗   ███╗ █████╗ ██████╗ ██████╗ ███████╗████████╗    ███████╗██╗  ██╗████████╗██████╗  █████╗ ███████╗\n" +
                "████╗ ████║██╔══██╗██╔══██╗██╔══██╗██╔════╝╚══██╔══╝    ██╔════╝╚██╗██╔╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝\n" +
                "██╔████╔██║███████║██████╔╝██████╔╝█████╗     ██║       █████╗   ╚███╔╝    ██║   ██████╔╝███████║███████╗\n" +
                "██║╚██╔╝██║██╔══██║██╔═══╝ ██╔═══╝ ██╔══╝     ██║       ██╔══╝   ██╔██╗    ██║   ██╔══██╗██╔══██║╚════██║\n" +
                "██║ ╚═╝ ██║██║  ██║██║     ██║     ███████╗   ██║       ███████╗██╔╝ ██╗   ██║   ██║  ██║██║  ██║███████║\n" +
                "╚═╝     ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝     ╚══════╝   ╚═╝       ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝\n";

        System.out.println(banner);

        McLib.EVENT_BUS.register(this);

        this.configs = new ConfigManager();

        File configFolder = Loader.instance().getConfigDir();
        configFolder.mkdir();

        this.configs.register(configFolder);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
