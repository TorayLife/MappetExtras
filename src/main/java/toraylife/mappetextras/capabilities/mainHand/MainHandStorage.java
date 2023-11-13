package toraylife.mappetextras.capabilities.mainHand;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MainHandStorage implements Capability.IStorage<IMainHand> {
    @Override
    public NBTBase writeNBT(Capability<IMainHand> capability, IMainHand instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IMainHand> capability, IMainHand instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound)
        {
            instance.deserializeNBT((NBTTagCompound) nbt);
        }
    }
}
