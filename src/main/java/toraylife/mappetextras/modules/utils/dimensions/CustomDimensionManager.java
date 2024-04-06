package toraylife.mappetextras.modules.utils.dimensions;

import mchorse.mappet.api.utils.manager.BaseManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import toraylife.mappetextras.modules.utils.UtilsModule;

import java.io.File;
import java.util.Collection;

public class CustomDimensionManager extends BaseManager<Dimension> {

    public static DimensionType voidDimensionType;
    public static DimensionType flatDimensionType;
    public CustomDimensionManager(File folder) {
        super(folder);
        try {
            //voidDimensionType = DimensionType.register("Void", "_mpe_void", UtilsModule.getInstance().voidDimensionId.get(), VoidDimensionProvider.class, true);
            //flatDimensionType = DimensionType.register("Flat", "_mpe_flat", UtilsModule.getInstance().flatDimensionId.get(), FlatDimensionProvider.class, true);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to register dimension types. Try change dimension type id's in config", e);
        }
    }

    @Override
    protected Dimension createData(String id, NBTTagCompound tag) {
        Dimension dimension = new Dimension();
        if (tag != null) {
            dimension.deserializeNBT(tag);
        }
        return dimension;
    }

    public void registerDimensions() {
        Collection<String> keys = this.getKeys();
        for (String key : keys) {
            Dimension dimension = this.load(key);
            if (dimension == null || !dimension.initializeOnStartup.get()) {
                continue;
            }
            try {
                dimension.register();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
