package com.stek101.projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityLizardSpit;
import com.stek101.projectzulu.common.mobs.renders.RenderLizardSpit;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LizardSpitDeclaration extends CreatureDeclaration {

	public LizardSpitDeclaration() {
		super("Lizard Spit", 3, EntityLizardSpit.class, null);
		setRegistrationProperties(128, 3, true);
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}

	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {}
    
    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderLizardSpit(0.5f);
    }
}