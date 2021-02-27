package com.atheera.craftofthewild.objects.items.misc;

import java.util.List;

import com.atheera.craftofthewild.init.ItemInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class RupeeItem extends Item{

	public RupeeItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Item item = stack.getItem();
		
		if(item == ItemInit.RUPEE_GREEN.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Worth 1 Rupee.")); }
		if(item == ItemInit.RUPEE_BLUE.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Worth 5 Rupees.")); }
		if(item == ItemInit.RUPEE_RED.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Worth 20 Rupees.")); }
		if(item == ItemInit.RUPEE_PURPLE.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Worth 50 Rupees.")); }
		if(item == ItemInit.RUPEE_SILVER.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Worth 100 Rupees.")); }
		if(item == ItemInit.RUPEE_GOLD.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Worth 300 Rupees.")); }
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}