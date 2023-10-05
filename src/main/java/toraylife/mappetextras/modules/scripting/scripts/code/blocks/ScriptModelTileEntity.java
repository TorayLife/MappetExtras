package toraylife.mappetextras.modules.scripting.scripts.code.blocks;

import mchorse.blockbuster.common.block.BlockModel;
import mchorse.blockbuster.common.tileentity.TileEntityModel;
import mchorse.blockbuster.network.Dispatcher;
import mchorse.blockbuster.network.common.PacketModifyModelBlock;
import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.metamorph.api.morphs.AbstractMorph;
import toraylife.mappetextras.modules.scripting.scripts.user.blocks.IScriptModelTileEntity;

public class ScriptModelTileEntity extends ScriptTileEntity implements IScriptModelTileEntity {
    public ScriptModelTileEntity(TileEntityModel tile) {
        super(tile);
    }


    @Override
    public void setMorph(AbstractMorph morph) {
        this.getMinecraftTileEntity().setMorph(morph);
    }

    @Override
    public AbstractMorph getMorph() {
        return this.getMinecraftTileEntity().morph.get();
    }

    @Override
    public int getLight() {
        return this.getMinecraftTileEntity().getSettings().getLightValue();
    }

    @Override
    public void setLight(int light) {
        TileEntityModel tileEntityModel = this.getMinecraftTileEntity();
        tileEntityModel.getSettings().setLightValue(light);
        tileEntityModel.getWorld().setBlockState(tileEntityModel.getPos(), tileEntityModel.getWorld().getBlockState(tileEntityModel.getPos()).withProperty(BlockModel.LIGHT, tileEntityModel.getSettings().getLightValue()), 2);
    }

    @Override
    public boolean isShadow() {
        return this.getMinecraftTileEntity().getSettings().isShadow();
    }

    @Override
    public void setShadow(boolean shadow) {
        this.getMinecraftTileEntity().getSettings().setShadow(shadow);
    }

    @Override
    public boolean isGlobal() {
        return this.getMinecraftTileEntity().getSettings().isGlobal();
    }

    @Override
    public void setGlobal(boolean global) {
        this.getMinecraftTileEntity().getSettings().setGlobal(global);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.getMinecraftTileEntity().getSettings().setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return this.getMinecraftTileEntity().getSettings().isEnabled();
    }

    @Override
    public void setPreventReset(boolean preventReset) {
        this.getMinecraftTileEntity().getSettings().setExcludeResetPlayback(preventReset);
    }

    @Override
    public boolean isPreventReset() {
        return this.getMinecraftTileEntity().getSettings().isExcludeResetPlayback();
    }

    @Override
    public void setRenderLast(boolean renderLast) {
        this.getMinecraftTileEntity().getSettings().setRenderLast(renderLast);
    }

    @Override
    public boolean isRenderLast() {
        return this.getMinecraftTileEntity().getSettings().isRenderLast();
    }

    @Override
    public void setRenderAlways(boolean renderAlways) {
        this.getMinecraftTileEntity().getSettings().setRenderAlways(renderAlways);
    }

    @Override
    public boolean isRenderAlways() {
        return this.getMinecraftTileEntity().getSettings().isRenderAlways();
    }

    @Override
    public void setEnabledBlockHitbox(boolean enabled) {
        this.getMinecraftTileEntity().getSettings().setEnableBlockHitbox(enabled);
    }

    @Override
    public boolean isEnabledBlockHitbox() {
        return this.getMinecraftTileEntity().getSettings().isBlockHitbox();
    }

    @Override
    public TileEntityModel getMinecraftTileEntity() {
        return (TileEntityModel) super.getMinecraftTileEntity();
    }

    @Override
    public void sendToAll() {
        Dispatcher.sendToAll(new PacketModifyModelBlock(this.getMinecraftTileEntity().getPos(), this.getMinecraftTileEntity()));
    }
}
