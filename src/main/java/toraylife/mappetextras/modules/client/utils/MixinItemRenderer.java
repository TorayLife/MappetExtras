package toraylife.mappetextras.modules.client.utils;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.client.ItemRendererAccessor;

@Mixin(value = ItemRenderer.class)
public abstract class MixinItemRenderer implements ItemRendererAccessor {
    @Shadow
    private float equippedProgressMainHand;

    @Shadow
    private float prevEquippedProgressMainHand;

    @Shadow
    protected abstract void renderArmFirstPerson(float p_187456_1_, float p_187456_2_, EnumHandSide p_187456_3_);

    @Override
    public void invokeRenderArmFirstPerson(float a, float b, EnumHandSide handSide) {
        this.renderArmFirstPerson(a, b, handSide);
    }

    @Override
    public float getPrevEquippedProgressMainHand() {
        return this.prevEquippedProgressMainHand;
    }

    @Override
    public float getEquippedProgressMainHand() {
        return this.equippedProgressMainHand;
    }
}
