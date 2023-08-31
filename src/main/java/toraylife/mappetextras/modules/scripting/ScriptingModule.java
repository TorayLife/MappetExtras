package toraylife.mappetextras.modules.scripting;

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

public class ScriptingModule extends AbstractModule {
    private static ScriptingModule instance;
    public static IModule getInstance() {
        if (ScriptingModule.instance == null) {
            ScriptingModule.instance = new ScriptingModule();
        }
        return ScriptingModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("scripting_module");
        this.enabled = builder.getBoolean("enabled",true);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras scripting module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras scripting module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        if (!this.isEnabled()) return;

        MappetExtras.logger.info("Mappet extras scripting module postInit!");
    }

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(Arrays.asList(
                "mixins/mixins." + this.getModuleId() + ".json"
        ));
    }

    @Override
    public String getModuleId() {
        return "scripting";
    }
}
