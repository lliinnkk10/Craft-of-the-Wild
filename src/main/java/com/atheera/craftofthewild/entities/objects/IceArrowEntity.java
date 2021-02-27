package com.atheera.craftofthewild.entities.objects;

import java.util.Set;

import com.atheera.craftofthewild.init.ItemInit;
import com.google.common.collect.Sets;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IceArrowEntity extends ArrowEntity {
	private final Set<EffectInstance> customPotionEffects = Sets.newHashSet();
	
	@OnlyIn(Dist.CLIENT)
	public IceArrowEntity(EntityType<? extends IceArrowEntity> type, World worldIn) {
		super(type, worldIn);
	}
	public IceArrowEntity(World worldIn, LivingEntity shooter) {
		super(EntityType.ARROW, worldIn);	
	}
	@Override
	public void setPotionEffect(ItemStack stack) {
		if(stack.getItem() == ItemInit.ICE_ARROW.get()) {

			this.customPotionEffects.add(new EffectInstance(Effects.SLOWNESS, 100, 50));
			
		}
	}

}