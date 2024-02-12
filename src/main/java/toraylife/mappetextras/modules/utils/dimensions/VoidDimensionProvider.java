package toraylife.mappetextras.modules.utils.dimensions;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;

public class VoidDimensionProvider extends FlatDimensionProvider {
    public VoidDimensionProvider() {
        super();
        this.biomeProvider = new BiomeProviderSingle(Biomes.VOID);
    }

    @Override
    public DimensionType getDimensionType() {
        return CustomDimensionManager.voidDimensionType;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorFlat(world, 0, false, "3;minecraft:air;127;decoration");
    }
}
