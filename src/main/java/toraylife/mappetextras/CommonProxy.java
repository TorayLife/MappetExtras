package toraylife.mappetextras;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.events.EventHandler;
import toraylife.mappetextras.network.Dispatcher;

public class CommonProxy {

    public static EventHandler eventHandler;
    public CommonProxy() {
    }
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(eventHandler = new EventHandler());
        Dispatcher.register();
        MappetExtras.modules.forEach(module -> module.preInit(event));
    }

    public void init(FMLInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.postInit(event));
    }
}
