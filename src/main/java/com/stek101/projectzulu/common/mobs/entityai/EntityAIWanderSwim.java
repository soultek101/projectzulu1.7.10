package com.stek101.projectzulu.common.mobs.entityai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;

import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;

public class EntityAIWanderSwim extends EntityAIBase{
	private EntityGenericCreature entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private float speed;
	private double maxDepth;

	public EntityAIWanderSwim(EntityGenericCreature par1EntityCreature, float speed, double maxDepth) {
		this.entity = par1EntityCreature;
		this.speed = speed;
		this.maxDepth =  maxDepth;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (this.entity.getAge() >= 100 && entity.isEntityGrounded()) {
			return false;
		}
		else {
			Vec3 var1 = RandomPositionGenerator.swimRandomlyTowardHeightLevel(this.entity, 10, 7, entity.getMaxFlightHeight());

			if (var1 == null) {
				return false;
			}
			else {
				if (var1.yCoord < maxDepth) {
					this.yPosition = 60.0D;
				}
				else
				{
					this.yPosition = var1.yCoord;
				}
				this.xPosition = var1.xCoord;
				//this.yPosition = var1.yCoord;
				this.zPosition = var1.zCoord;
				return entity.isTargetPositionValid(new ChunkCoordinates( (int)xPosition, (int)yPosition, (int)zPosition));
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		/* Continue if I'm not at Target Position or if its invalid block */
		/* If Target Position is not an Air Block, Target Position = null (i.e. Don't try to go there) */
		//return entity.getRNG().nextInt(100) != 0 && !entity.atTargetPosition() && entity.isTargetPositionValid();
		return !this.entity.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		/* Set Target Postion */
		//entity.setTargetPosition(new ChunkCoordinates((int)xPosition, (int)yPosition, (int)zPosition));
		this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
	}


}
