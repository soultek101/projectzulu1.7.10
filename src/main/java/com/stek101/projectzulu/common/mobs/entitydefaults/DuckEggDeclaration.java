package com.stek101.projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityDuckEgg;
import com.stek101.projectzulu.common.mobs.renders.RenderDuckEgg;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DuckEggDeclaration extends CreatureDeclaration {

	public DuckEggDeclaration() {
		super("DuckEgg", 48, EntityDuckEgg.class, null);
		setRegistrationProperties(128, 3, true);
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}

	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {}
    
    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderDuckEgg(0.5f);
    }
}