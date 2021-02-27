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

public class AncientItem extends Item {

	public AncientItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Item item = stack.getItem();
		if(KeyboardHelper.isHoldingShift()) {
			if(item == ItemInit.ANCIENT_SCREW.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A screw used in ancient machinery. It's made of unknown material, and no matter how many times it's turned, its threads never seem to show sign of wear.")); }
			if(item == ItemInit.ANCIENT_SPRING.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A spring used in ancient machinery. It is light and buoyant enough to float on water, and no matter how many times it's compressed, it never loses tension.")); }
			if(item == ItemInit.ANCIENT_SHAFT.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A machine part used in ancient machinery. It's incredibly sturdy, and it's not made of any recognizable material. It may come in handy someday.")); }
			if(item == ItemInit.ANCIENT_GEAR.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A gear used in ancient machinery. Despite being incredibly old, its build quality is leaps and bounds above anything built using current technology.")); }
			if(item == ItemInit.ANCIENT_CORE.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "This crystal was made using lost technology. At one time it was the power source for ancient machines. This item is very valuable to researchers.")); }
			if(item == ItemInit.ANCIENT_GIANT_CORE.get()) { tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A giant energy crystal made using lost ancient technology. Cores this large are an extremely rare find. A researcher would probably know how to use this.")); }
		} else {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}