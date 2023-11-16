package toraylife.mappetextras;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.capabilities.mainHand.IMainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHandStorage;
import toraylife.mappetextras.capabilities.offHand.IOffHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.offHand.OffHandStorage;
import toraylife.mappetextras.events.EventHandler;
import toraylife.mappetextras.events.EventTriggerHandler;
import toraylife.mappetextras.network.Dispatcher;

public class CommonProxy {
    public static EventHandler eventHandler;
    public static EventTriggerHandler eventTriggerHandler;
    public CommonProxy() {
    }
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(eventHandler = new EventHandler());
        MinecraftForge.EVENT_BUS.register(eventTriggerHandler = new EventTriggerHandler());
        Dispatcher.register();
        MappetExtras.modules.forEach(module -> module.preInit(event));

        CapabilityManager.INSTANCE.register(IMainHand.class, new MainHandStorage(), MainHand::new);
        CapabilityManager.INSTANCE.register(IOffHand.class, new OffHandStorage(), OffHand::new);
    }

    public void init(FMLInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.postInit(event));
    }
}
