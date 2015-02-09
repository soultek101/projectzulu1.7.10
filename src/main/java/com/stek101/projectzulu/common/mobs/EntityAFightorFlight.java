package com.stek101.projectzulu.common.mobs;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericTameable;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAvoidEntity;

public class EntityAFightorFlight {
	
	private int isHurt = 0;
	private float aggroLevel;
	private double aggroRange;
	private EntityGenericCreature entity;
	private EntityAIAvoidEntity aiEntityAvoidEntity;
	private World worldObj;
	private CustomEntityList entityEntry;
	private EntityPlayer entityplayer;
	private boolean temptingItem;
	
	public float angerLevel;
	public float fleeLevel;	
	
	public EntityAFightorFlight setEntity(EntityGenericCreature par1, World par2, float par3, double par4) {
	  this.entity = par1;			
	  this.worldObj = par2;
	  this.aggroLevel = par3;
	  this.aggroRange = par4;	  
	  this.aiEntityAvoidEntity = new EntityAIAvoidEntity(entity, EntityPlayer.class, 16.0f, 1.3D, 1.2D);	  
	  return this;	
	}
	
	public EntityAFightorFlight setEntity(EntityGenericCreature par1, World par2, float par3, double par4, boolean shouldHop) {
		  this.entity = par1;			
		  this.worldObj = par2;
		  this.aggroLevel = par3;
		  this.aggroRange = par4;	  
		  this.aiEntityAvoidEntity = new EntityAIAvoidEntity(entity, EntityPlayer.class, 16.0f, 1.3D, 1.2D, shouldHop);	  
		  return this;	
	}
	
	
	public void updateEntityAFF(World worldobj, Item temptItem){
		ItemStack var1 = null;
		//ItemStack var1 = this.entityplayer.getCurrentEquippedItem();
        entityplayer = worldObj.getClosestPlayerToEntity(this.entity, this.aggroRange);
        temptingItem = false;
        
        if (entityplayer != null) {
          var1 = this.entityplayer.getCurrentEquippedItem();
          //var1 == null ? false : entityplayer.equals(var1.getItem());
          if (var1 != null && temptItem != null) {        	  
           	  if (temptItem.equals(var1.getItem())){
 	         	entity.setAngerLevel(0); // calm down and move on with life
 	         	entity.setFleeTick(0);
        		temptingItem = true;
        	  }
          }
        }
        
     
        
        if (this.aggroRange != 0 && temptingItem == false ){  /** 0 means deactivate FoF behavior or entity player is too far**/
 
        	/* Check if target can be detected, then entity will decide whether to fight or flee, ignore if tamed */
	        //if (!(this instanceof EntityGenericTameable) && !((EntityGenericTameable)this).isTamed()){
   
        	
        	if (!((EntityGenericTameable)entity).isTamed()){ /** Do not include tamed mobs in FoF*/
	         
        	  if(entityplayer != null && entity.canEntityBeSeen(entityplayer)) {
        		  
               if (this.aggroLevel > 0) { 
            	
	          	Random rand = new Random();         	
	          	int dieRoll = rand.nextInt(100);    	
	          	
	             if ((this.aggroLevel) >= dieRoll){
	          		if (entity.getAngerLevel() == 0 && entity.getFleeTick() == 0){
	          		  entity.tasks.removeTask(aiEntityAvoidEntity);
	          		  entity.setAngerLevel(400);  //make it angry!!!          		  
	          	      //System.out.println("FIGHT FIGHT FIGHT, aggrolevel of " + aggroLevel + " dieRoll of " + dieRoll);         		
	          		  }
	          	} else {
	          		if (entity.getAngerLevel() == 0 && entity.getFleeTick() == 0){ 
	          		 entity.tasks.addTask(1, aiEntityAvoidEntity );
	          		 entity.setFleeTick(400);
	          		//System.out.println("FLEEEEEEEEEEEE, aggrolevel of " + aggroLevel + " dieRoll of " + dieRoll); 
	          		 }
	          	  }
                 }else{ /* auto flee each and every time */
                	 //System.out.println("*** auto flee ***"); 
                	 entity.tasks.addTask(1, aiEntityAvoidEntity );
	          		 entity.setFleeTick(400);
                 }
	           } else {
	         	 entity.setAngerLevel(0); // calm down and move on with life
	         	 entity.setFleeTick(0);
	           }          
	          }
        	
	        /* A wounded animal will sometimes get more dangerous
	     	if ((entity.getHealth() < 0.9 * entity.getMaxHealth()) && isHurt == 0){
	     		aggroLevel = aggroLevel + 25;
	     		isHurt = 1;
	     	}*/
	     	
	     	/* A wounded animal can also decide to flee if hurt too much 
	     	if ((entity.getHealth() < 0.5 * entity.getMaxHealth()) && isHurt == 1){
	     		
	     		if ((aggroLevel - 25)< 0){
	     			aggroLevel = 0;
	     		}
	     		else
	     		{
	     		    aggroLevel = aggroLevel - 25;
	     		}
	     		isHurt = 2;
	     	}*/
	     	
	     	
	      }
	}
	
	public void updateEntityAFF(World worldobj){
		updateEntityAFF(worldobj, null);
	}


}
