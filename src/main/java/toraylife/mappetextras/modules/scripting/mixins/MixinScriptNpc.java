package toraylife.mappetextras.modules.scripting.mixins;

import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.code.entities.ScriptNpc;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.entities.EntityNpc;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import toraylife.mappetextras.modules.main.documentation.MixinTargetName;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptTrigger;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptTrigger;

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
    @Unique
    public void addPatrolPoint(int x, int y, int z) {
        this.addPatrolPoint(x, y, z, null);
    }

    /**
     * Adds a new patrol point using the given vector position.
     *
     * @param pos Position vector
     */
    @Unique
    public void addPatrolPoint(ScriptVector pos) {
        this.addPatrolPoint(pos, null);
    }

    /**
     * Adds a new patrol point with the given position and trigger.
     *
     * @param pos     Position vector
     * @param trigger Trigger for this patrol point
     */
    @Unique
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
    @Unique
    public void addPatrolPoint(int x, int y, int z, IScriptTrigger trigger) {
        this.entity.getState().patrol.add(new BlockPos(x, y, z));
        this.entity.getState().patrolTriggers.add(trigger == null ? new Trigger() : trigger.getTrigger());
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
        this.entity.getState().patrol.set(index, new BlockPos(x, y, z));
        this.entity.getState().patrolTriggers.set(index, trigger == null ? new Trigger() : trigger.getTrigger());
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
}