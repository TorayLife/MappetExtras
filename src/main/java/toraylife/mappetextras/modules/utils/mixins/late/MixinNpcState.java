package toraylife.mappetextras.modules.utils.mixins.late;

import io.netty.buffer.ByteBuf;
import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.utils.NpcStateUtils;
import mchorse.mclib.config.values.ValueFloat;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NpcState.class, remap = false)
public abstract class MixinNpcState {

    @Shadow
    public ValueFloat shadowSize;

    @Shadow
    public ValueFloat jumpPower;

    @Shadow
    public abstract void deserializeNBT(NBTTagCompound tag);

    @Inject(method = "readFromBuf", at = @At(value = "HEAD"), remap = false, cancellable = true)
    public void readFromBuf(ByteBuf buf, CallbackInfo ci) {
        this.deserializeNBT(NpcStateUtils.stateFromBuf(buf).serializeNBT());

        ci.cancel();
    }

    @Inject(method = "writeToBuf", at = @At(value = "HEAD"), remap = false, cancellable = true)
    public void writeToBuf(ByteBuf buf, CallbackInfo ci) {
        NpcStateUtils.stateToBuf(buf, (NpcState) (Object) this);

        ci.cancel();
    }
}
