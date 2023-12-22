package toraylife.mappetextras.capabilities.morphLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptNpc;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.entities.IScriptEntity;
import mchorse.mappet.api.scripts.user.entities.IScriptNpc;
import mchorse.mappet.api.scripts.user.entities.IScriptPlayer;
import mchorse.mappet.entities.EntityNpc;
import mchorse.mappet.network.common.npc.PacketNpcStateChange;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.network.common.survival.PacketMorphPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.client.network.PacketNPCStateChange;
import toraylife.mappetextras.network.Dispatcher;

public class MorphLocal implements IMorphLocal {
    private EntityPlayer player;
    private Map<Integer, NBTTagCompound> localMorphs = new HashMap();

    public static MorphLocal get(EntityPlayer player) {
        IMorphLocal morphCapability = player == null ? null : player.getCapability(MorphLocalProvider.MORPH_LOCAL, null);
        if (morphCapability instanceof MorphLocal) {
            MorphLocal profile = (MorphLocal) morphCapability;
            profile.player = player;
            return profile;
        } else {
            return null;
        }
    }

    @Override
    public void applyMorphLocally(Entity entity, AbstractMorph morph) {
        if(entity == null){
            return;
        }

        IScriptEntity scriptEntity = ScriptEntity.create(entity);
        int id = -1;
        if (scriptEntity instanceof IScriptPlayer) {
            id = ((IScriptPlayer)scriptEntity).getMinecraftPlayer().getEntityId();

            Dispatcher.sendTo(new PacketMorphPlayer(id, morph), (EntityPlayerMP) this.player);
        } else if (scriptEntity instanceof IScriptNpc) {
            id = entity.getEntityId();

            NpcState state = ((EntityNpc) entity).getState();
            state.morph = morph;

            Dispatcher.sendTo(new PacketNPCStateChange(id, state), (EntityPlayerMP) this.player);
        }

        this.localMorphs.put(id, morph.toNBT());
    }

    @Override
    public NBTTagCompound getMorph(int id) {
        return this.localMorphs.get(id);
    }

    @Override
    public List<Integer> getIds() {
        return new ArrayList<Integer>(this.localMorphs.keySet());
    }

    @Override
    public void removeMorphLocally(Entity entity) {
        if(entity == null) {
            return;
        }

        IScriptEntity scriptEntity = ScriptEntity.create(entity);
        int id = -1;
        if (scriptEntity instanceof IScriptPlayer) {
            id = ((IScriptPlayer) scriptEntity).getMinecraftPlayer().getEntityId();

            Dispatcher.sendTo(new PacketMorphPlayer(entity.getEntityId(), scriptEntity.getMorph()), (EntityPlayerMP) this.player);
        } else if (scriptEntity instanceof IScriptNpc) {
            id = entity.getEntityId();

            NpcState state = ((EntityNpc) entity).getState();
            state.morph = ((EntityNpc) entity).getMorph();

            Dispatcher.sendTo(new PacketNpcStateChange(id, state), (EntityPlayerMP) this.player);
        }

        this.localMorphs.remove(id);
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        Iterator var2 = this.localMorphs.keySet().iterator();

        while(var2.hasNext()) {
            Integer key = (Integer)var2.next();
            tag.setTag(key + "", this.localMorphs.get(key));
        }

        return tag;
    }

    public void deserializeNBT(NBTTagCompound tag) {
        Iterator var2 = tag.getKeySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            int id = Integer.parseInt(key);
            this.localMorphs.put(id, tag.getCompoundTag(key));
        }

    }
}
