package com.stek101.projectzulu.common.mobs.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.stek101.projectzulu.common.mobs.entity.EntityGenericAnimal;

public class RenderGenericHorse extends RenderGenericLiving {
    
    public final ResourceLocation saddledTexture;
    public final ResourceLocation wildTexture;

    public RenderGenericHorse(ModelBase modelBase, float shadowSize, ResourceLocation wildTexture,
            ResourceLocation saddledTexture) {
        super(modelBase, shadowSize, wildTexture);
        this.saddledTexture = saddledTexture;
        this.wildTexture = wildTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityGenericAnimal) {

            EntityGenericAnimal animal = (EntityGenericAnimal) entity;
            if (animal.getSaddled()) {
                return saddledTexture;
            }
            else{
            	return wildTexture;
            }
	
        }
        return livingTexture;
    }
}
