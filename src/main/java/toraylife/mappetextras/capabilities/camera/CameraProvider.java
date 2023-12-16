package toraylife.mappetextras.capabilities.camera;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CameraProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(ICamera.class)
    public static final Capability<ICamera> CAMERA = null;

    private ICamera instance = CAMERA.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CAMERA;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return hasCapability(capability, facing) ? CAMERA.<T>cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAMERA.getStorage().writeNBT(CAMERA, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAMERA.getStorage().readNBT(CAMERA, instance, null, nbt);
    }

    public static ICamera getHandler(Entity entity) {
        if (entity.hasCapability(CAMERA, EnumFacing.DOWN))
            return entity.getCapability(CAMERA, EnumFacing.DOWN);
        return null;
    }
}