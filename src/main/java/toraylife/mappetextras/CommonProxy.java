package toraylife.mappetextras;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.capabilities.IHand;
import toraylife.mappetextras.capabilities.camera.Camera;
import toraylife.mappetextras.capabilities.camera.CameraStorage;
import toraylife.mappetextras.capabilities.camera.ICamera;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHandStorage;
import toraylife.mappetextras.capabilities.minecraftHUD.IMinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUDStorage;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.offHand.OffHandStorage;
import toraylife.mappetextras.capabilities.shake.IShake;
import toraylife.mappetextras.capabilities.shake.Shake;
import toraylife.mappetextras.capabilities.shake.ShakeStorage;
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

        CapabilityManager.INSTANCE.register(IHand.class, new MainHandStorage(), MainHand::new);
        CapabilityManager.INSTANCE.register(IHand.class, new OffHandStorage(), OffHand::new);
        CapabilityManager.INSTANCE.register(IMinecraftHUD.class, new MinecraftHUDStorage(), MinecraftHUD::new);
        CapabilityManager.INSTANCE.register(IShake.class, new ShakeStorage(), Shake::new);
        CapabilityManager.INSTANCE.register(ICamera.class, new CameraStorage(), Camera::new);
    }

    public void init(FMLInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.postInit(event));
    }
}
