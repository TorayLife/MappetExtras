package toraylife.mappetextras.capabilities.shake;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ShakeProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IShake.class)
    public static final Capability<IShake> SHAKE = null;

    private IShake instance = SHAKE.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SHAKE;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return hasCapability(capability, facing) ? SHAKE.<T>cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return SHAKE.getStorage().writeNBT(SHAKE, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        SHAKE.getStorage().readNBT(SHAKE, instance, null, nbt);
    }

    public static IShake getHandler(Entity entity) {
        if (entity.hasCapability(SHAKE, EnumFacing.DOWN))
            return entity.getCapability(SHAKE, EnumFacing.DOWN);
        return null;
    }
}
