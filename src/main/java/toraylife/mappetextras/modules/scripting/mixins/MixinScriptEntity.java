package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.entities.IScriptEntity;
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

    /**
     * Gets the existence time of the entity (in ticks).
     *
     * @return age in ticks.
     */
    public int getAge(){
        return this.entity.ticksExisted;
    }

    /**
     * Sets whether this entity is glowing or not.
     *
     * @param glowing true to make the entity glow, false otherwise
     */
    public void setGlowing(boolean glowing){
        this.entity.setGlowing(glowing);
    }

    /**
     * Checks if this entity is glowing.
     *
     * @return true if glowing, false otherwise
     */
    public boolean isGlowing(){
        return this.entity.isGlowing();
    }

    /**
     * Checks if this entity is being spectated by the given player.
     *
     * @param player the player to check
     * @return true if being spectated by the player, false otherwise
     */
    public boolean isSpectated(ScriptPlayer player){
        return this.entity.isSpectatedByPlayer(player.getMinecraftPlayer());
    }

    /**
     * Rotates this entity over the given duration, interpolating between the start and end angles.
     *
     * @param interpolation name interpolation
     * @param durationTicks the duration in ticks
     * @param pitch the ending pitch angle
     * @param yaw the ending yaw angle
     * @param yawHead the ending head yaw angle
     */
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

    /**
     * Causes this entity to jump.
     */
    public void jump(){
        ((EntityLiving)this.entity).getJumpHelper().setJumping();
    }

    /**
     * Checks if this entity is a child form.
     *
     * @return true if this entity is a child, false otherwise
     */
    public boolean isChild(){
        return ((EntityLivingBase)this.entity).isChild();
    }

    /**
     * Проверяет, не умерла ли данная сущность.
     *
     * @return true, если мертв, false, если еще жив.
     */
    public boolean isDead(){
        return this.entity.isDead;
    }

    /**
     * Checks if this entity is silent.
     *
     * @return true if silent, false otherwise
     */
    public boolean isSilent(){
        return this.entity.isSilent();
    }

    /**
     * Sets whether this entity silent.
     *
     * @param silent true to make silent, false otherwise
     */
    public void setSilent(boolean silent){
        this.entity.setSilent(silent);
    }

    /**
     * Checks if this entity is attackable.
     *
     * @return true if attackable, false otherwise
     */
    public boolean isAttackable(){
        return ((EntityLivingBase)this.entity).attackable();
    }

    /**
     * Checks if this entity is alive.
     *
     * @return true if alive, false if dead
     */
    public boolean isAlive(){
        return this.entity.isEntityAlive();
    }

    /**
     * Checks if this entity is undead.
     *
     * @return true if undead, false otherwise
     */
    public boolean isUndead(){
        return ((EntityLivingBase)this.entity).isEntityUndead();
    }

    /**
     * Gets the fall distance for this entity.
     *
     * @return the fall distance
     */
    public float getFallDistance(){
        return this.entity.fallDistance;
    }

    /**
     * Gets the entity ID for this entity.
     *
     * @return the entity ID
     */
    public int getEntityId(){
        return this.entity.getEntityId();
    }

    /**
     * Sets the entity ID for this entity.
     *
     * @param id the new entity ID
     */
    public void setEntityId(int id){
        this.entity.setEntityId(id);
    }

    /**
     * Gets the AI move speed for this entity.
     *
     * @return the AI move speed
     */
    public float getAIMoveSpeed(){
        return ((EntityLivingBase)this.entity).getAIMoveSpeed();
    }

    /**
     * Sets the AI move speed for this entity.
     *
     * @param speed the new AI move speed
     */
    public void setAIMoveSpeed(float speed){
        ((EntityLivingBase)this.entity).setAIMoveSpeed(speed);
    }

    /**
     * Sets whether the entity will pass through blocks
     *
     * @param clip true to enable no clip, false to disable
     */
    public void setNoClip(boolean clip){
        this.entity.noClip = clip;
    }

    /**
     * Gets whether an entity can pass through blocks
     *
     * @return true if no clip is enabled, false otherwise
     */
    public boolean getNoClip(){
        return this.entity.noClip;
    }
}