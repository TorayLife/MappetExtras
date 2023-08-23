package toraylife.mappetextras;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import toraylife.mappetextras.modules.IModule;
import zone.rong.mixinbooter.ILateMixinLoader;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LateMixinLoader implements IFMLLoadingPlugin, ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return MappetExtras.modules.stream()
                .map(IModule::getMixinConfigs)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
