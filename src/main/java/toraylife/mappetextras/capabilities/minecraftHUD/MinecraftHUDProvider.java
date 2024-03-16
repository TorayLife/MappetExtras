package toraylife.mappetextras.capabilities.minecraftHUD;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MinecraftHUDProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IMinecraftHUD.class)
    public static final Capability<IMinecraftHUD> MINECRAFTHUD = null;

    private IMinecraftHUD instance = MINECRAFTHUD.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == MINECRAFTHUD;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return hasCapability(capability, facing) ? MINECRAFTHUD.<T>cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MINECRAFTHUD.getStorage().writeNBT(MINECRAFTHUD, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        MINECRAFTHUD.getStorage().readNBT(MINECRAFTHUD, instance, null, nbt);
    }

    public static IMinecraftHUD getHandler(Entity entity) {
        if (entity.hasCapability(MINECRAFTHUD, EnumFacing.DOWN))
            return entity.getCapability(MINECRAFTHUD, EnumFacing.DOWN);
        return null;
    }
}
