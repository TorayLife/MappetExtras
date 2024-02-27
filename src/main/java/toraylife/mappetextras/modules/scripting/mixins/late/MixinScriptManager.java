package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.api.scripts.Script;
import mchorse.mappet.api.scripts.ScriptManager;
import mchorse.mappet.api.utils.manager.BaseManager;
import mchorse.mappet.api.utils.manager.FolderManager;
import mchorse.mclib.config.values.ValueString;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.scripting.ScriptingModule;

import java.io.File;
import java.util.Objects;

@Mixin(value = ScriptManager.class, remap = false)
public abstract class MixinScriptManager extends BaseManager<Script> {
    public MixinScriptManager(File folder) {
        super(folder);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(File folder, CallbackInfo ci) {
        ValueString scriptsFolderPath = ((ScriptingModule)ScriptingModule.getInstance()).scriptsFolderPath;

        if(!Objects.equals(scriptsFolderPath.get(), "") && MappetExtras.isValidPath(scriptsFolderPath.get())) {
            this.folder = new File(scriptsFolderPath.get());
            this.folder.mkdirs();
        }
    }
}
