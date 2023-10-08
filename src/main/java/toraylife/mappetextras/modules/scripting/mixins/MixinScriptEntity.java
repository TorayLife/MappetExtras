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
public abstract class MixinScriptEntity<T extends Entity> {
    @Shadow
    protected T entity;

    @Shadow
    public abstract void setRotations(float pitch, float yaw, float yawHead);

    /**
     * Gets the age of this entity in ticks.
     *
     * @return The age in ticks
     */
    public int getAge() {
        return this.entity.ticksExisted;
    }

    /**
     * Sets whether this entity is glowing or not.
     *
     * @param glowing True to make this entity glow, false to stop glowing
     */
    public void setGlowing(boolean glowing) {
        this.entity.setGlowing(glowing);
    }

    /**
     * Checks if this entity is glowing.
     *
     * @return True if glowing, false otherwise
     */
    public boolean isGlowing() {
        return this.entity.isGlowing();
    }

    /**
     * Checks if this entity is being spectated by the given player.
     *
     * @param player The player to check
     * @return True if being spectated by the player, false otherwise
     */
    public boolean isSpectated(ScriptPlayer player) {
        return this.entity.isSpectatedByPlayer(player.getMinecraftPlayer());
    }

    /**
     * Rotates this entity over the given duration to the target pitch, yaw and yawHead using the given interpolation.
     *
     * @param interpolation Interpolation method
     * @param durationTicks Duration in ticks
     * @param pitch         Target pitch
     * @param yaw           Target yaw
     * @param yawHead       Target yaw of the head
     */
    public void rotateTo(String interpolation, int durationTicks, float pitch, float yaw, float yawHead) {
        Interpolation interp = Interpolation.valueOf(interpolation.toUpperCase());
        float startPitch = this.entity.rotationPitch;
        float startYaw = this.entity.rotationYaw;
        float startYawHead = this.entity.getRotationYawHead();

        for (int i = 0; i < durationTicks; i++) {
            float progress = (float) i / (float) durationTicks;
            float interpPitch = interp.interpolate(startPitch, pitch, progress);
            float interpYaw = interp.interpolate(startYaw, yaw, progress);
            float interpYawHead = interp.interpolate(startYawHead, yawHead, progress);

            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(i, () -> this.setRotations(interpPitch, interpYaw, interpYawHead)));
        }
    }

    /**
     * Makes this entity jump.
     */
    public void jump() {
        ((EntityLiving) this.entity).getJumpHelper().setJumping();
    }

    /**
     * Checks if this entity is a child.
     *
     * @return True if a child, false otherwise
     */
    public boolean isChild() {
        return ((EntityLivingBase) this.entity).isChild();
    }

    /**
     * Checks if this entity is dead.
     *
     * @return True if dead, false if alive
     */
    public boolean isDead() {
        return this.entity.isDead;
    }

    /**
     * Checks if this entity is silent.
     *
     * @return True if silent, false otherwise
     */
    public boolean isSilent() {
        return this.entity.isSilent();
    }

    /**
     * Sets whether this entity is silent.
     *
     * @param silent True to make silent, false otherwise
     */
    public void setSilent(boolean silent) {
        this.entity.setSilent(silent);
    }

    /**
     * Checks if this entity is attackable.
     *
     * @return True if attackable, false otherwise
     */
    public boolean isAttackable() {
        return ((EntityLivingBase) this.entity).attackable();
    }

    /**
     * Checks if this entity is alive.
     *
     * @return True if alive, false if dead
     */
    public boolean isAlive() {
        return this.entity.isEntityAlive();
    }

    /**
     * Checks if this entity is undead.
     *
     * @return True if undead, false otherwise
     */
    public boolean isUndead() {
        return ((EntityLivingBase) this.entity).isEntityUndead();
    }

    public float getFallDistance() {
        return this.entity.fallDistance;
    }

    public int getEntityId() {
        return this.entity.getEntityId();
    }

    public void setEntityId(int id) {
        this.entity.setEntityId(id);
    }

    public float getAIMoveSpeed() {
        return ((EntityLivingBase) this.entity).getAIMoveSpeed();
    }

    public void setAIMoveSpeed(float speed) {
        ((EntityLivingBase) this.entity).setAIMoveSpeed(speed);
    }

    public void setNoClip(boolean clip) {
        this.entity.noClip = clip;
    }

    public boolean getNoClip() {
        return this.entity.noClip;
    }

    public void onKillEntity(IScriptEntity entity) {
        Entity target = this.entity;
        Entity attacker = entity.getMinecraftEntity();

        target.onKillEntity((EntityLivingBase) attacker);
    }
}