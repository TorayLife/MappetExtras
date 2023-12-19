package toraylife.mappetextras.modules.scripting.mixins.late;

import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptNpc;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.entities.EntityNpc;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.triggers.ScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.triggers.IScriptTrigger;

@Mixin(value = ScriptNpc.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptNpc")
public abstract class MixinScriptNpc extends ScriptEntity<EntityNpc> {

    protected MixinScriptNpc(EntityNpc entity) {
        super(entity);
    }

    /**
     * Adds a new patrol point at the given coordinates.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public void addPatrolPoint(int x, int y, int z) {
        this.addPatrolPoint(x, y, z, null);
    }

    /**
     * Adds a new patrol point using the given vector position.
     *
     * @param pos Position vector
     */
    public void addPatrolPoint(ScriptVector pos) {
        this.addPatrolPoint(pos, null);
    }

    /**
     * Adds a new patrol point with the given position and trigger.
     *
     * @param pos     Position vector
     * @param trigger Trigger for this patrol point
     */
    public void addPatrolPoint(ScriptVector pos, IScriptTrigger trigger) {
        this.addPatrolPoint((int) pos.x, (int) pos.y, (int) pos.z, trigger);
    }

    /**
     * Adds a new patrol point with the given coordinates and trigger.
     *
     * @param x       X coordinate
     * @param y       Y coordinate
     * @param z       Z coordinate
     * @param trigger Trigger for this patrol point
     */
    public void addPatrolPoint(int x, int y, int z, IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.patrol.add(new BlockPos(x, y, z));
        npcState.patrolTriggers.add(trigger == null ? new Trigger() : trigger.getTrigger());
        this.entity.setState(npcState, true);
    }

    /**
     * Sets the patrol point at the given index to the new position and trigger.
     *
     * @param index   Index of patrol point to update
     * @param x       New X coordinate
     * @param y       New Y coordinate
     * @param z       New Z coordinate
     * @param trigger New trigger
     */
    public void setPatrolPoint(int index, int x, int y, int z, IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.patrol.set(index, new BlockPos(x, y, z));
        npcState.patrolTriggers.set(index, trigger == null ? new Trigger() : trigger.getTrigger());
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the position vector of the patrol point at the given index.
     *
     * @param index Index of patrol point
     * @return Position vector of the patrol point
     */
    public ScriptVector getPatrolPoint(int index) {
        BlockPos pos = this.entity.getState().patrol.get(index);
        return new ScriptVector(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Gets the trigger for the patrol point at the given index.
     *
     * @param index Index of patrol point
     * @return Patrol point trigger
     */
    public IScriptTrigger getPatrolTrigger(int index) {
        return new ScriptTrigger(this.entity.getState().patrolTriggers.get(index));
    }

    /**
     * Gets the initialization trigger.
     *
     * @return The initialization trigger
     */
    public IScriptTrigger getInitializationTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerInitialize);
    }

    /**
     * Sets the initialization trigger.
     *
     * @param trigger The new initialization trigger
     */
    public void setInitializationTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerInitialize = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the interaction trigger.
     *
     * @return The interaction trigger
     */
    public IScriptTrigger getInteractionTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerInteract);
    }

    /**
     * Sets the interaction trigger.
     *
     * @param trigger The new interaction trigger
     */
    public void setInteractionTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerInteract = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the damaged trigger.
     *
     * @return The damaged trigger
     */
    public IScriptTrigger getDamagedTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerDamaged);
    }

    /**
     * Sets the damaged trigger.
     *
     * @param trigger The new damaged trigger
     */
    public void setDamagedTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerDamaged = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the death trigger.
     *
     * @return The death trigger
     */
    public IScriptTrigger getDeathTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerDied);
    }

    /**
     * Sets the death trigger.
     *
     * @param trigger The new death trigger
     */
    public void setDeathTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerDied = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the tick trigger.
     *
     * @return The tick trigger
     */
    public IScriptTrigger getTickTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerTick);
    }

    /**
     * Sets the tick trigger.
     *
     * @param trigger The new tick trigger
     */
    public void setTickTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerTick = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the target trigger.
     *
     * @return The target trigger
     */
    public IScriptTrigger getTargetTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerTarget);
    }

    /**
     * Sets the target trigger.
     *
     * @param trigger The new target trigger
     */
    public void setTargetTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerTarget = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the collision trigger.
     *
     * @return The collision trigger
     */
    public IScriptTrigger getCollisionTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerEntityCollision);
    }

    /**
     * Sets the collision trigger.
     *
     * @param trigger The new collision trigger
     */
    public void setCollisionTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerEntityCollision = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }

    /**
     * Gets the respawn trigger.
     *
     * @return The respawn trigger
     */
    public IScriptTrigger getRespawnTrigger() {
        return new ScriptTrigger(this.entity.getState().triggerRespawn);
    }

    /**
     * Sets the respawn trigger.
     *
     * @param trigger The new respawn trigger
     */
    public void setRespawnTrigger(IScriptTrigger trigger) {
        NpcState npcState = this.entity.getState();
        npcState.triggerRespawn = trigger.getTrigger();
        this.entity.setState(npcState, true);
    }
}