package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.mclib.utils.Interpolation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.network.PacketPlayAnimation;
import toraylife.mappetextras.network.Dispatcher;

import java.util.Arrays;
import java.util.Iterator;

import java.util.Arrays;
import java.util.stream.Collectors;

@Mixin(value = ScriptEntity.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptEntity")
public abstract class MixinScriptEntity<T extends Entity> {
    @Shadow
    protected T entity;

    @Shadow
    public abstract void setRotations(float pitch, float yaw, float yawHead);

    @Shadow public abstract AbstractMorph getMorph();

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
     * Make this entity jump.
     */
    public void jump() {
        ((EntityLiving) this.entity).getJumpHelper().setJumping();
    }

    /**
     * Check if this entity is a child.
     *
     * @return True if a child, false otherwise
     */
    public boolean isChild() {
        return ((EntityLivingBase) this.entity).isChild();
    }

    /**
     * Check if this entity is dead.
     *
     * @return True if dead, false if alive
     */
    public boolean isDead() {
        return this.entity.isDead;
    }

    /**
     * Check if this entity is silent.
     *
     * @return True if silent, false otherwise
     */
    public boolean isSilent() {
        return this.entity.isSilent();
    }

    /**
     * Set whether this entity is silent.
     *
     * @param silent True to make silent, false otherwise
     */
    public void setSilent(boolean silent) {
        this.entity.setSilent(silent);
    }

    /**
     * Check if this entity is attackable.
     *
     * @return True if attackable, false otherwise
     */
    public boolean isAttackable() {
        return ((EntityLivingBase) this.entity).attackable();
    }

    /**
     * Check if this entity is alive.
     *
     * @return True if alive, false if dead
     */
    public boolean isAlive() {
        return this.entity.isEntityAlive();
    }

    /**
     * Check if this entity is undead.
     *
     * @return True if undead, false otherwise
     */
    public boolean isUndead() {
        return ((EntityLivingBase) this.entity).isEntityUndead();
    }

    /**
     * Get the fall distance for this entity.
     *
     * @return The fall distance
     */
    public float getFallDistance() {
        return this.entity.fallDistance;
    }

    /**
     * Get the entity ID for this entity.
     *
     * @return The entity ID
     */
    public int getEntityId() {
        return this.entity.getEntityId();
    }

    /**
     * Set the entity ID for this entity.
     *
     * @param id The entity ID to set
     */
    public void setEntityId(int id) {
        this.entity.setEntityId(id);
    }

    /**
     * Get the AI move speed for this living entity.
     *
     * @return The AI move speed
     */
    public float getAIMoveSpeed() {
        return ((EntityLivingBase) this.entity).getAIMoveSpeed();
    }

    /**
     * Set the AI move speed for this living entity.
     *
     * @param speed The move speed to set
     */
    public void setAIMoveSpeed(float speed) {
        ((EntityLivingBase) this.entity).setAIMoveSpeed(speed);
    }

    /**
     * Set whether this entity can clip through blocks.
     *
     * @param clip True if it can clip, false otherwise
     */
    public void setNoClip(boolean clip) {
        this.entity.noClip = clip;
    }

    /**
     * Get whether this entity can clip through blocks.
     *
     * @return True if it can clip, false otherwise
     */
    public boolean getNoClip() {
        return this.entity.noClip;
    }

    /**
     * Damage this entity on type.
     *
     * types:
     * IN_FIRE,
     * LIGHTNING_BOLT,
     * ON_FIRE,
     * LAVA,
     * HOT_FLOOR,
     * IN_WALL,
     * CRAMMING,
     * DROWN,
     * STARVE,
     * CACTUS,
     * FALL,
     * FLY_INTO_WALL,
     * OUT_OF_WORLD,
     * GENERIC,
     * MAGIC,
     * WITHER,
     * ANVIL,
     * FALLING_BLOCK,
     * DRAGON_BREATH,
     * FIREWORKS
     *
     * @param health Amount of damage
     * @param damageType Type of damage
     */
    public void damage(float health, String damageType) {
        if (this.entity instanceof EntityLivingBase) {
            return;
        }

        this.entity.attackEntityFrom(new DamageSource(damageType.toUpperCase()), health);
    }

    /**
     * Set whether this entity is sprinting.
     *
     * @param sprinting True if sprinting, false otherwise
     */
    public void setSprinting(boolean sprinting) {
        this.entity.setSprinting(sprinting);
    }

    /**
     * Set whether this entity is sneaking.
     *
     * @param sneaking True if sneaking, false otherwise
     */
    public void setSneaking(boolean sneaking) {
        this.entity.setSneaking(sneaking);
    }

    /**
     * Gets the horizontal facing direction this entity is facing as name.
     *
     * @return Facing direction name (south, north, east, west).
     */
    public String getFacing() {
        return this.entity.getHorizontalFacing().getName();
    }

    /**
     * Checks if this entity is currently walking.
     *
     * @return True if walking, false otherwise.
     */
    public boolean isWalking() {
        return this.entity.prevDistanceWalkedModified
                - this.entity.distanceWalkedModified != 0;
    }

    /**
     * @author TorayLife
     * @reason Custom dimensions compatibility
     */
    @Overwrite
    public void setDimension(int dimension) {
        if (this.entity.dimension != dimension) {
            if (DimensionManager.isDimensionRegistered(dimension)) {
                MinecraftServer minecraftServer = this.entity.getServer();
                WorldServer worldServer = minecraftServer.getWorld(dimension);
                Teleporter teleporter = new Teleporter(worldServer) {
                    public void placeInPortal(Entity entityIn, float rotationYaw) {
                    }

                    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
                        return false;
                    }
                };
                if (this.entity instanceof EntityPlayerMP) {
                    EntityPlayerMP player = (EntityPlayerMP)this.entity;
                    minecraftServer.getPlayerList().transferPlayerToDimension(player, dimension, teleporter);
                } else {
                    this.entity.changeDimension(dimension, teleporter);
                }

            } else {
                throw new IllegalArgumentException("Registered dimensions: " + Arrays.toString(DimensionManager.getIDs()));
            }
        }
    }

    /**
     * Play an animation on this entity, if it is an entity that can apply morphs, and it's morph is a ChameleonMorph.
     *
     * <pre>{@code
     *     function main(c) {
     *         c.getSubject().playAnimation("running");
     *     }
     * }</pre>
     *
     * @param animation name of animation
     */

    public void playAnimation(String animation) {
        Dispatcher.sendToTracked(this.entity, new PacketPlayAnimation(animation, this.entity.getUniqueID().toString()));
        if (this.entity instanceof EntityPlayerMP) {
            Dispatcher.sendTo(new PacketPlayAnimation(animation, this.entity.getUniqueID().toString()), (EntityPlayerMP) this.entity);
        }
    }
}