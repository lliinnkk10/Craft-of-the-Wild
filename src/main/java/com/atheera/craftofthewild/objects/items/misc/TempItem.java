package com.atheera.craftofthewild.objects.items.misc;

import javax.annotation.Nonnull;

import com.atheera.craftofthewild.capabilities.rupee.CurrencyCapability;
import com.atheera.craftofthewild.capabilities.rupee.ICurrency;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullConsumer;

public class TempItem extends Item {

	public TempItem(Properties properties) {
		super(properties);
	}
	
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote)
            playerIn.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).ifPresent(new NonNullConsumer<ICurrency>() {
                @Override
                public void accept(@Nonnull ICurrency iCurrency) {
                    playerIn.sendStatusMessage(new StringTextComponent("Currency: "+iCurrency.getAmount()), false);
                    //iCurrency.addOrSubtractAmount(1);
                }
            });
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
	
}