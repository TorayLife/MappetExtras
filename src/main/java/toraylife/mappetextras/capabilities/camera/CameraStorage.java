package toraylife.mappetextras.capabilities.camera;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class CameraStorage implements Capability.IStorage<ICamera>{
    @Override
    public NBTBase writeNBT(Capability<ICamera> capability, ICamera instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<ICamera> capability, ICamera instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound)
        {
            instance.deserializeNBT((NBTTagCompound) nbt);
        }
    }
}
