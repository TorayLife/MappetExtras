package toraylife.mappetextras.capabilities.morphLocal;

import java.util.List;

import mchorse.metamorph.api.morphs.AbstractMorph;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface IMorphLocal {
    void applyMorphLocally(Entity entity, AbstractMorph morph);
    void removeMorphLocally(Entity entity);

    NBTTagCompound getMorph(int var1);

    List<Integer> getIds();

    NBTTagCompound serializeNBT();

    void deserializeNBT(NBTTagCompound var1);
}