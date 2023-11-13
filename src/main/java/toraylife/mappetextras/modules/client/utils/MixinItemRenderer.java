package toraylife.mappetextras.modules.client.utils;

import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.client.ItemRendererAccessor;

@Mixin(value = ItemRenderer.class)
public abstract class MixinItemRenderer implements ItemRendererAccessor {
    @Shadow
    private float equippedProgressMainHand;

    @Shadow
    private float prevEquippedProgressMainHand;

    public float getPrevEquippedProgressMainHand(){
        return this.prevEquippedProgressMainHand;
    }

    public float getEquippedProgressMainHand(){
        return this.equippedProgressMainHand;
    }
}
