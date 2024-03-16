package toraylife.mappetextras.capabilities.offHand;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class OffHandStorage implements Capability.IStorage<IOffHand> {
    @Override
    public NBTBase writeNBT(Capability<IOffHand> capability, IOffHand instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IOffHand> capability, IOffHand instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound)
        {
            instance.deserializeNBT((NBTTagCompound) nbt);
        }
    }
}
