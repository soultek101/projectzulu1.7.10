package com.stek101.projectzulu.common.mobs.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityDuckEgg extends EntityThrowable
{
	
		public EntityDuckEgg(World par1World)
		{
		super(par1World);
		}
		public EntityDuckEgg(World par1World, EntityLivingBase par2EntityLivingBase)
		{
		super(par1World, par2EntityLivingBase);
		}
		public EntityDuckEgg(World par1World, double par2, double par4, double par6)
		{
		super(par1World, par2, par4, par6);
		}
		/**
		* Called when this EntityThrowable hits a block or entity.
		*/
		protected void onImpact(MovingObjectPosition movObjPos)
		{
	        if (movObjPos.entityHit != null)
	        {
	        	movObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
	        }

	        if (!this.worldObj.isRemote && this.rand.nextInt(8) == 0)
	        {
	            byte b0 = 1;

	            if (this.rand.nextInt(32) == 0)
	            {
	                b0 = 4;
	            }

	            for (int i = 0; i < b0; ++i)
	            {
	                //EntityChicken entitychicken = new EntityChicken(this.worldObj);
	            	EntityDuck entityduck = new EntityDuck(this.worldObj);
	                entityduck.setGrowingAge(-24000);
	                entityduck.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
	                this.worldObj.spawnEntityInWorld(entityduck);
	            }
	        }

	        for (int j = 0; j < 8; ++j)
	        {
	            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	        }

	        if (!this.worldObj.isRemote)
	        {
	            this.setDead();
	        }
		}
}
