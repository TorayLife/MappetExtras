package toraylife.mappetextras.modules.utils;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;
import java.util.List;

public class DimensionCustom extends WorldProvider {


    public DimensionCustom() {
        this.biomeProvider = new BiomeProviderSingle(Biomes.VOID);
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionType.OVERWORLD;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    @Override
    public boolean isDaytime() {
        return false;
    }

    @Override
    public boolean isSkyColored() {
        return false;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorFlat(world, 0, false, "3;minecraft:air;127;decoration");
    }

    public void register(String name, int id) {
        DimensionManager.registerDimension(id, DimensionType.register(name, "_mpe", id, this.getClass(), true));
    }
}

class VoidChunkGenerator implements IChunkGenerator {
    private final World world;

    public VoidChunkGenerator(World world) {
        this.world = world;
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        return new Chunk(this.world, new ChunkPrimer(), x, z);
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
