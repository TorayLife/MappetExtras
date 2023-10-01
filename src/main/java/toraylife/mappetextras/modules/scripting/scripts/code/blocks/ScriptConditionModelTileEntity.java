package toraylife.mappetextras.modules.scripting.scripts.code.blocks;

import mchorse.mappet.api.scripts.code.blocks.ScriptTileEntity;
import mchorse.mappet.tile.TileConditionModel;
import mchorse.mappet.utils.ConditionModel;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptConditionModel;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptConditionModel;
import toraylife.mappetextras.modules.scripting.scripts.user.blocks.IScriptConditionModelTileEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScriptConditionModelTileEntity extends ScriptTileEntity implements IScriptConditionModelTileEntity {
    public ScriptConditionModelTileEntity(TileConditionModel tile) {
        super(tile);
    }

    @Override
    public List<IScriptConditionModel> getConditionModels() {
        return this.getMinecraftTileEntity().list.stream().map(ScriptConditionModel::new).collect(Collectors.toList());
    }

    @Override
    public void setConditionModels(List<IScriptConditionModel> conditionModels) {
        this.getMinecraftTileEntity().list = conditionModels.stream().map(IScriptConditionModel::getConditionModel).collect(Collectors.toList());
    }

    @Override
    public void setConditionModels(IScriptConditionModel... conditionModels) {
        this.setConditionModels(Arrays.asList(conditionModels));
    }

    @Override
    public IScriptConditionModel addNew() {
        this.getMinecraftTileEntity().list.add(new ConditionModel());
        return this.get(this.getMinecraftTileEntity().list.size() - 1);
    }

    @Override
    public void add(IScriptConditionModel... conditionModel) {
        this.getMinecraftTileEntity().list.addAll(Arrays.stream(conditionModel).map(IScriptConditionModel::getConditionModel).collect(Collectors.toList()));
    }

    @Override
    public void remove(int index) {
        this.getMinecraftTileEntity().list.remove(index);
    }

    @Override
    public void set(int index, IScriptConditionModel conditionModel) {
        this.getMinecraftTileEntity().list.set(index, conditionModel.getConditionModel());
    }

    @Override
    public IScriptConditionModel get(int index) {
        return new ScriptConditionModel(this.getMinecraftTileEntity().list.get(index));
    }

    public TileConditionModel getMinecraftTileEntity() {
        return (TileConditionModel) super.getMinecraftTileEntity();
    }

    @Override
    public int getFrequency() {
        return this.getMinecraftTileEntity().frequency;
    }

    @Override
    public void setFrequency(int frequency) {
        this.getMinecraftTileEntity().frequency = frequency;
    }

    @Override
    public boolean getGlobal() {
        return this.getMinecraftTileEntity().isGlobal;
    }

    @Override
    public void setGlobal(boolean global) {
        this.getMinecraftTileEntity().isGlobal = global;
    }

    @Override
    public boolean getShadow() {
        return this.getMinecraftTileEntity().isShadow;
    }

    @Override
    public void setShadow(boolean shadow) {
        this.getMinecraftTileEntity().isShadow = shadow;
    }
}
