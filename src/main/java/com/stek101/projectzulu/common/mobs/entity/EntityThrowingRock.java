package com.stek101.projectzulu.common.mobs.entity;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.api.ItemList;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class EntityThrowingRock extends EntityThrowable
{
	
		public EntityThrowingRock(World par1World)
		{
			super(par1World);
		}
		public EntityThrowingRock(World par1World, EntityLivingBase par2EntityLivingBase)
		{
			super(par1World, par2EntityLivingBase);
		}
		public EntityThrowingRock(World par1World, double par2, double par4, double par6)
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
	        	movObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 2.0F);
	        }

	        if (!this.worldObj.isRemote && this.rand.nextInt(4) == 0)
	        {
	            byte b0 = 1;

	            if (this.rand.nextInt(32) == 0)
	            {
	                b0 = 4;
	            }
	            
	        	Random rand = new Random();
	        	EntityPlayer player = ProjectZulu_Core.proxy.getClientPlayer();
	        	Side side = FMLCommonHandler.instance().getEffectiveSide();

	            if (side == Side.SERVER) {
	                double xrand = (double) (worldObj.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
	    	    	double yrand = (double) (worldObj.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
	    	    	double zrand = (double) (worldObj.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
	        		EntityItem itemDrop = new EntityItem(worldObj, (double) this.posX + xrand, (double) this.posY + yrand, (double) this.posZ + zrand, new ItemStack(ItemList.throwingRock.get()));
	        		itemDrop.delayBeforeCanPickup = 10;	    	
	        		worldObj.spawnEntityInWorld(itemDrop);    	 
	        		}
	        }

	        for (int j = 0; j < 8; ++j)
	        {
	            this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	        }

	        if (!this.worldObj.isRemote)
	        {
	            this.setDead();
	        }
		}
}
