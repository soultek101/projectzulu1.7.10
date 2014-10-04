package com.stek101.projectzulu.common.mobs.entityai;

import net.minecraft.entity.ai.EntityAIBase;

import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;

public class EntityAIRestrictSun extends EntityAIBase
{
    private EntityGenericCreature theEntity;

    public EntityAIRestrictSun(EntityGenericCreature p_i1652_1_)
    {
        this.theEntity = p_i1652_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.theEntity.worldObj.isDaytime();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theEntity.getNavigator().setAvoidSun(true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.theEntity.getNavigator().setAvoidSun(false);
    }
}