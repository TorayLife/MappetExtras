package toraylife.mappetextras.modules.utils.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;

public class FlatDimensionProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return CustomDimensionManager.flatDimensionType;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorFlat(world, 0, false, "");
    }
}
