package com.atheera.craftofthewild.init;

import com.atheera.craftofthewild.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);
	
	public static final RegistryObject<SoundEvent> PICKUP = SOUNDS.register("pickup_rupee", () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, "pickup_rupee")));
	
}