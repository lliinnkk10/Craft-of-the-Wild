package com.atheera.craftofthewild.objects.items.swords.one;

import java.util.List;

import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SpringLoadedSwordItem extends SwordItem {

	private int count = 0;
	
	public SpringLoadedSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "This strange hammer is one of Kilton's specialties. Being struck by it doesn't hurt much, but the fourth swing in a string of attacks will send the victim flying."));
		} else {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		count++;
		if(count == 3) { stack.addEnchantment(Enchantments.KNOCKBACK, 6); }
		if(count == 4) { 
			target.addPotionEffect(new EffectInstance(Effects.LEVITATION, 50)); 
			count = 0;
			stack.getEnchantmentTagList().clear();
		} 

		return super.hitEntity(stack, target, attacker);
	}
}