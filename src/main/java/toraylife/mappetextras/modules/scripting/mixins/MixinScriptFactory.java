package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.api.scripts.code.items.ScriptInventory;
import mchorse.mappet.api.scripts.user.IScriptServer;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.api.scripts.user.items.IScriptInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ScriptFactory.class, remap = false)
public abstract class MixinScriptFactory{
    public IScriptWorld getMappetWorld(World minecraftWorld) {
        return new ScriptWorld(minecraftWorld);
    }

    public IScriptServer getMappetServer(MinecraftServer minecraftServer){
        return new ScriptServer(minecraftServer);
    }

    public IScriptTileEntity getMappetTileEntity(TileEntity minecraftTileEntity){
        return new ScriptTileEntity(minecraftTileEntity);
    }

    public IScriptInventory getMappetInventory(IInventory minecraftInventory){
        return new ScriptInventory(minecraftInventory);
    }

    public double radiansToDegrees(double radians) {
        return radians * 180 / Math.PI;
    }

    public double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public String getClassName(Object value) {
        String classes = value.getClass().toString();
        int beginIndex = classes.lastIndexOf(".")+1;

        return classes.substring(beginIndex);
    }
}