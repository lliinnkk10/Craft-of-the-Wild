package com.atheera.craftofthewild.init;

import com.atheera.craftofthewild.Main;
import com.atheera.craftofthewild.objects.blocks.ExampleChestBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	
	//Initiate
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	//Create
		//Blocks
	public static final RegistryObject<Block> EXAMPLE_CHEST = BLOCKS.register("example_chest", () -> new ExampleChestBlock(Block.Properties.create(Material.WOOD).harvestLevel(1).hardnessAndResistance(5f, 5f)));
	
	
		//Items
	public static final RegistryObject<Item> EXAMPLE_CHEST_ITEM = ITEMS.register("example_chest", () -> new BlockItem(EXAMPLE_CHEST.get(), new Item.Properties().group(Main.CTOW_B_IG)));
}