package com.stek101.projectzulu.common.mobs.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;

import cpw.mods.fml.common.Loader;

public class EntityBrownBear extends EntityBear {

    public EntityBrownBear(World par1World) {
        super(par1World);
        setSize(1.5f, 2.1f);
        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false));
    }

    @Override
    public int getTotalArmorValue() {
        return 4;
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "brownbearliving";
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 4), 1);
        }
        super.dropRareDrop(par1);
    }
}
