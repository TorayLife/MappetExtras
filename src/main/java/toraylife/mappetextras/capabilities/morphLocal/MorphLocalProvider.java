package toraylife.mappetextras.capabilities.morphLocal;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MorphLocalProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IMorphLocal.class)
    public static final Capability<IMorphLocal> MORPH_LOCAL = null;
    private IMorphLocal instance;

    public MorphLocalProvider() {
        this.instance = MORPH_LOCAL.getDefaultInstance();
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == MORPH_LOCAL;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return this.hasCapability(capability, facing) ? MORPH_LOCAL.cast(this.instance) : null;
    }

    public NBTBase serializeNBT() {
        return MORPH_LOCAL.getStorage().writeNBT(MORPH_LOCAL, this.instance, null);
    }

    public void deserializeNBT(NBTBase nbt) {
        MORPH_LOCAL.getStorage().readNBT(MORPH_LOCAL, this.instance, null, nbt);
    }

    public static IMorphLocal getHandler(Entity entity) {
        return entity.hasCapability(MORPH_LOCAL, EnumFacing.DOWN) ? entity.getCapability(MORPH_LOCAL, EnumFacing.DOWN) : null;
    }
}
