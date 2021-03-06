package com.stek101.projectzulu.common.mobs.entityai;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericTameable;
import com.stek101.projectzulu.common.mobs.entity.EntityStates;

public class EntityAIAvoidEntity extends EntityAIBase{

    public final IEntitySelector field_98218_a = new IEntitySelector()
    {
        //private static final String __OBFID = "CL_00001575";
        /**
         * Return whether the specified entity is applicable to this filter.
         */
        public boolean isEntityApplicable(Entity p_82704_1_)
        {
            return p_82704_1_.isEntityAlive() && EntityAIAvoidEntity.this.theEntity.getEntitySenses().canSee(p_82704_1_);
        }
    };
    
    /** The entity we are attached to */
    private EntityGenericCreature theEntity;
    private double farSpeed;
    private double nearSpeed;
    private Entity closestLivingEntity;
    private float distanceFromEntity;
    
    /** The PathEntity of our entity */
    private PathEntity entityPathEntity;
    
    /** The PathNavigate of our entity */
    private PathNavigate entityPathNavigate;
    
    /** The class of the entity we should avoid */
    private Class targetEntityClass;
    //private static final String __OBFID = "CL_00001574";
    
    private boolean shouldHop = false;
    private int slimeJumpDelay = 0; 
    
    public EntityAIAvoidEntity(EntityGenericCreature par1, Class par2, float par3, double par4, double par5)
    {
        this.theEntity = par1;
        this.targetEntityClass = par2;
        this.distanceFromEntity = par3;
        this.farSpeed = par4;
        this.nearSpeed = par5;
        this.entityPathNavigate = par1.getNavigator();
        this.setMutexBits(1);
    }
    
    public EntityAIAvoidEntity(EntityGenericCreature par1, Class par2, float par3, double par4, double par5, boolean shouldHop)
    {
        this(par1, par2, par3, par4, par5);
        this.shouldHop = shouldHop;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.targetEntityClass == EntityPlayer.class)
        {
            if (this.theEntity instanceof EntityGenericTameable && ((EntityGenericTameable)this.theEntity).isTamed())
            {
                return false;
            }

            this.closestLivingEntity = this.theEntity.worldObj.getClosestPlayerToEntity(this.theEntity, (double)this.distanceFromEntity);

            if (this.closestLivingEntity == null)
            {
                return false;
            }
        }
        else
        {
            List list = this.theEntity.worldObj.selectEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand((double)this.distanceFromEntity, 3.0D, (double)this.distanceFromEntity), this.field_98218_a);

            if (list.isEmpty())
            {
                return false;
            }

            this.closestLivingEntity = (Entity)list.get(0);
        }

        Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, Vec3.createVectorHelper(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

        if (vec3 == null)
        {
            return false;
        }
        else if (this.closestLivingEntity.getDistanceSq(vec3.xCoord, vec3.yCoord, vec3.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity))
        {
            return false;
        }
        else
        {
            this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
            return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(vec3);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	if(shouldHop){
            tryToHop();
        }
    	
        return !this.entityPathNavigate.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
        if(shouldHop){
            tryToHop();
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D)
        {
            this.theEntity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.theEntity.getNavigator().setSpeed(this.farSpeed);
        }
    }

    public void tryToHop(){
 		if (theEntity.onGround && this.slimeJumpDelay-- <= 0){
 			this.slimeJumpDelay = this.getJumpDelay();

 			theEntity.getJumpHelper().setJumping();
 			theEntity.getNavigator().setSpeed(this.nearSpeed);
 		}
 		else{
 			theEntity.getNavigator().setSpeed(0);
 		}
     }
     
 	/**
 	 * Gets the amount of time the slime needs to wait between jumps.
 	 */
    
 	protected int getJumpDelay(){
 		if (this.theEntity.getEntityState() == EntityStates.fleeing){
 			return theEntity.getRNG().nextInt(3);
 		}
 		else
 		{
 			return theEntity.getRNG().nextInt(5) + 2;
 		}
 	}






    
   

    
}