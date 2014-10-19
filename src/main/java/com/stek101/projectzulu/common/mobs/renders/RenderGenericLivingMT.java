package com.stek101.projectzulu.common.mobs.renders;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;

public class RenderGenericLivingMT extends RenderGenericLiving {
	public final List <ResourceLocation> entityTexture = new ArrayList <ResourceLocation>();
	    
	    public RenderGenericLivingMT(ModelBase par1ModelBase, float shadowSize, List <String> wildTextures) {
	    	super(par1ModelBase, shadowSize, new ResourceLocation(DefaultProps.mobKey, wildTextures.get(0)));
	    	 for (int i = 0; i < wildTextures.size(); i++) {
	             entityTexture.add(new ResourceLocation(DefaultProps.mobKey, wildTextures.get(i)));	             
	         }

	    }
	    
		 @Override
		    protected ResourceLocation getEntityTexture(Entity entity)
		    {
		        return this.getTexture((EntityGenericCreature)entity);  
		    }
		 
		 protected ResourceLocation getTexture(EntityGenericCreature par1Entity)
		    {	
		    	return entityTexture.get(par1Entity.textureID);	    	
		    }

	}
