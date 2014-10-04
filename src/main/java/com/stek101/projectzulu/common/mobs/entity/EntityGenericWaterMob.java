package com.stek101.projectzulu.common.mobs.entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget.Sorter;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/*Original code from Oceancraft 1.4, adapted by Lots o Mobs*/
public abstract class EntityGenericWaterMob extends EntityGenericAnimal implements IAnimals {
  
  	   private float entityRotation = 0.0F;
	   private float rotationVelocity = 0.0F;
	   private float randomSpeed = 0.0F;
	   private float randomVecX = 0.0F;
	   private float randomVecY = 0.0F;
	   private float randomVecZ = 0.0F;
	   protected int mobInterval = 30;
	   protected float mobSpeed = 0.3F;

	   public EntityGenericWaterMob(World var1) {
	      super(var1);
	      this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
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
	   
	    public boolean canBreatheUnderwater()
	    {
	        return true;
	    }

	    /**
	     * Get number of ticks, at least during which the living entity will be silent.
	     */
	    public int getTalkInterval()
	    {
	        return 120;
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
	        }
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
			if(!isEntityGrounded() && targetPosition != null){
				/* Get The Direction I want to travel in */
				double var1 = (double)this.targetPosition.posX + 0.5D - this.posX;
				double var3 = (double)this.targetPosition.posY + 0.6D - this.posY;
				double var5 = (double)this.targetPosition.posZ + 0.5D - this.posZ;

				/* Change Velocity */
				/* Normalize the Direction I want to travel in, then add and scale it to Motion */
				this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D*0.3D;
				this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D*0.3D;
				this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D*0.3D;
				float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
				this.moveForward = 0.5F;
				this.rotationYaw += var8;
			}
			super.updateAITasks();
		}
	    
	    @Override
		public void moveEntityWithHeading(float par1, float par2) {
			if(isEntityGrounded()){
	    		super.moveEntityWithHeading(par1, par2);
	    		return;
	    	}
		
	        if (this.isInWater())
	        {
	         //   this.moveFlying(par1, par2, 0.05F);
	            this.moveEntity(this.motionX, this.motionY, this.motionZ);
	         //   this.motionX *= 0.800000011920929D;
	         //   this.motionY *= 0.800000011920929D;
	         //   this.motionZ *= 0.800000011920929D;
	        }
	        else if (this.handleLavaMovement())
	        {
	            this.moveFlying(par1, par2, 0.02F);
	            this.moveEntity(this.motionX, this.motionY, this.motionZ);
	            this.motionX *= 0.5D;
	            this.motionY *= 0.5D;
	            this.motionZ *= 0.5D;
	        }
	        else
	        {
	            float var3 = 0.91F;

	            if (this.onGround)
	            {
	                var3 = 0.54600006F;
	                Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
	                if (block != null)
	                {
	                    var3 = block.slipperiness * 0.91F;
	                }
	            }

	            float var8 = 0.16277136F / (var3 * var3 * var3);
	            this.moveFlying(par1, par2, this.onGround ? 0.1F * var8 : 0.02F);
	            var3 = 0.91F;

	            if (this.onGround)
	            {
	                var3 = 0.54600006F;
	                Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
	                if (block != null)
	                {
	                    var3 = block.slipperiness * 0.91F;
	                }
	            }

	            this.moveEntity(this.motionX, this.motionY, this.motionZ);
	            this.motionX *= (double)var3;
	            this.motionY *= (double)var3;
	            this.motionZ *= (double)var3;
	        }

	        this.prevLimbSwingAmount = this.limbSwingAmount;
	        double var10 = this.posX - this.prevPosX;
	        double var9 = this.posZ - this.prevPosZ;
	        float var7 = MathHelper.sqrt_double(var10 * var10 + var9 * var9) * 4.0F;

	        if (var7 > 1.0F)
	        {
	            var7 = 1.0F;
	        }

	        this.limbSwingAmount += (var7 - this.limbSwingAmount) * 0.4F;
	        this.limbSwing += this.limbSwing;
		}
	    
		   @Override
		   public void onLivingUpdate() {
		      super.onLivingUpdate();
		      
		      this.entityRotation += this.rotationVelocity;
		      
		      if(this.entityRotation > 6.2831855F) {
		         this.entityRotation -= 6.2831855F;
		         if(this.rand.nextInt(10) == 0) {
		            this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
		         }
		      }

		      if(this.isInWater()) {
		    	  
		         float var1;
		         
		         if(this.entityRotation < 3.1415927F) {
		            var1 = this.entityRotation / 3.1415927F;
		            if((double)var1 > 0.75D) {
		               this.randomSpeed = 1.0F;
		            }
		         } else {
		            this.randomSpeed *= 0.95F;
		         }
		         
		         if(!this.worldObj.isRemote) {
		        	//System.out.println("not worldOjb.Remote ");
		            this.motionX = (double)(this.randomVecX * this.randomSpeed);
		            this.motionY = (double)(this.randomVecY * this.randomSpeed);
		            this.motionZ = (double)(this.randomVecZ * this.randomSpeed);
		         }	         
	        
		         this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / 3.1415927F - this.renderYawOffset) * 0.1F;
		         this.rotationYaw = this.renderYawOffset;
		         var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		         if(var1 > 0.07F) {
		            this.rotationPitch = (float)Math.atan2(this.motionY, (double)var1) * 180.0F / 3.1415927F;
		         }
		         
		      } 
		      
