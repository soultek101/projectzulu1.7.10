package com.stek101.projectzulu.common.mobs.entityai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;

import com.stek101.projectzulu.common.mobs.entity.EntityGenericWaterMob;
import com.stek101.projectzulu.common.mobs.entity.EntityStates;

public class EntityAIPanicSwim extends EntityAIBase
{
	private EntityGenericWaterMob entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private float speed;
	private double maxDepth;

    boolean shouldHop = false;
    int slimeJumpDelay = 0;   
    
    public EntityAIPanicSwim(EntityGenericWaterMob par1EntityCreature, float par2, double maxDepth) {
        this.entity = par1EntityCreature;
        this.speed = par2;
        this.maxDepth = maxDepth;
        this.setMutexBits(1);
    }
    
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.entity.getEntityState() != EntityStates.fleeing) {
            return false;
        }        
        else {
        	Vec3 var1 = RandomPositionGenerator.swimRandomlyTowardHeightLevel(this.entity, 10, 7, entity.getMaxFlightHeight());

        	if (var1 == null) {
				return false;
			}
			else {
				this.xPosition = var1.xCoord;
				this.yPosition = var1.yCoord;
				this.zPosition = var1.zCoord;
				return entity.isTargetPositionValid(new ChunkCoordinates( (int)xPosition, (int)yPosition, (int)zPosition));
			}
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
    	this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    	//entity.setTargetPosition(new ChunkCoordinates((int)xPosition, (int)yPosition, (int)zPosition));
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
    	return !this.entity.getNavigator().noPath();
    	//return entity.getRNG().nextInt(100) != 0 && !entity.atTargetPosition() && entity.isTargetPositionValid();
    	
    }
    
    @Override
    public void resetTask() {
    	super.resetTask();
    }
    
    
}