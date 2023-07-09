package toraylife.mappetextras;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {
    public CommonProxy() {
    }
    public void preInit(FMLPreInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.preInit(event));
    }

    public void init(FMLInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.modules.forEach(module -> module.postInit(event));
    }
}
