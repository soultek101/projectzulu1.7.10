package com.ngb.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import com.ngb.projectzulu.common.api.BlockList;
import com.ngb.projectzulu.common.blocks.heads.BlockMobHeads;
import com.ngb.projectzulu.common.blocks.heads.ItemMobHeads;
import com.ngb.projectzulu.common.blocks.heads.TileEntityMobHeads;
import com.ngb.projectzulu.common.blocks.heads.TileEntityMobHeadsRenderer;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MobSkullsDeclaration extends BlockDeclaration {

    public MobSkullsDeclaration() {
        super("MobSkulls");
    }

    @Override
    protected boolean createBlock() {
        BlockList.mobHeads = Optional.of(new BlockMobHeads().setBlockName(name.toLowerCase()).setBlockTextureName(
                DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.mobHeads.get();
        GameRegistry.registerBlock(block, ItemMobHeads.class, name.toLowerCase());
        GameRegistry.registerTileEntity(TileEntityMobHeads.class, "TileEntityMobHead");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void clientRegisterBlock() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMobHeads.class, new TileEntityMobHeadsRenderer());
    }
}