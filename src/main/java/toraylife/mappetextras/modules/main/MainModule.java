package toraylife.mappetextras.modules.main;

import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueBoolean;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.IModule;
import toraylife.mappetextras.modules.main.client.gui.ValueVersionCheck;

public class MainModule implements IModule {

    public ValueBoolean showVersionUpdateMessage;
    public ValueBoolean showUpdateOnlyIfOutdated;
    private static MainModule instance;

    public static MainModule getInstance() {
        if (MainModule.instance == null) {
            MainModule.instance = new MainModule();
        }
        return MainModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("main_module").register(new ValueVersionCheck("versionCheck").clientSide());
        this.showVersionUpdateMessage = builder.getBoolean("show_version_update_message", true);
        this.showVersionUpdateMessage.clientSide();
        this.showUpdateOnlyIfOutdated = builder.getBoolean("show_update_only_if_outdated", false);
        this.showUpdateOnlyIfOutdated.clientSide();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras main module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras main module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MappetExtras.logger.info("Mappet extras main module postInit!");
    }

    @Override
    public String getModuleId() {
        return "main";
    }
}
