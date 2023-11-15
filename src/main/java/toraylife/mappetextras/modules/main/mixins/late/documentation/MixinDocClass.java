package toraylife.mappetextras.modules.main.mixins.late.documentation;

import mchorse.mappet.client.gui.scripts.utils.documentation.DocClass;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocMethod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.mixins.utils.PreciseDocClass;

import java.util.List;

@Mixin(value = DocClass.class, remap = false)
public abstract class MixinDocClass implements PreciseDocClass {

    @Shadow
    public List<DocMethod> methods;

    @Override
    public DocMethod getExactMethod(String name) {
        for (DocMethod method : this.methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }

        return null;
    }
}
