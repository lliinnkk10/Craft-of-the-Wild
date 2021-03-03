package com.atheera.craftofthewild.events;

import com.atheera.craftofthewild.Main;

import net.minecraft.client.MainWindow;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class RupeeHUDEvent {
	
	@SubscribeEvent
	public static void renderRupeeAmount(RenderGameOverlayEvent event) {
		
		MainWindow window = event.getWindow();
		
		
		
	}
	
}