package toraylife.mappetextras.capabilities.morphLocal;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MorphLocalStorage implements Capability.IStorage<IMorphLocal> {
    public NBTBase writeNBT(Capability<IMorphLocal> capability, IMorphLocal instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    public void readNBT(Capability<IMorphLocal> capability, IMorphLocal instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound) {
            instance.deserializeNBT((NBTTagCompound)nbt);
        }

    }
}
