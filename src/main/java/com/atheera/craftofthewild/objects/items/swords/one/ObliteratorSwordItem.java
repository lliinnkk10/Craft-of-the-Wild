package com.atheera.craftofthewild.objects.items.swords.one;

import java.util.List;

import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ObliteratorSwordItem extends SwordItem {

	public ObliteratorSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A weapon that defeats foes with one hit and causes the user to die from one hit. It loses its sheen and power after two consecutive usesm byt wukk eventually regain both."));
		} else {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		player.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 20, 420));
		if(player.abilities.isCreativeMode) {
			player.abilities.isCreativeMode = false;
			player.abilities.disableDamage = false;
			player.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 20, 420));
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
}