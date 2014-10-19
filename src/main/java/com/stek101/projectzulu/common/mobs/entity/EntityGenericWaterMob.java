package com.stek101.projectzulu.common.mobs.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/*Original code from Oceancraft 1.4, adapted by Lots o Mobs*/
public abstract class EntityGenericWaterMob extends EntityGenericAnimal implements IAnimals {
  
  	   //prate float entityRotation = 0.0F;
	   //private float rotationVelocity = 0.0F;
	   private float randomSpeed = 0.90F;
	   private float randomVecX = 0.0F;
	   private float randomVecY = 0.0F;
	   private float randomVecZ = 0.0F;
	   protected int mobInterval = 30;
	   protected float mobSpeed = 0.3F;
	   private float randomMotionVecX;
	   private float randomMotionVecY;
	   private float randomMotionVecZ;

	   public EntityGenericWaterMob(World var1) {
	      super(var1);
	      //this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
	   }
	   
	    /**
	     * Checks if this entity is inside water (if inWater field is true as a result of handleWaterMovement() returning
	     * true)
	     */
	   @Override
	    public boolean isInWater()
	    {
	        return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
	    }
	   
	   @Override
	    public boolean canBreatheUnderwater()
	    {
	        return true;
	    }

	    /**
	     * Determines if an entity can be despawned, used on idle far away entities
	     */
	    @Override
	    protected boolean canDespawn()
	    {
	        return true;
	    }

	    /**
	     * Get the experience points the entity currently has.
	     */
	    protected int getExperiencePoints(EntityPlayer p_70693_1_)
	    {
	        return 1 + this.worldObj.rand.nextInt(3);
	    }

	    /**
	     * Gets called every tick from main Entity class
	     */
	    public void onEntityUpdate()
	    {
	        int i = this.getAir();
	        super.onEntityUpdate();

	        if (this.isEntityAlive() && !this.isInWater())
	        {
	            --i;
	            this.setAir(i);

	            if (this.getAir() == -20)
	            {
	                this.setAir(0);
	                this.attackEntityFrom(DamageSource.drown, 2.0F);
	            }
	           
	        }
	        else
	        {
	            this.setAir(300);
	            //this.setEntityGrounded(false);
	        }
	    }
	    
	    @Override
		public boolean isTargetPositionValid(){
			return isTargetPositionValid(this.targetPosition);
		}
	        
	    @Override	    
		public boolean isTargetPositionValid(ChunkCoordinates targetPosition){
			/* Invalid if not Water, is below height = 3? (superflat), and if its null */
			if (targetPosition != null && (this.worldObj.isAirBlock(targetPosition.posX, targetPosition.posY, targetPosition.posZ)  
					|| !this.worldObj.getBlock(targetPosition.posX, targetPosition.posY, targetPosition.posZ).getMaterial().equals(Material.water))){
//				targetPosition = null;
				return false;
			}
			return true;
		}
	    
	    @Override
		protected void updateAITasks() {
			if(!isEntityGrounded() && targetPosition != null && this.inWater){
				/* Get The Direction I want to travel in */
				double var1 = (double)this.targetPosition.posX + 0.5D - this.posX;
				double var3 = (double)this.targetPosition.posY + 0.6D - this.posY;
				double var5 = (double)this.targetPosition.posZ + 0.5D - this.posZ;

				/* Change Velocity */
				/* Normalize the Direction I want to travel in, then add and scale it to Motion */
				this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D*0.3D;
				this.motionY += (Math.signum(var3) * 0.6D - this.motionY) * 0.10000000149011612D*0.3D;
				this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D*0.3D;
				float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
				this.moveForward = 0.5F;
				this.rotationYaw += var8;
			}			
			else{
				if (!this.isInWater()) {
					 this.rotationPitch = 0.0F;
			            if(this.onGround && this.rand.nextInt(30) == 0) {
			               this.motionY = 0.30000001192092896D;
			               this.motionX = (double)(-0.4F + this.rand.nextFloat() * 0.8F);
			               this.motionZ = (double)(-0.4F + this.rand.nextFloat() * 0.8F);
				       }
				}
			}
			
			 if (this.entityAge > 100)
		        {
		            this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
		        }
		        else if (this.rand.nextInt(50) == 0 || !this.inWater || this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F)
		        {
		            float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
		            this.randomMotionVecX = MathHelper.cos(f) * 0.2F;
		            this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
		            this.randomMotionVecZ = MathHelper.sin(f) * 0.2F;
		        }
			super.updateAITasks();
		}
	    
