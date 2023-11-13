package toraylife.mappetextras.modules.client;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ItemRenderer.class)
public interface ItemRendererAccessor {
    @Invoker
    void invokeRenderArmFirstPerson(float a, float b, EnumHandSide handSide);
    float getEquippedProgressMainHand();
    float getPrevEquippedProgressMainHand();
}