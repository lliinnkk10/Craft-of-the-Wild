package com.atheera.craftofthewild.objects.items.swords.one;

import java.util.List;

import javax.annotation.Nonnull;

import com.atheera.craftofthewild.utils.helpers.KeyboardHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MasterSwordItem extends SwordItem {
	
	//NBT tags
	public String tagCharged = "charged";
	public boolean charged;
	
	public String tagDragon = "dragon";
	public boolean dragon;
	
	public String tagWither = "wither";
	public boolean wither;
	
	public String tagDurability = "durability";
	public int durability;
	
	public String tagLevel = "level";
	public int level;
	
	public String tagCharges = "charges";
	public int charges;
	
	public String tagTimer = "timer";
	public int timer;
	
	public String tagCounter = "count";
	public int counter;
	
	//Local variables
	private final int cooldown = 200;
	private final int milestone1 = 10;
	private final int milestone2 = 20;
	private final int milestone3 = 30;
	
	public MasterSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT nbt = stack.getOrCreateTag();
        int taglevel = nbt.getInt(tagLevel);
        int tagtimer = nbt.getInt(tagTimer);
        boolean tagdragon = nbt.getBoolean(tagDragon);
        boolean tagwither = nbt.getBoolean(tagWither);
        boolean tagcharged = nbt.getBoolean(tagCharged);
        TextFormatting white = TextFormatting.WHITE;
        TextFormatting purple = TextFormatting.LIGHT_PURPLE;
        if(KeyboardHelper.isHoldingCtrl()) { //Adds info of how to level up the sword if CTRL is held down while hovering
 
            if(taglevel < milestone1) { //Less than milestone 1
                tooltip.add(new StringTextComponent(white + "The sword requires " + purple + (milestone1 - taglevel) + white + " kills to get the next upgrade."));
 
            } else if((taglevel >= milestone1 && taglevel < milestone2) || //Less than milestone 2
                      (taglevel >= milestone1 && !tagwither)) { //Or more than milestone 2 but not killed the boss
            	int remaining = milestone2 - taglevel;
            	String text = ("The sword requires " + purple + remaining + white + " kills to get the next upgrade.");
            	if(remaining <= 0) { text = ("You have enough kills for the upgrade but haven't killed the " + purple + "boss" + white + "."); }
                tooltip.add(new StringTextComponent(white + text));
                tooltip.add(new StringTextComponent(white + "The sword also requires the soul of the" + purple + " Wither" + white + " boss to evolve further."));
                tooltip.add(new StringTextComponent(white + "Soul collected: " + purple + tagwither));
 
            } else if(((taglevel >= milestone2 && taglevel < milestone3) && tagwither) || //Less than milestone 3
                      ((taglevel >= milestone2 && tagwither) && !tagdragon)) { //Or more than milestone 3 but not killed the boss
            	int remaining = milestone3 - taglevel;
            	String text = ("The sword requires " + purple + remaining + " kills to get the next upgrade.");
            	if(remaining <= 0) { text = ("You have enough kills for the upgrade but haven't killed the " + purple + "boss" + white + "."); }
                tooltip.add(new StringTextComponent(white + text));
                tooltip.add(new StringTextComponent(white + "The sword also requires the soul of the " + purple + "Ender Dragon" + white + " to evolve further."));
                tooltip.add(new StringTextComponent(white + "Soul collected: " + purple + tagdragon));
 
            } else if(taglevel >= milestone3 && tagdragon && tagwither) { //More than milestone 3
                tooltip.add(new StringTextComponent(white + "The sword has been fully upgraded and is at it's greatest power!"));
           
                //Error message
            } else { tooltip.add(new StringTextComponent(TextFormatting.RED + "Something went horribly wrong!")); }
 
        } else if (KeyboardHelper.isHoldingShift()) { //Adds description from the game to the item if SHIFT is held down while hovering
            tooltip.add(new StringTextComponent(white + "The legendary sword that seals the darkness."));
            tooltip.add(new StringTextComponent(white + "Its blade gleams with a sacred luster that can oppose the Calamity."));
            tooltip.add(new StringTextComponent(white + "Only a hero chosen by the sword itself may wield it."));
 
        } else { //Regular info displayed when not holding SHIFT or CTRL
            if (!tagcharged) { tooltip.add(new StringTextComponent(TextFormatting.RED + "Sword is broken! Time until repaired: " + (cooldown - tagtimer)));
                if (tagtimer >= cooldown && !tagcharged) { tooltip.remove(1); }
            }
            tooltip.add(new StringTextComponent(white + "Durability remaining: " + (nbt.getInt(tagDurability))));
            tooltip.add(new StringTextComponent(white + "Current level: " + taglevel));
            tooltip.add(new StringTextComponent(white + "Slash charges remaining: " + nbt.getInt(tagCharges)));
            tooltip.add(new StringTextComponent(white + "Hold " + purple + "SHIFT" + white + " for description."));
            tooltip.add(new StringTextComponent(white + "Hold " + purple + "CTRL" + white + " for more information."));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
 
    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) { //Just sets the base values of some NBT tags when crafting the item
        CompoundNBT nbt = stack.getOrCreateTag();
        this.charged = nbt.getBoolean(tagCharged);
        nbt.putBoolean(tagCharged, true);
        this.durability = nbt.getInt(tagDurability);
        nbt.putInt(tagDurability, 9);
        this.charges = nbt.getInt(tagCharges);
        nbt.putInt(tagCharges, 0);
        this.level = nbt.getInt(tagLevel);
        nbt.putInt(tagLevel, 0);
        this.wither = nbt.getBoolean(tagWither);
        nbt.putBoolean(tagWither, false);
        this.dragon = nbt.getBoolean(tagDragon);
        nbt.putBoolean(tagDragon, false);
        super.onCreated(stack, worldIn, playerIn);
    }
    
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		CompoundNBT nbt = stack.getOrCreateTag();
		int taglevel = nbt.getInt(tagLevel);
        boolean tagwither = nbt.getBoolean(tagWither);
        boolean tagdragon = nbt.getBoolean(tagDragon);
		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.<Attribute, AttributeModifier>create();

		if(nbt.getBoolean(tagCharged)) {
			if(slot == EquipmentSlotType.MAINHAND) {
				if(taglevel < milestone1) {
					multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",(double)9, Operation.ADDITION ));
		        } else if((taglevel >= milestone1 && taglevel < milestone2 && !tagwither) ||
		                  (taglevel >= milestone1 && !tagwither)) {
		        	multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",(double)19, Operation.ADDITION ));
		        } else if(((taglevel >= milestone2 && taglevel < milestone3) && tagwither) ||
		                  ((taglevel >= milestone2 && tagwither) && !tagdragon)) {
		        	multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",(double)29, Operation.ADDITION ));
		        } else if(taglevel >= milestone3 && tagdragon && tagwither) {
		        	multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",(double)39, Operation.ADDITION ));
		        }
			}
		} else {
			if(slot == EquipmentSlotType.MAINHAND) {
				multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",(double)-Integer.MAX_VALUE, Operation.ADDITION ));
			}
		}
		return multimap;
	}
    
    @Override
    public void inventoryTick(ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        CompoundNBT nbt = stack.getOrCreateTag();
        int taglevel = nbt.getInt(tagLevel);
        int tagtimer = nbt.getInt(tagTimer);
        boolean tagwither = nbt.getBoolean(tagWither);
        boolean tagdragon = nbt.getBoolean(tagDragon);
        
        if(!nbt.getBoolean(tagCharged)) {
            nbt.putInt(tagTimer, tagtimer + 1);
        }
        if(!(tagtimer >= cooldown)) {
            super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
            return;
        }
        nbt.putInt(tagTimer, tagtimer = 0);
        if(taglevel < milestone1) { //Does V if first upgrade milestone not reached
            setNBT(nbt, stack, 19);
 
        } else if((taglevel >= milestone1 && taglevel < milestone2 && !tagwither) ||
                  (taglevel >= milestone1 && !tagwither)) { //Does V if first upgrade milestone is reached
            setNBT(nbt, stack, 19);
 
        } else if(((taglevel >= milestone2 && taglevel < milestone3) && tagwither) ||
                  ((taglevel >= milestone2 && tagwither) && !tagdragon)) { //Does V if second upgrade milestone is reached
            setNBT(nbt, stack, 29);
 
        } else if(taglevel >= milestone3 && tagdragon && tagwither) { //Does V if third upgrade milestone is reached
            setNBT(nbt, stack, 39);
        }
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
 
    private void setNBT(CompoundNBT nbt, ItemStack stack, int durability) {
        nbt.putInt(tagDurability, durability);
        nbt.putBoolean(tagCharged, true);
        this.durability = durability;
        this.charged = true;
    }
 
    public enum EMobType {
        WITHER,
        DRAGON,
        REGULAR
    }
 
    //Method called in LevelupSwordsEvent to level up the sword as well as give it charges per 10 kills
    public void levelUp(ItemStack stack, EMobType type) {
        CompoundNBT nbt = stack.getOrCreateTag();
        int level = nbt.getInt(tagLevel);
        int counter = nbt.getInt(tagCounter);
        switch (type) {
            case DRAGON: {
            	nbt.putInt(tagLevel, level + 10);
            	nbt.putBoolean(tagDragon, true);
            	break;
            }
            case WITHER: {
                nbt.putInt(tagLevel, level + 5);
                nbt.putBoolean(tagWither, true);
                break;
            }
            case REGULAR: {
                nbt.putInt(tagLevel, level + 1);
                break;
            }
        }
        nbt.putInt(tagCounter, counter + 1);
        if (counter == 10) {
            nbt.putInt(tagCharges, nbt.getInt(tagCharges) + 1);
            nbt.putInt(tagCounter, counter = 0);
        }
    }
 
    //Method called in DamageSwordsEvent to remove durability when hitting enemies, if none remain then break the sword
    public void damageItem(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        boolean tagcharged = nbt.getBoolean(tagCharged);
        int tagdurability = nbt.getInt(tagDurability);
        if (!tagcharged) {
            return;
        }
        if (!nbt.contains(tagDurability)) {
            return;
        }
        if (tagdurability >= 1) {
            nbt.putInt(tagDurability, tagdurability - 1);
        } else {
            this.charged = tagcharged;
            nbt.putBoolean(tagCharged, false);
            this.durability = tagdurability;
            nbt.putInt(tagDurability, 0);
            stack.getEnchantmentTagList().clear();
        }
    }
	//Making the sword not enchantable and unbreakable, also giving it enchantment effect when it is not broken
	@Override public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) { return false; }
	@Override public boolean isEnchantable(ItemStack stack) { return false; }
	@Override public boolean isBookEnchantable(ItemStack stack, ItemStack book) { return false; }
	@Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) { return false; }
	@Override public boolean isDamageable(ItemStack stack) { return false; }
	@Override public boolean hasEffect(ItemStack stack) { return stack.getOrCreateTag().getBoolean(tagCharged); }
	
}