package toraylife.mappetextras;

import toraylife.mappetextras.modules.IModule;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class LateMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return MappetExtras.modules.stream()
                .map(IModule::getMixinConfigs)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
