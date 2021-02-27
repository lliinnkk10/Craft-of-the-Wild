package com.atheera.craftofthewild.objects.items.arrows;

import com.atheera.craftofthewild.entities.objects.IceArrowEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class IceArrowItem extends ArrowItem {
	public IceArrowItem(Item.Properties builder) {
		super(builder);
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		IceArrowEntity arrowentity = new IceArrowEntity(worldIn, shooter);
		arrowentity.setPotionEffect(stack);
		return arrowentity;
	}

	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
		return false;
	}
}