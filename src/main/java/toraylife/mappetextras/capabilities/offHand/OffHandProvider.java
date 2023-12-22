package toraylife.mappetextras.capabilities.offHand;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import toraylife.mappetextras.capabilities.IHand;

public class OffHandProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IHand.class)
    public static final Capability<IHand> OFF = null;

    private IHand instance = OFF.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == OFF;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return hasCapability(capability, facing) ? OFF.<T>cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return OFF.getStorage().writeNBT(OFF, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        OFF.getStorage().readNBT(OFF, instance, null, nbt);
    }

    public static IHand getHandler(Entity entity) {
        if (entity.hasCapability(OFF, EnumFacing.DOWN))
            return entity.getCapability(OFF, EnumFacing.DOWN);
        return null;
    }
}
