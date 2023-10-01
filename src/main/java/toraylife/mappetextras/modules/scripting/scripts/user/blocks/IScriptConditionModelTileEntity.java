package toraylife.mappetextras.modules.scripting.scripts.user.blocks;

import mchorse.mappet.api.scripts.user.blocks.IScriptTileEntity;
import mchorse.mappet.tile.TileConditionModel;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptConditionModel;

import java.util.List;

public interface IScriptConditionModelTileEntity extends IScriptTileEntity {

    /**
     * Retrieves the list of IScriptConditionModel objects.
     *
     * @return The list of IScriptConditionModel objects.
     */
    List<IScriptConditionModel> getConditionModels();

    /**
     * Sets the list of condition models for the tile entity.
     */
    void setConditionModels(List<IScriptConditionModel> conditionModels);

    /**
     * Sets the list of condition models for the tile entity.
     */
    void setConditionModels(IScriptConditionModel... conditionModels);

    /**
     * Adds a new IScriptConditionModel object.
     */
    IScriptConditionModel addNew();

    /**
     * Adds one or more IScriptConditionModel objects to the list of conditions.
     *
     * @param conditionModel the IScriptConditionModel objects to add
     */
    void add(IScriptConditionModel... conditionModel);

    /**
     * Removes a condition model at the specified index.
     *
     * @param index the index of the element to be removed
     */
    void remove(int index);

    /**
     * Sets the value at the specified index with the given condition model.
     *
     * @param index          the index at which to set the value
     * @param conditionModel the condition model to set
     */
    void set(int index, IScriptConditionModel conditionModel);

    /**
     * Retrieves the IScriptConditionModel object at the specified index.
     *
     * @param index the index of the IScriptConditionModel object to retrieve
     * @return the IScriptConditionModel object at the specified index
     */
    IScriptConditionModel get(int index);

    /**
     * Get Minecraft tile entity instance. <b>BEWARE:</b> you need to know the MCP
     * mappings in order to directly call methods on this instance!
     *
     * <p>See {@link IScriptTileEntity} for more usage info.</p>
     */
    @Override
    TileConditionModel getMinecraftTileEntity();

    /**
     * Retrieves the update frequency.
     */
    int getFrequency();

    /**
     * Sets the update frequency.
     */
    void setFrequency(int frequency);

    /**
     * Retrieves true, if global toggle is enabled.
     */
    boolean getGlobal();

    /**
     * Sets the value of the global toggle.
     */
    void setGlobal(boolean global);

    /**
     * Retrieves true, if shadow toggle is enabled.
     */
    boolean getShadow();

    /**
     * Sets the value of the shadow toggle.
     */
    void setShadow(boolean shadow);
}
