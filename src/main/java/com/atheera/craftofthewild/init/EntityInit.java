package com.atheera.craftofthewild.init;

import com.atheera.craftofthewild.Main;
import com.atheera.craftofthewild.entities.objects.IceArrowEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);
	
	public static final RegistryObject<EntityType<IceArrowEntity>> ICE_ARROW_ENTITY = ENTITY_TYPES.register
		("arrow_ice_entity", () -> EntityType.Builder.<IceArrowEntity>create(EntityClassification.MISC)
		.size(1.0f, 1.0f).build(new ResourceLocation(Main.MOD_ID, "arrow_ice_entity").toString()));
	
}
