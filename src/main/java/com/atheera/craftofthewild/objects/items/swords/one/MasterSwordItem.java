package com.atheera.craftofthewild.objects.items.swords.one;

import java.util.List;

import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MasterSwordItem extends SwordItem {
	
	public String tagCharged = "charged";
	public boolean charged;
	
	public String tagDragon = "dragon";
	public boolean dragon;
	
	public String tagWither = "wither";
	public boolean wither;
	
	public String tagDurability = "durability";
	public int durability;
	
	public String tagLevel = "level";
	public int level;
	public int[] milestones = { 10, 20, 30 };
	
	public String tagCharges = "charges";
	public int charges;
	
	private int timer;
	private int cooldown = 200;
	private int counter;
	
	public MasterSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(KeyboardHelper.isHoldingCtrl()) {
			
			if(nbt.getInt(tagLevel) < milestones[0]) {
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Reach " + milestones[0] + " to get the next upgrade to the sword."));
				
			} else if(nbt.getInt(tagLevel) >= milestones[0] && nbt.getInt(tagLevel) < milestones[1]) {
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Reach " + milestones[0] + " to get the next upgrade to the sword."));
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "The sword also requires the soul of the Wither boss to evolve further."));
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Soul collected: " + nbt.getBoolean(tagWither)));
				
			} else if((nbt.getInt(tagLevel) >= milestones[1] && nbt.getInt(tagLevel) < milestones[2]) && nbt.getBoolean(tagWither)) {
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Reach " + milestones[0] + " to get the next upgrade to the sword."));
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "The sword also requires the soul of the Ender Dragon to evolve further."));
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Soul collected: " + nbt.getBoolean(tagDragon)));
				
			} else if(nbt.getInt(tagLevel) >= milestones[2] && nbt.getBoolean(tagDragon)) {
				tooltip.add(new StringTextComponent(TextFormatting.WHITE + "The sword has been fully upgraded and is at it's greatest power!"));
			} else {
				tooltip.add(new StringTextComponent(TextFormatting.RED + "Something went wrong!"));
			}
		} else if(KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "The legendary sword that seals the darkness. Its blade gleams with a sacred luster that can oppose the Calamity. Only a hero chosen by the sword itself may wield it."));
		} else {
			if(!(nbt.getBoolean(tagCharged))) { 
				tooltip.add(new StringTextComponent(TextFormatting.RED + "Sword is broken, time until repaired: " + (cooldown - timer))); 
				if(timer >= cooldown && !nbt.getBoolean(tagCharged)) {
					tooltip.remove(1);
				}
			}
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Durability remaining: " + nbt.getInt(tagDurability)));
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Is charged: " + nbt.getBoolean(tagCharged)));
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Current level: " + nbt.getInt(tagLevel)));
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Slash charges remaining: " + nbt.getInt(tagCharges)));
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "CTRL" + TextFormatting.WHITE + " for more information."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
		CompoundNBT nbt = stack.getOrCreateTag();
		stack.addEnchantment(Enchantments.SHARPNESS, 5);
		charged = nbt.getBoolean(tagCharged);
		nbt.putBoolean(tagCharged, true);
		durability = nbt.getInt(tagDurability);
		nbt.putInt(tagDurability, 10);
		charges = nbt.getInt(tagCharges);
		nbt.putInt(tagCharges, 0);
		level = nbt.getInt(tagLevel);
		nbt.putInt(tagLevel, 0);
		
		super.onCreated(stack, worldIn, playerIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		CompoundNBT nbt = stack.getOrCreateTag();
		boolean added;
		
		if(!nbt.getBoolean(tagCharged)) { timer++; }
		
		if(timer >= cooldown && !nbt.getBoolean(tagCharged)) {
			added = false;
			timer = 0;
			
			if(nbt.getInt(tagLevel) < milestones[0]) {
				do {
					durability = nbt.getInt(tagDurability);
					nbt.putInt(tagDurability, 10);
					charged = nbt.getBoolean(tagCharged);
					nbt.putBoolean(tagCharged, true);
					stack.addEnchantment(Enchantments.SHARPNESS, 5);
					added = true;
				} while(!added);
			} else if(nbt.getInt(tagLevel) >= milestones[0] && nbt.getInt(tagLevel) < milestones[1]) {
				do {
					durability = nbt.getInt(tagDurability);
					nbt.putInt(tagDurability, 10);
					charged = nbt.getBoolean(tagCharged);
					nbt.putBoolean(tagCharged, true);
					stack.addEnchantment(Enchantments.SHARPNESS, 10);
					added = true;
				} while(!added);
				
			} else if(nbt.getInt(tagLevel) >= milestones[1] && nbt.getInt(tagLevel) < milestones[2]) {
				do {
					durability = nbt.getInt(tagDurability);
					nbt.putInt(tagDurability, 20);
					charged = nbt.getBoolean(tagCharged);
					nbt.putBoolean(tagCharged, true);
					stack.addEnchantment(Enchantments.SHARPNESS, 20);
					added = true;
				} while(!added);
			} else if(nbt.getInt(tagLevel) >= milestones[2]) {
				do {
					durability = nbt.getInt(tagDurability);
					nbt.putInt(tagDurability, 30);
					charged = nbt.getBoolean(tagCharged);
					nbt.putBoolean(tagCharged, true);
					stack.addEnchantment(Enchantments.SHARPNESS, 30);
					added = true;
				} while(!added);
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	public void levelUp(ItemStack stack) {
		CompoundNBT nbt = stack.getOrCreateTag();
		level = nbt.getInt(tagLevel);
		nbt.putInt(tagLevel, level + 1);
		counter++;
		if(counter == 10) {
			charges = nbt.getInt(tagCharges);
			nbt.putInt(tagCharges, charges + 1);
			counter = 0;
		}
	}
	
	public void damageItem(ItemStack stack) {
		CompoundNBT nbt = stack.getOrCreateTag();
		
		if(nbt.getBoolean(tagCharged)) {
			if(nbt.getInt(tagDurability) >= 1) {
				durability = nbt.getInt(tagDurability);
				nbt.putInt(tagDurability, durability -= 1);
			} else if(nbt.getInt(tagDurability) <= 0) {
				charged = nbt.getBoolean(tagCharged);
				nbt.putBoolean(tagCharged, false);
				durability = nbt.getInt(tagDurability);
				nbt.putInt(tagDurability, 0);
				stack.getEnchantmentTagList().clear();
			}
		}
	}
	
	@Override public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) { return false; }
	@Override public boolean isEnchantable(ItemStack stack) { return false; }
	@Override public boolean isBookEnchantable(ItemStack stack, ItemStack book) { return false; }
	@Override public boolean isDamageable(ItemStack stack) { return false; }
	@Override public boolean hasEffect(ItemStack stack) { return stack.getOrCreateTag().getBoolean(tagCharged); }
	
}