package com.atheera.craftofthewild.materials;

import java.util.function.Supplier;

import com.atheera.craftofthewild.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModToolTier implements IItemTier {
	
	OBLITERATOR(1, 2, 1.0f, 0.0f, 0, null),
	SPRING(1, 240, 1.0f, 0.0f, 0, () -> {
		return Ingredient.fromItems(ItemInit.ANCIENT_SPRING.get());
	}),
	SICKLE(1, 251, 1.0f, 0.0f, 14, () -> {
		return Ingredient.fromItems(Items.IRON_INGOT);
	}),
	MASTER(4, 5, 10.0f, 1.0f, 0, () -> {
		return Ingredient.fromItems(Items.NETHER_STAR);
	});
	
		
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;
		
	private ModToolTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = new LazyValue<>(repairMaterial);
	}

	@Override public int getMaxUses() { return this.maxUses; }
	@Override public float getEfficiency() { return this.efficiency; }
	@Override public float getAttackDamage() { return this.attackDamage; }
	@Override public int getHarvestLevel() { return this.harvestLevel; }
	@Override public int getEnchantability() { return this.enchantability; }
	@Override public Ingredient getRepairMaterial() { return this.repairMaterial.getValue(); }
}