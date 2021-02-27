package com.atheera.craftofthewild.objects.items.swords.one;

import java.util.List;
import java.util.Random;

import com.atheera.craftofthewild.init.ItemInit;
import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SickleSwordItem extends SwordItem {

	Random rand = new Random();
	
	public SickleSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "A grim weapon favored by the Yiga. The half-moon shape of the blade allows for the rapid delivery of fatal wounds and serves as a symbol of their terror. Its durability is low."));
		} else if(KeyboardHelper.isHoldingCtrl()) {
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "This weapon also functions as a tool for getting more saplings from leaves as well as getting hylian rice from tall grass."));
		} else {	
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "SHIFT" + TextFormatting.WHITE + " for description."));
			tooltip.add(new StringTextComponent(TextFormatting.WHITE + "Hold "+ TextFormatting.LIGHT_PURPLE + "CTRL" + TextFormatting.WHITE + " for more information."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		Block block = state.getBlock();

		if(block == Blocks.GRASS || block == Blocks.TALL_GRASS) {
			ItemStack rice = new ItemStack(ItemInit.HYLIAN_RICE.get());
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
			item.setItem(rice);
			if(rand.nextInt(10) == 9) { world.addEntity(item); }
		}
		
		if(block == Blocks.ACACIA_LEAVES) {
			ItemStack sapling = new ItemStack(Items.ACACIA_SAPLING);
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
			item.setItem(sapling);
			if(rand.nextInt(10) == 9) { world.addEntity(item); }
		}
		
		if(block == Blocks.BIRCH_LEAVES) {
			ItemStack sapling = new ItemStack(Items.BIRCH_SAPLING);
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
			item.setItem(sapling);
			if(rand.nextInt(10) == 9) { world.addEntity(item); }
		}
		
		if(block == Blocks.DARK_OAK_LEAVES) {
			ItemStack sapling = new ItemStack(Items.DARK_OAK_SAPLING);
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
			item.setItem(sapling);
			if(rand.nextInt(10) == 9) { world.addEntity(item); }
		}
		
		if(block == Blocks.JUNGLE_LEAVES) {
			ItemStack sapling = new ItemStack(Items.JUNGLE_SAPLING);
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
			item.setItem(sapling);
			if(rand.nextInt(10) == 9) { world.addEntity(item); }
		}
		
		if(block == Blocks.OAK_LEAVES) {
			ItemStack sapling = new ItemStack(Items.OAK_SAPLING);
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
			item.setItem(sapling);
			if(rand.nextInt(10) == 9) { world.addEntity(item); }
		}
		
		if(block == Blocks.SPRUCE_LEAVES) {
			ItemStack sapling = new ItemStack(Items.SPRUCE_SAPLING);
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
			item.setItem(sapling);
			if(rand.nextInt(10) == 9) { world.addEntity(item); }
		}

		if(!world.isRemote && !state.getBlock().isIn(BlockTags.FIRE)) {
			stack.damageItem(1, entityLiving, (entity) -> {
				entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
		}

		return !state.isIn(BlockTags.LEAVES) && !state.isIn(Blocks.GRASS) && !state.isIn(Blocks.FERN) && !state.isIn(Blocks.VINE)
				? super.onBlockDestroyed(stack, world, state, pos, entityLiving)
				: true;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (!state.isIn(Blocks.COBWEB) && !state.isIn(BlockTags.LEAVES)) {
	         return state.isIn(BlockTags.WOOL) ? 5.0F : super.getDestroySpeed(stack, state);
	      } else {
	         return 15.0F;
	      }
	   }
	
}