package com.stek101.projectzulu.common.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.stek101.projectzulu.common.blocks.GuiAnimalName;
import com.stek101.projectzulu.common.blocks.tombstone.GuiTombstone;
import com.stek101.projectzulu.common.blocks.tombstone.TileEntityTombstone;
import com.stek101.projectzulu.common.blocks.universalpot.ContainerUniversalFlowerPot;
import com.stek101.projectzulu.common.blocks.universalpot.GuiContainerUniversalFlowerPot;
import com.stek101.projectzulu.common.blocks.universalpot.TileEntityUniversalFlowerPot;
import com.stek101.projectzulu.common.dungeon.GuiLimitedMobSpawner;
import com.stek101.projectzulu.common.dungeon.TileEntityLimitedMobSpawner;
import com.stek101.projectzulu.common.potion.brewingstands.ContainerBrewingStandSingle;
import com.stek101.projectzulu.common.potion.brewingstands.GuiBrewingStandSingle;
import com.stek101.projectzulu.common.potion.brewingstands.TileEntityBrewingBase;
import cpw.mods.fml.common.network.IGuiHandler;

public class ZuluGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tileEntityServer;
        switch (GuiID.getGuiIDByID(guiID)) {
        case FlowerPot:
            tileEntityServer = world.getTileEntity(x, y, z);
            if (tileEntityServer instanceof TileEntityUniversalFlowerPot) {
                return new ContainerUniversalFlowerPot(player.inventory,
                        (TileEntityUniversalFlowerPot) tileEntityServer);
            }
        case BrewingStand:
            tileEntityServer = world.getTileEntity(x, y, z);
            if (tileEntityServer instanceof TileEntityBrewingBase) {
                return new ContainerBrewingStandSingle(player.inventory, (TileEntityBrewingBase) tileEntityServer);
            }
        case Unknown:
            throw new IllegalStateException("GuiID cannot be Found" + guiID);
        default:
            break;
        }
        return null;

    }

    @Override
    public Object getClientGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntityClient;
        switch (GuiID.getGuiIDByID(guiID)) {
        case Tombstone:
            tileEntityClient = world.getTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityTombstone) {
                return new GuiTombstone((TileEntityTombstone) tileEntityClient);
            }
            break;
        case FlowerPot:
            tileEntityClient = world.getTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityUniversalFlowerPot) {
                return new GuiContainerUniversalFlowerPot(player.inventory,
                        (TileEntityUniversalFlowerPot) tileEntityClient);
            }
            break;
        case AnimalName:
            return new GuiAnimalName(world, player, x);
        case MobSpawner:
            tileEntityClient = world.getTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityLimitedMobSpawner) {
                return new GuiLimitedMobSpawner((TileEntityLimitedMobSpawner) tileEntityClient);
            }
        case BrewingStand:
            tileEntityClient = world.getTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityBrewingBase) {
                return new GuiBrewingStandSingle(player.inventory, (TileEntityBrewingBase) tileEntityClient);
            }
        case Unknown:
            throw new IllegalStateException("GuiID cannot be Found" + guiID);
        }

        return null;
    }

}
