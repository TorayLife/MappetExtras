package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.mclib.utils.Interpolation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;

@Mixin(value = ScriptEntity.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptEntity")
public abstract class MixinScriptEntity <T extends Entity>{
    @Shadow protected T entity;

    @Shadow public abstract void setRotations(float pitch, float yaw, float yawHead);

    public int getAge(){
        return this.entity.ticksExisted;
    }

    public void setGlowing(boolean glowing){
        this.entity.setGlowing(glowing);
    }

    public boolean isGlowing(){
        return this.entity.isGlowing();
    }

    public boolean isSpectated(ScriptPlayer player){
        return this.entity.isSpectatedByPlayer(player.getMinecraftPlayer());
    }

    public void rotateTo(String interpolation, int durationTicks, float pitch, float yaw, float yawHead)
    {
        Interpolation interp = Interpolation.valueOf(interpolation.toUpperCase());
        float startPitch = this.entity.rotationPitch;
        float startYaw = this.entity.rotationYaw;
        float startYawHead = this.entity.getRotationYawHead();

        for (int i = 0; i < durationTicks; i++)
        {
            float progress = (float) i / (float) durationTicks;
            float interpPitch = interp.interpolate(startPitch, pitch, progress);
            float interpYaw = interp.interpolate(startYaw, yaw, progress);
            float interpYawHead = interp.interpolate(startYawHead, yawHead, progress);

            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(i, () -> this.setRotations(interpPitch, interpYaw, interpYawHead)));
        }
    }

    public void jump(){
        ((EntityLiving)this.entity).getJumpHelper().setJumping();
    }

    public boolean isChild(){
        return ((EntityLivingBase)this.entity).isChild();
    }

    public boolean isDead(){
        return this.entity.isDead;
    }

    public boolean isSilent(){
        return this.entity.isSilent();
    }

    public void setSilent(boolean silent){
        this.entity.setSilent(silent);
    }

    public boolean isAttackable(){
        return ((EntityLivingBase)this.entity).attackable();
    }

    public boolean isAlive(){
        return this.entity.isEntityAlive();
    }

    public boolean isUndead(){
        return ((EntityLivingBase)this.entity).isEntityUndead();
    }

    public float getFallDistance(){
        return this.entity.fallDistance;
    }

    public int getEntityId(){
        return this.entity.getEntityId();
    }

    public void setEntityId(int id){
        this.entity.setEntityId(id);
    }

    public float getAIMoveSpeed(){
        return ((EntityLivingBase)this.entity).getAIMoveSpeed();
    }

    public void setAIMoveSpeed(float speed){
        ((EntityLivingBase)this.entity).setAIMoveSpeed(speed);
    }

    public void setNoClip(boolean clip){
        this.entity.noClip = clip;
    }

    public boolean getNoClip(){
        return this.entity.noClip;
    }
}