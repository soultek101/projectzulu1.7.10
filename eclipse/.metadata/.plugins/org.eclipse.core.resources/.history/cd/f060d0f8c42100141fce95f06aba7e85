package com.ngb.projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.config.Configuration;
import com.ngb.projectzulu.common.api.CustomMobData;
import com.ngb.projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import com.ngb.projectzulu.common.mobs.entity.EntityLizardSpit;
import com.ngb.projectzulu.common.mobs.renders.RenderLizardSpit;
import com.ngb.projectzulu.common.mobs.renders.RenderWrapper;
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