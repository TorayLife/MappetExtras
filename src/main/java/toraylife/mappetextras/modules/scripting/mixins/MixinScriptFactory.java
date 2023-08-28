package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.user.IScriptServer;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.api.scripts.user.items.IScriptInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.scripting.mixins.helpers.MixinScriptInventoryHelper;
import toraylife.mappetextras.modules.scripting.mixins.helpers.MixinScriptServerHelper;
import toraylife.mappetextras.modules.scripting.mixins.helpers.MixinScriptTileEntityHelper;
import toraylife.mappetextras.modules.scripting.mixins.helpers.MixinScriptWorldHelper;

@Mixin(value = ScriptFactory.class, remap = false)
public abstract class MixinScriptFactory{
    public IScriptWorld getMappetWorld(World minecraftWorld) {
        return new MixinScriptWorldHelper().create(minecraftWorld);
    }

    public IScriptServer getMappetServer(MinecraftServer minecraftServer){
        return new MixinScriptServerHelper().create(minecraftServer);
    }

    public IScriptTileEntity getMappetTileEntity(TileEntity minecraftTileEntity){
        return new MixinScriptTileEntityHelper().create(minecraftTileEntity);
    }

    public IScriptInventory getMappetInventory(IInventory minecraftInventory){
        return new MixinScriptInventoryHelper().create(minecraftInventory);
    }
}