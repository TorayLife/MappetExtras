package toraylife.mappetextras.modules;

import mchorse.mclib.config.values.ValueBoolean;
import toraylife.mappetextras.modules.utils.UtilsModule;

public abstract class AbstractModule implements IModule {
    public ValueBoolean enabled;

    @Override
    public boolean isEnabled() {
        return this.enabled.get();
    }
}
