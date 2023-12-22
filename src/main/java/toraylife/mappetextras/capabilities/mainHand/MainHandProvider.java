package toraylife.mappetextras.capabilities.mainHand;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import toraylife.mappetextras.capabilities.IHand;

public class MainHandProvider implements ICapabilitySerializable<NBTBase>{
    @CapabilityInject(IHand.class)
    public static final Capability<IHand> MAIN = null;

    private IHand instance = MAIN.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == MAIN;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return hasCapability(capability, facing) ? MAIN.<T>cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MAIN.getStorage().writeNBT(MAIN, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        MAIN.getStorage().readNBT(MAIN, instance, null, nbt);
    }

    public static IHand getHandler(Entity entity) {
        if (entity.hasCapability(MAIN, EnumFacing.DOWN))
            return entity.getCapability(MAIN, EnumFacing.DOWN);
        return null;
    }
}