		      else {
		         this.motionX = 0.0D;
		         this.motionY -= 0.08D;
		         this.motionY *= 0.9800000190734863D;
		         this.motionZ = 0.0D;
		         
		         if(!this.isInWater()) {
		            this.rotationPitch = 0.0F;
		            if(this.onGround && this.rand.nextInt(30) == 0) {
		               this.motionY = 0.30000001192092896D;
		               this.motionX = (double)(-0.4F + this.rand.nextFloat() * 0.8F);
		               this.motionZ = (double)(-0.4F + this.rand.nextFloat() * 0.8F);
		            }
		         }
		      }
		      //System.out.println("motionX " + this.motionX + " , motionY " + this.motionY + " , motionZ " + this.motionZ);

		   }
		  
		   @Override
		   protected void updateAITick() {
		      ++this.entityAge;
		      
		      if(this.entityAge > 100) {
		    	 
		         this.randomVecX = this.randomVecY = this.randomVecZ = 0.0F;
		         
		      } else if(this.rand.nextInt(this.mobInterval) == 0 || !this.inWater || this.isCollided || this.randomVecX == 0.0F && this.randomVecY == 0.0F && this.randomVecZ == 0.0F) {
		    	  if(this.entityToAttack != null && this.attackTime <= 0) {
		        	 
		            if(this.entityToAttack != null) {
		               if(!this.entityToAttack.isDead && this.entityToAttack.isInWater() && this.getDistanceToEntity(this.entityToAttack) <= 10.0F && this.attackTime <= 0) {
		                  this.randomVecX = (float)Math.signum(this.entityToAttack.posX - this.posX) * this.mobSpeed;
		                  this.randomVecY = (float)Math.signum(this.entityToAttack.posY - this.posY) * 0.1F;
		                  this.randomVecZ = (float)Math.signum(this.entityToAttack.posZ - this.posZ) * this.mobSpeed;
		                 
		                  if(!(this.entityToAttack instanceof EntityLiving)) {
		                     this.randomVecY -= 0.12F;
		                     if(this.entityToAttack instanceof EntityFishHook && this.getDistanceToEntity(this.entityToAttack) < 1.0F) {
		                        EntityFishHook var2 = (EntityFishHook)this.entityToAttack;
		                        this.entityToAttack = null;
		                        if(var2.field_146043_c == null) {
		                           this.attackEntityFrom(DamageSource.causeThrownDamage(var2, var2.field_146042_b), 0.0F);
		                           var2.field_146043_c = this;
		                           var2.field_146042_b.addStat(StatList.field_151183_A, 1);
		                        }
		                     }
		                  }
		               } else {
		                  this.entityToAttack = null;
		               }
		            }
		         } else {
		            float var1 = this.rand.nextFloat() * 3.1415927F * 2.0F;
		            this.randomVecX = MathHelper.cos(var1) * this.mobSpeed;
		            this.randomVecY = -0.1F + this.rand.nextFloat() * 0.2F;
		            this.randomVecZ = MathHelper.sin(var1) * this.mobSpeed;
		            if(this.getAttackStrength() > 0) {
		               this.entityToAttack = this.findEntityToAttack();
		            }
		         }
		      }
		      this.despawnEntity();	     
		   }
		   
		   /**
		     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
		     * prevent them from trampling crops
		     */
		    protected boolean canTriggerWalking()
		    {
		        return false;
		    }
		   
		   protected boolean canAttackEntity(Entity var1) {
			   
		      return var1 instanceof EntityLiving && this.width > var1.width && this.height >= var1.height;
		   }
		   
		   protected Entity findEntityToAttack() {
			      List var1 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(10.0D, 6.0D, 10.0D));
			      Collections.sort(var1, new Sorter(this));
			      Iterator var2 = var1.iterator();

			      Entity var3;
			      do {
			         if(!var2.hasNext()) {
			            return null;
			         }

			         var3 = (Entity)var2.next();
			      } while(!var3.isInWater() || !this.canAttackEntity(var3));

			      return var3;
			   }
		   
		   public void collideWithEntity(Entity var1) {
			      super.collideWithEntity(var1);
			      if(this.entityToAttack == var1 && this.getAttackStrength() != 0) {
			         this.attackTime = 30;
			         this.entityToAttack.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength());
			      }
		   }
		   
		   protected int getAttackStrength() {
			 	      return 0;
		}
			   	   
}
