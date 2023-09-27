package toraylife.mappetextras.modules.scripting.scripts.code;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.network.common.blocks.PacketEditEmitter;
import mchorse.mappet.tile.TileEmitter;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptEmitterTileEntity;

public class ScriptEmitterTileEntity extends ScriptTileEntity implements IScriptEmitterTileEntity {
    public ScriptEmitterTileEntity(TileEmitter tile) {
        super(tile);
    }


    public ScriptChecker getChecker() {
        return new ScriptChecker(((TileEmitter)this.getMinecraftTileEntity()).getChecker());
    }

    public void setChecker(ScriptChecker checker) {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.checker = checker.checker.toNBT();
        tile.setExpression(packetEditEmitter);
    }

    @Override
    public float getRadius() {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();
        return tile.getRadius();
    }

    @Override
    public void setRadius(float radius) {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.radius = radius;
        tile.setExpression(packetEditEmitter);
    }

    @Override
    public int getUpdate() {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();
        return tile.getUpdate();
    }

    @Override
    public void setUpdate(int update) {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.update = update;
        tile.setExpression(packetEditEmitter);
    }

    @Override
    public boolean getDisable() {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();
        return tile.getDisable();
    }

    @Override
    public void setDisable(boolean disable) {
        TileEmitter tile = (TileEmitter) this.getMinecraftTileEntity();

        /* TODO: Rewrite with access transformer */
        PacketEditEmitter packetEditEmitter = new PacketEditEmitter(tile);
        packetEditEmitter.disable = disable;
        tile.setExpression(packetEditEmitter);
    }
}
