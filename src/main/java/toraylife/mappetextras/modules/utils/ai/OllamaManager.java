package toraylife.mappetextras.modules.utils.ai;

import mchorse.mappet.api.utils.manager.BaseManager;
import mchorse.mappet.api.utils.manager.IManager;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;

public class OllamaManager extends BaseManager<OllamaConnection> implements IManager<OllamaConnection> {

    public OllamaManager(File folder) {
        super(folder);
    }

    @Override
    protected OllamaConnection createData(String id, NBTTagCompound tag) {
        OllamaConnection ollamaConnection = new OllamaConnection();
        ollamaConnection.deserializeNBT(tag);
        return ollamaConnection;
    }

    @Override
    public boolean save(String name, NBTTagCompound tag) {
        OllamaConnection ollamaConnection = new OllamaConnection();
        ollamaConnection.deserializeNBT(tag);
        return super.save(name, ollamaConnection.serializeNBT());
    }
}
