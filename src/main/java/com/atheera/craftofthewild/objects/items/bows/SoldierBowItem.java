package com.atheera.craftofthewild.objects.items.bows;

import java.util.List;

import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SoldierBowItem extends BowItem {
	
	public SoldierBowItem(Item.Properties builder) {
		super(builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "This bow designed for armed conflict. Inflicts more damage than a normal bow, but it will still burn if it touches fire."));
		} else {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	
	
}