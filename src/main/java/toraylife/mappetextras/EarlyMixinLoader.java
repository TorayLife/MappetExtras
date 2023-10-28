package toraylife.mappetextras;

import toraylife.mappetextras.modules.IModule;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.util.List;
import java.util.stream.Collectors;


public class EarlyMixinLoader implements IEarlyMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return MappetExtras.modules.stream()
                .map(IModule::getEarlyMixinConfigs)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
