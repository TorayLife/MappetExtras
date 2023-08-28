package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.items.ScriptInventory;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ScriptInventory.class, remap = false)
public abstract class MixinScriptInventory {
    public ScriptInventory create(IInventory inventory){
        return new ScriptInventory(inventory);
    }
}
