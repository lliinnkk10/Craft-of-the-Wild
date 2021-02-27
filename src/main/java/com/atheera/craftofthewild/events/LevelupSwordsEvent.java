package com.atheera.craftofthewild.events;

import com.atheera.craftofthewild.Main;
import com.atheera.craftofthewild.objects.items.swords.one.MasterSwordItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class LevelupSwordsEvent {
	
	@SubscribeEvent
	public static void levelupEvent(LivingDeathEvent event) {
		DamageSource source = event.getSource();
		Entity entity = event.getEntity();
		
		if(source.getTrueSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)source.getTrueSource();
			ItemStack stack = player.getHeldItemMainhand();
			Item sword = stack.getItem();
			
			if(sword instanceof MasterSwordItem) {
				if(entity instanceof WitherEntity) { ((MasterSwordItem)sword).levelUp(stack, 1); }
				else if(entity instanceof EnderDragonEntity) { ((MasterSwordItem)sword).levelUp(stack, 2); }
				else ((MasterSwordItem)sword).levelUp(stack, 0);
			}
		}
	}
	
}