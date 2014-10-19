package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.heads.BlockMobHeads;
import com.stek101.projectzulu.common.blocks.heads.ItemMobHeads;
import com.stek101.projectzulu.common.blocks.heads.TileEntityMobHeads;
import com.stek101.projectzulu.common.blocks.heads.TileEntityMobHeadsRenderer;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

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