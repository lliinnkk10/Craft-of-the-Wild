package com.atheera.craftofthewild.events;

import com.atheera.craftofthewild.Main;
import com.atheera.craftofthewild.objects.items.swords.one.MasterSwordItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class DamageSwordsEvent {
	
	@SubscribeEvent
	public static void damageItem(LivingHurtEvent event) {
		DamageSource source = event.getSource();
		
		if(source.getTrueSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)source.getTrueSource();
			ItemStack stack = player.getHeldItemMainhand();
			Item item = stack.getItem();
			
			if(item instanceof MasterSwordItem) {
				((MasterSwordItem)item).damageItem(stack);
			}
		}
	}
}