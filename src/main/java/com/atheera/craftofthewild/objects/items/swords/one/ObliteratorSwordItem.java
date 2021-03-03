package com.atheera.craftofthewild.objects.items.swords.one;

import java.util.List;

import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ObliteratorSwordItem extends SwordItem {

	public String tagCharged = "charged";
	public boolean charged;
	
	public String tagDurability = "durability";
	public int durability;
	
	public String tagTimer = "timer";
	public int timer;
	
	public String tagDamage = "damage";
	public int damage;
	
	private final int cooldown = 200;
	
	public ObliteratorSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A weapon that defeats foes with one hit and causes the user to die from one hit. It loses its sheen and power after two consecutive usesm byt wukk eventually regain both."));
		} else {
            if (!nbt.getBoolean(tagCharged)) { tooltip.add(new StringTextComponent(TextFormatting.RED + "Sword is broken do not use it again! Time until repaired: " + (cooldown - nbt.getInt(tagTimer))));
            	if (nbt.getInt(tagTimer) >= cooldown && !nbt.getBoolean(tagCharged)) { tooltip.remove(1); }
        }
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Durability remaining: " + (nbt.getInt(tagDurability) + 1)));
            tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		CompoundNBT nbt = stack.getOrCreateTag();
		boolean tagcharged = nbt.getBoolean(tagCharged);
		PlayerAbilities tagabilities = player.abilities;

		if(tagcharged) {
			player.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 20, 420));
			if(tagabilities.isCreativeMode) {
				tagabilities.isCreativeMode = false;
				tagabilities.disableDamage = false;
				player.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 20, 420));
			}
		}
		
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		CompoundNBT nbt = stack.getOrCreateTag();
		int tagtimer = nbt.getInt(tagTimer);
		
		if(!nbt.getBoolean(tagCharged)) {
			nbt.putInt(tagTimer, tagtimer + 1);
		}
        if(!(tagtimer >= cooldown)) {
            super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
            return;
        }
        nbt.putInt(tagTimer, tagtimer = 0);
        this.durability = nbt.getInt(tagDurability);
        nbt.putInt(tagDurability, 1);
        this.charged = nbt.getBoolean(tagCharged);
        nbt.putBoolean(tagCharged, true);
        
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
		CompoundNBT nbt = stack.getOrCreateTag();
		this.charged = nbt.getBoolean(tagCharged);
		nbt.putBoolean(tagCharged, true);
		this.durability = nbt.getInt(tagDurability);
		nbt.putInt(tagDurability, 1);
		super.onCreated(stack, worldIn, playerIn);
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		CompoundNBT nbt = stack.getOrCreateTag();
		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.<Attribute, AttributeModifier>create();

		if(nbt.getBoolean(tagCharged)) {
			if(slot == EquipmentSlotType.MAINHAND) {
				multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",(double)Integer.MAX_VALUE, Operation.ADDITION ));
			}
		} else {
			if(slot == EquipmentSlotType.MAINHAND) {
				multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",(double)-Integer.MAX_VALUE, Operation.ADDITION ));
			}
		}
		return multimap;
	}
	
	public void damageItem(ItemStack stack) {
		CompoundNBT nbt = stack.getOrCreateTag();
		boolean tagcharged = nbt.getBoolean(tagCharged);
		int tagdurability = nbt.getInt(tagDurability);
		if(!tagcharged) {
			return;
		}
		if(!nbt.contains(tagDurability)) {
			return;
		}
		if(tagdurability >= 1) {
			nbt.putInt(tagDurability, tagdurability -1);
		} else {
            this.charged = tagcharged;
            nbt.putBoolean(tagCharged, false);
            this.durability = tagdurability;
            nbt.putInt(tagDurability, 0);
            stack.getEnchantmentTagList().clear();
		}
	}
	
	//Making the sword not enchantable and unbreakable, also giving it enchantment effect when it is not broken
	@Override public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) { return false; }
	@Override public boolean isEnchantable(ItemStack stack) { return false; }
	@Override public boolean isBookEnchantable(ItemStack stack, ItemStack book) { return false; }
	@Override public boolean isDamageable(ItemStack stack) { return false; }
	@Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) { return false; }
	@Override public boolean hasEffect(ItemStack stack) { return stack.getOrCreateTag().getBoolean(tagCharged); }
}