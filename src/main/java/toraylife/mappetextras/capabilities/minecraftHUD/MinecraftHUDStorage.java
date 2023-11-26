package toraylife.mappetextras.capabilities.minecraftHUD;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MinecraftHUDStorage implements Capability.IStorage<IMinecraftHUD> {
    @Override
    public NBTBase writeNBT(Capability<IMinecraftHUD> capability, IMinecraftHUD instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IMinecraftHUD> capability, IMinecraftHUD instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound)
        {
            instance.deserializeNBT((NBTTagCompound) nbt);
        }
    }
}
