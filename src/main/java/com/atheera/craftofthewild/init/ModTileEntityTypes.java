package com.atheera.craftofthewild.init;

import com.atheera.craftofthewild.Main;
import com.atheera.craftofthewild.tileentity.ExampleChestTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
		.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

	public static final RegistryObject<TileEntityType<ExampleChestTileEntity>> EXAMPLE_CHEST = TILE_ENTITY_TYPES
		.register("example_chest", () -> TileEntityType.Builder
		.create(ExampleChestTileEntity::new, BlockInit.EXAMPLE_CHEST.get()).build(null));

}