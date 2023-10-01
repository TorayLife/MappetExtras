package toraylife.mappetextras.modules.scripting.scripts.code.blocks;

import com.google.common.collect.ImmutableMap;
import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.blocks.BlockTrigger;
import mchorse.mappet.tile.TileTrigger;
import net.minecraft.block.properties.IProperty;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.blocks.IScriptTriggerTileEntity;

public class ScriptTriggerTileEntity extends ScriptTileEntity implements IScriptTriggerTileEntity {
    public ScriptTriggerTileEntity(TileTrigger tile) {
        super(tile);
    }

    public IScriptTrigger getLeft() {
        return new ScriptTrigger(this.getMinecraftTileEntity().leftClick);
    }

    public void setLeft(ScriptTrigger trigger) {
        this.getMinecraftTileEntity().leftClick = trigger.trigger;
    }

    public IScriptTrigger getRight() {
        return new ScriptTrigger(this.getMinecraftTileEntity().rightClick);
    }

    public void setRight(ScriptTrigger trigger) {
        this.getMinecraftTileEntity().rightClick = trigger.trigger;
    }

    @Override
    public boolean getCollidable() {
        TileTrigger tile = this.getMinecraftTileEntity();
        ImmutableMap<IProperty<?>, Comparable<?>> properties = tile.getWorld().getBlockState(tile.getPos()).getProperties();

        for (IProperty<?> p : properties.keySet()) {
            if (p.equals(BlockTrigger.COLLIDABLE)) {
                return properties.get(p).equals(true);
            }
        }
        return false;
    }

    @Override
    public void setCollidable(boolean collidable) {
        TileTrigger tile = this.getMinecraftTileEntity();
        tile.getWorld().setBlockState(tile.getPos(), tile.getWorld().getBlockState(tile.getPos()).withProperty(BlockTrigger.COLLIDABLE, collidable));
    }

    @Override
    public TileTrigger getMinecraftTileEntity() {
        return (TileTrigger) super.getMinecraftTileEntity();
    }
}
