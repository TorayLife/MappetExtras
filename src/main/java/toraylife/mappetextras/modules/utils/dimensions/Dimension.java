package toraylife.mappetextras.modules.utils.dimensions;

import mchorse.mappet.api.utils.AbstractData;
import mchorse.mclib.config.values.GenericValue;
import mchorse.mclib.config.values.ValueBoolean;
import mchorse.mclib.config.values.ValueInt;
import mchorse.mclib.utils.ValueSerializer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.DimensionManager;
public class Dimension extends AbstractData {
    public ValueInt dimensionId = new ValueInt("dimensionId");
    public ValueBoolean initializeOnStartup = new ValueBoolean("initializeOnStartup");
    public WorldProvider worldProvider = new WorldProviderSurface();


    public ValueSerializer serializer;

    public Dimension() {
        this.serializer = new ValueSerializer();
        this.dimensionId.set(DimensionManager.getNextFreeDimId());
        this.initializeOnStartup.set(false);
        this.registerValue(this.dimensionId);
        this.registerValue(this.initializeOnStartup);
    }


    public void registerValue(GenericValue value) {
        this.serializer.registerValue(value.id, value.id, value, true, true);
    }
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = this.serializer.toNBT();
        tag.setString("worldProvider", this.worldProvider.getClass().getName());
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.serializer.fromNBT(nbt);
        try {
            String name = nbt.getString("worldProvider");
            Class<?> worldProviderClass = Class.forName(name);
            this.worldProvider = (WorldProvider) worldProviderClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void register() {
        DimensionManager.registerDimension(this.dimensionId.get(), this.worldProvider.getDimensionType());
    }

    public void unregister() {
        DimensionManager.unregisterDimension(this.dimensionId.get());
    }
}