	    /**
	     * Moves the entity based on the specified heading.  Args: strafe, forward
	     */
	  /*  public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
	    {
	        double d0;

	        if (this.isInWater())
	        {
	            d0 = this.posY;
	            this.moveFlying(p_70612_1_, p_70612_2_, this.isAIEnabled() ? 0.04F : 0.02F);
	            this.moveEntity(this.motionX, this.motionY, this.motionZ);
	            //this.motionX *= 0.800000011920929D;
	            //this.motionY *= 0.800000011920929D;
	            //this.motionZ *= 0.800000011920929D;
	            this.motionX = (double)(this.randomMotionVecX * this.randomSpeed);
	            this.motionY = (double)(this.randomMotionVecY * this.randomSpeed);
	            this.motionZ = (double)(this.randomMotionVecZ * this.randomSpeed);
	            this.motionY -= 0.02D;

	            if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
	            {
	                this.motionY = 0.30000001192092896D;
	            }
	        }
	        else if (this.handleLavaMovement())
	        {
	            d0 = this.posY;
	            this.moveFlying(p_70612_1_, p_70612_2_, 0.02F);
	            this.moveEntity(this.motionX, this.motionY, this.motionZ);
	            this.motionX *= 0.5D;
	            this.motionY *= 0.5D;
	            this.motionZ *= 0.5D;
	            this.motionY -= 0.02D;

	            if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
	            {
	                this.motionY = 0.30000001192092896D;
	            }
	        }
	        else
	        {
	            float f2 = 0.91F;

	            if (this.onGround)
	            {
	                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
	            }

	            float f3 = 0.16277136F / (f2 * f2 * f2);
	            float f4;

	            if (this.onGround)
	            {
	                f4 = this.getAIMoveSpeed() * .01f;
	            }
	            else
	            {
	                f4 = this.jumpMovementFactor;
	            }

	            //this.moveFlying(p_70612_1_, p_70612_2_, f4);
	            this.moveFlying(p_70612_1_, p_70612_2_, 0.02f);
	            f2 = 0.91F;

	            if (this.onGround)
	            {
	                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
	            }

	            this.moveEntity(this.motionX, this.motionY, this.motionZ);

	            if (this.isCollidedHorizontally)
	            {
	                this.motionY = 0.02D;
	            }

	            if (this.worldObj.isRemote && (!this.worldObj.blockExists((int)this.posX, 0, (int)this.posZ) || !this.worldObj.getChunkFromBlockCoords((int)this.posX, (int)this.posZ).isChunkLoaded))
	            {
	                if (this.posY > 0.0D)
	                {
	                    this.motionY = -0.1D;
	                }
	                else
	                {
	                    this.motionY = 0.0D;
	                }
	            }
	            else
	            {
	                this.motionY -= 0.08D;
	            }

	            this.motionY *= 0.9800000190734863D;
	            this.motionX *= (double)f2;
	            this.motionZ *= (double)f2;
	        }

	        this.prevLimbSwingAmount = this.limbSwingAmount;
	        d0 = this.posX - this.prevPosX;
	        double d1 = this.posZ - this.prevPosZ;
	        float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

	        if (f6 > 1.0F)
	        {
	            f6 = 1.0F;
	        }

	        this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
	        this.limbSwing += this.limbSwingAmount;
	    }*/
	    
	    @Override
		public boolean setTargetPosition(ChunkCoordinates targetPosition){
			if(isTargetPositionValid(targetPosition)){
				this.targetPosition = targetPosition;
				return true;
			}
			return false;
		}
	    
	    /* Checks if Entity is at the target position, return true if TargetPosition is null*/
		@Override
	    public boolean atTargetPosition(){
			if(targetPosition != null){
			   return getDistance(targetPosition.posX, targetPosition.posY, targetPosition.posZ) < 2;
			}
			return true;
		}
	    
 

}
