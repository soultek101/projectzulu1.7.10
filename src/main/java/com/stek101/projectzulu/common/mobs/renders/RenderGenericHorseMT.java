package com.stek101.projectzulu.common.mobs.renders;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericAnimal;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;

public class RenderGenericHorseMT extends RenderGenericLiving {
    public final List <ResourceLocation> entityTexture = new ArrayList <ResourceLocation>();
    public final List <ResourceLocation> saddledTexture = new ArrayList <ResourceLocation>();

    public RenderGenericHorseMT(ModelBase modelBase, float shadowSize, List <String> wildTextures,
           List <String> saddledTextures) {
    	
        super(modelBase, shadowSize,  new ResourceLocation(DefaultProps.mobKey, wildTextures.get(0)));
        
        for (int i = 0; i < wildTextures.size(); i++) {
            entityTexture.add(new ResourceLocation(DefaultProps.mobKey, wildTextures.get(i)));
            saddledTexture.add(new ResourceLocation(DefaultProps.mobKey, saddledTextures.get(i)));
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityGenericAnimal) {
            EntityGenericAnimal animal = (EntityGenericAnimal) entity;   
            
            if (animal.getSaddled()) {            	
                return saddledTexture.get(animal.textureID);
            }
        }
        return this.getTexture((EntityGenericCreature)entity);
    }
    
	 protected ResourceLocation getTexture(EntityGenericCreature par1Entity)
	    {	
	    	return entityTexture.get(par1Entity.textureID);	    	
	    }
}