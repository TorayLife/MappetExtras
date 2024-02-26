package toraylife.mappetextras.modules.utils.mixins.late;

import mchorse.mappet.RegisterHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.utils.dimensions.CustomDimensionManager;

import java.util.Calendar;

@Mixin(value = RegisterHandler.class, remap = false)
public abstract class MixinRegisterHandler {
    @Inject(method = "getNpcToolTexture", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void getNpcToolTexture(CallbackInfoReturnable<ModelResourceLocation> cir, String postfix, Calendar calendar) {
        toraylife.mappetextras.events.RegisterHandler reg = new toraylife.mappetextras.events.RegisterHandler();

        if (reg.isMappetBirthday()) {
            postfix = "_mchorse";
        }else if(reg.isLlamaBirthday()){
            postfix = "_llama";
        }else if(reg.isDemonBirthday()){
            postfix = "_demon";
        } else if(reg.isDyamoBirthday()){
            postfix = "_dyamo";
        }

        cir.setReturnValue(new ModelResourceLocation("mappet:npc_tool" + postfix, "inventory"));
        cir.cancel();
    }

    @Inject(method = "onClientConnect", at = @At(value = "INVOKE", target = "Lmchorse/mappet/api/huds/HUDManager;<init>(Ljava/io/File;)V"), cancellable = true)
    public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event, CallbackInfo ci) {
        MappetExtras.customDimensionManager = new CustomDimensionManager(null);
    }

    @Inject(method = "onClientDisconnect", at = @At(value = "INVOKE", target = "Ljava/util/Set;clear()V"), cancellable = true)
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event, CallbackInfo ci) {
        MappetExtras.customDimensionManager = null;
    }
}
