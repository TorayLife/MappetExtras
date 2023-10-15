package toraylife.mappetextras.modules.scripting.scripts.code.blocks;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.network.common.blocks.PacketEditEmitter;
import mchorse.mappet.tile.TileEmitter;
import toraylife.mappetextras.modules.scripting.scripts.code.conditions.ScriptChecker;
import toraylife.mappetextras.modules.scripting.scripts.user.blocks.IScriptEmitterTileEntity;
import toraylife.mappetextras.modules.scripting.scripts.user.conditions.IScriptChecker;

public class ScriptEmitterTileEntity extends ScriptTileEntity implements IScriptEmitterTileEntity {
    public ScriptEmitterTileEntity(TileEmitter tile) {
        super(tile);
    }


    public ScriptChecker getChecker() {
        return new ScriptChecker(this.getMinecraftTileEntity().getChecker());
    }

    public void setChecker(IScriptChecker checker) {
        TileEmitter tile = this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.checker = ((ScriptChecker) checker).checker.toNBT();
        tile.setExpression(packetEditEmitter);
    }

    @Override
    public float getRadius() {
        TileEmitter tile = this.getMinecraftTileEntity();
        return tile.getRadius();
    }

    @Override
    public void setRadius(float radius) {
        TileEmitter tile = this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.radius = radius;
        tile.setExpression(packetEditEmitter);
    }

    @Override
    public int getUpdate() {
        TileEmitter tile = this.getMinecraftTileEntity();
        return tile.getUpdate();
    }

    @Override
    public void setUpdate(int update) {
        TileEmitter tile = this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.update = update;
        tile.setExpression(packetEditEmitter);
    }

    @Override
    public boolean isDisable() {
        TileEmitter tile = this.getMinecraftTileEntity();
        return tile.getDisable();
    }

    @Override
    public void setDisable(boolean disable) {
        TileEmitter tile = this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.disable = disable;
        tile.setExpression(packetEditEmitter);
    }

    @Override
    public TileEmitter getMinecraftTileEntity() {
        return (TileEmitter) super.getMinecraftTileEntity();
    }
}
