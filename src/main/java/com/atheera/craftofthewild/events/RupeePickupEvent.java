package com.atheera.craftofthewild.events;

import javax.annotation.Nonnull;

import com.atheera.craftofthewild.Main;
import com.atheera.craftofthewild.capabilities.rupee.CurrencyCapability;
import com.atheera.craftofthewild.capabilities.rupee.ICurrency;
import com.atheera.craftofthewild.init.ItemInit;
import com.atheera.craftofthewild.init.SoundInit;
import com.atheera.craftofthewild.objects.items.misc.RupeeItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class RupeePickupEvent {
	
	@SubscribeEvent
	public static void pickupItem(EntityItemPickupEvent event) {
		PlayerEntity player = event.getPlayer();
		World world = player.getEntityWorld();
		ItemStack stack = event.getItem().getItem();
		Item item = stack.getItem();
		
		if(item instanceof RupeeItem) {
			
			if(item == ItemInit.RUPEE_GREEN.get()) {
	            player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).ifPresent(new NonNullConsumer<ICurrency>() {
	                @Override public void accept(@Nonnull ICurrency iCurrency) { iCurrency.addOrSubtractAmount(1); } });
	            world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.PICKUP.get(), SoundCategory.NEUTRAL, 1.0f, 0.8F / (0.4F + 0.8F));
	            event.getItem().getItem().shrink(1);
			}
			
			if(item == ItemInit.RUPEE_BLUE.get()) {
	            player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).ifPresent(new NonNullConsumer<ICurrency>() {
	                @Override public void accept(@Nonnull ICurrency iCurrency) { iCurrency.addOrSubtractAmount(5); } });
	            world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.PICKUP.get(), SoundCategory.NEUTRAL, 1.0f, 0.8F / (0.4F + 0.8F));
	            event.getItem().getItem().shrink(1);
			}
			
			if(item == ItemInit.RUPEE_RED.get()) {
	            player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).ifPresent(new NonNullConsumer<ICurrency>() {
	                @Override public void accept(@Nonnull ICurrency iCurrency) { iCurrency.addOrSubtractAmount(20); } });
	            world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.PICKUP.get(), SoundCategory.NEUTRAL, 1.0f, 0.8F / (0.4F + 0.8F));
	            event.getItem().getItem().shrink(1);
			}
			
			if(item == ItemInit.RUPEE_PURPLE.get()) {
	            player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).ifPresent(new NonNullConsumer<ICurrency>() {
	                @Override public void accept(@Nonnull ICurrency iCurrency) { iCurrency.addOrSubtractAmount(50); } });
	            world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.PICKUP.get(), SoundCategory.NEUTRAL, 1.0f, 0.8F / (0.4F + 0.8F));
	            event.getItem().getItem().shrink(1);
			}
			
			if(item == ItemInit.RUPEE_SILVER.get()) {
	            player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).ifPresent(new NonNullConsumer<ICurrency>() {
	                @Override public void accept(@Nonnull ICurrency iCurrency) { iCurrency.addOrSubtractAmount(100); } });
	            world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.PICKUP.get(), SoundCategory.NEUTRAL, 1.0f, 0.8F / (0.4F + 0.8F));
	            event.getItem().getItem().shrink(1);
			}
			
			if(item == ItemInit.RUPEE_GOLD.get()) {
	            player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).ifPresent(new NonNullConsumer<ICurrency>() {
	                @Override public void accept(@Nonnull ICurrency iCurrency) { iCurrency.addOrSubtractAmount(300); } });
	            world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.PICKUP.get(), SoundCategory.NEUTRAL, 1.0f, 0.8F / (0.4F + 0.8F));
	            event.getItem().getItem().shrink(1);
			}
			
		}
		
	}
}