package com.atheera.craftofthewild.objects.items.misc;

import java.util.List;

import com.atheera.craftofthewild.init.ItemInit;
import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GemItem extends Item {

	public GemItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Item item = stack.getItem();
		if(KeyboardHelper.isHoldingShift()) {
			if(item == ItemInit.GEM_SALT.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Crystallized salt from the ancient sea commonly used to season meals. Cannot be eaten in this form.")); }
			if(item == ItemInit.GEM_AMBER.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A fossilized resin with a caramelesque sheen to it. It's been valued as a component in decorations and crafting since ancient times.")); }
			if(item == ItemInit.GEM_OPAL.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A valuable ore that gives off a mesmerizing iridescence similar to the inside of a seashell. It contains the power of water.")); }
			if(item == ItemInit.GEM_LUMINOUS.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "This mysterious mineral gives off a pale blue glow in the dark, which some believe to be souls of the dead. Apparently, this stone can be used as a base to make special clothing.")); }
			if(item == ItemInit.GEM_TOPAZ.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A fossilized resin with a caramelesque sheen to it. It's been valued as a component in decorations and crafting since ancient times.")); }
			if(item == ItemInit.GEM_RUBY.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A precious gem mined from large ore deposits found throughout Hyrule. Rubies contain the power of fire and have fetched a high price since ancient times.")); }
			if(item == ItemInit.GEM_SAPPHIRE.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A precious blue gem mined from nature rock formations. They've been known to fetch a high price since ancient times.")); }
		} else {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}