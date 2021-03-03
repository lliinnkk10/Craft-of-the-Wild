package com.atheera.craftofthewild.init;

import com.atheera.craftofthewild.Main;
import com.atheera.craftofthewild.materials.ModToolTier;
import com.atheera.craftofthewild.materials.Rarities;
import com.atheera.craftofthewild.objects.items.arrows.IceArrowItem;
import com.atheera.craftofthewild.objects.items.bows.RoyalBowItem;
import com.atheera.craftofthewild.objects.items.bows.SoldierBowItem;
import com.atheera.craftofthewild.objects.items.misc.AncientItem;
import com.atheera.craftofthewild.objects.items.misc.GemItem;
import com.atheera.craftofthewild.objects.items.misc.RupeeItem;
import com.atheera.craftofthewild.objects.items.misc.TempItem;
import com.atheera.craftofthewild.objects.items.swords.one.MasterSwordItem;
import com.atheera.craftofthewild.objects.items.swords.one.ObliteratorSwordItem;
import com.atheera.craftofthewild.objects.items.swords.one.SickleSwordItem;
import com.atheera.craftofthewild.objects.items.swords.one.SpringLoadedSwordItem;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	//initiate
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	private static ItemGroup iTab = Main.COTW_I_IG; //items
	private static ItemGroup fTab = Main.COTW_F_IG; //food
	private static ItemGroup wTab = Main.COTW_W_IG; //weapons
	//private static ItemGroup aTab = Main.COTW_A_IG; //armour
	
	//create
		//regular items
	public static final RegistryObject<RupeeItem> RUPEE_GREEN = ITEMS.register("item_rupee_green", () -> new RupeeItem(new Item.Properties().group(iTab).maxStackSize(1)));
	public static final RegistryObject<RupeeItem> RUPEE_BLUE = ITEMS.register("item_rupee_blue", () -> new RupeeItem(new Item.Properties().group(iTab).maxStackSize(1)));
	public static final RegistryObject<RupeeItem> RUPEE_RED = ITEMS.register("item_rupee_red", () -> new RupeeItem(new Item.Properties().group(iTab).maxStackSize(1)));
	public static final RegistryObject<RupeeItem> RUPEE_PURPLE = ITEMS.register("item_rupee_purple", () -> new RupeeItem(new Item.Properties().group(iTab).maxStackSize(1)));
	public static final RegistryObject<RupeeItem> RUPEE_SILVER = ITEMS.register("item_rupee_silver", () -> new RupeeItem(new Item.Properties().group(iTab).maxStackSize(1)));
	public static final RegistryObject<RupeeItem> RUPEE_GOLD = ITEMS.register("item_rupee_gold", () -> new RupeeItem(new Item.Properties().group(iTab).maxStackSize(1)));
	
	public static final RegistryObject<GemItem> GEM_SALT = ITEMS.register("item_gem_salt", () -> new GemItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<GemItem> GEM_AMBER = ITEMS.register("item_gem_amber", () -> new GemItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<GemItem> GEM_OPAL = ITEMS.register("item_gem_opal", () -> new GemItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<GemItem> GEM_LUMINOUS = ITEMS.register("item_gem_luminous", () -> new GemItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<GemItem> GEM_TOPAZ = ITEMS.register("item_gem_topaz", () -> new GemItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<GemItem> GEM_RUBY = ITEMS.register("item_gem_ruby", () -> new GemItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<GemItem> GEM_SAPPHIRE = ITEMS.register("item_gem_sapphire", () -> new GemItem(new Item.Properties().group(iTab)));
		//special items
	public static final RegistryObject<TempItem> TEMP = ITEMS.register("temp", () -> new TempItem(new Item.Properties().group(iTab)));
	
		//crafting items
	public static final RegistryObject<AncientItem> ANCIENT_SCREW = ITEMS.register("item_ancient_screw", () -> new AncientItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<AncientItem> ANCIENT_SPRING = ITEMS.register("item_ancient_spring", () -> new AncientItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<AncientItem> ANCIENT_SHAFT = ITEMS.register("item_ancient_shaft", () -> new AncientItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<AncientItem> ANCIENT_GEAR = ITEMS.register("item_ancient_gear", () -> new AncientItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<AncientItem> ANCIENT_CORE = ITEMS.register("item_ancient_core", () -> new AncientItem(new Item.Properties().group(iTab)));
	public static final RegistryObject<AncientItem> ANCIENT_GIANT_CORE = ITEMS.register("item_ancient_core_giant", () -> new AncientItem(new Item.Properties().group(iTab)));
	
		//food
			//ingredients
	public static final RegistryObject<Item> DURIAN = ITEMS.register("ing_hearty_durian", () -> new Item(new Item.Properties().group(fTab)
		.food(new Food.Builder()
		.effect(() -> new EffectInstance(Effects.ABSORPTION, Integer.MAX_VALUE), 1.0f)
		.hunger(2)
		.saturation(0.3f)
		.setAlwaysEdible()
		.build())));
	public static final RegistryObject<Item> FLEET_LOTUS_SEEDS = ITEMS.register("ing_hasty_lotus", () -> new Item(new Item.Properties().group(fTab)
		.food(new Food.Builder()
		.effect(() -> new EffectInstance(Effects.SPEED, 20*20), 1)
		.hunger(1)
		.saturation(0.1f)
		.setAlwaysEdible()
		.build())));
	public static final RegistryObject<Item> MIGHTY_BANANAS = ITEMS.register("ing_mighty_bananas", () -> new Item(new Item.Properties().group(fTab)
		.food(new Food.Builder()
		.effect(() -> new EffectInstance(Effects.STRENGTH, 20*20), 1)
		.hunger(2)
		.saturation(0.2f)
		.setAlwaysEdible()
		.build())));
	public static final RegistryObject<Item> TOUGH_SHROOM = ITEMS.register("ing_tough_shroom", () -> new Item(new Item.Properties().group(fTab)
		.food(new Food.Builder()
		.effect(() -> new EffectInstance(Effects.RESISTANCE, 20*20), 1)
		.hunger(1)
		.saturation(0.1f)
		.setAlwaysEdible()
		.build())));
	public static final RegistryObject<Item> STAMINOKA_BASS = ITEMS.register("ing_energizing_bass", () -> new Item(new Item.Properties().group(fTab)
		.food(new Food.Builder()
		.effect(() -> new EffectInstance(Effects.SATURATION, 20*20), 1)
		.hunger(2)
		.saturation(0.2f)
		.setAlwaysEdible()
		.build())));
	public static final RegistryObject<Item> PREPARED_MEAT = ITEMS.register("ing_prepared_meat", () -> new Item(new Item.Properties().group(fTab)
		.food(new Food.Builder()
		.hunger(4)
		.saturation(0.5f)
		.meat()
		.setAlwaysEdible()
		.build())));
	public static final RegistryObject<Item> HYLIAN_RICE = ITEMS.register("ing_hylian_rice", () -> new Item(new Item.Properties().group(fTab)));
			//cooked
	
		//elixirs

		//1h swords
	public static final RegistryObject<MasterSwordItem> SWORD_MASTER = ITEMS.register("sword_master", () -> new MasterSwordItem(ModToolTier.MASTER, (int) 0, -2.4f, new Item.Properties().group(wTab).isImmuneToFire().rarity(Rarities.GOLD)));
	public static final RegistryObject<SickleSwordItem> SWORD_SICKLE = ITEMS.register("sword_sickle", () -> new SickleSwordItem(ModToolTier.SICKLE, (int) 13, -2.0f, new Item.Properties().group(wTab)));
	public static final RegistryObject<ObliteratorSwordItem> SWORD_OBLITERATOR = ITEMS.register("sword_obliterator", () -> new ObliteratorSwordItem(ModToolTier.OBLITERATOR, (int) 1, -2.4f, new Item.Properties().group(wTab).rarity(Rarities.GOLD)));
	public static final RegistryObject<SpringLoadedSwordItem> SWORD_SPRING = ITEMS.register("sword_spring_hammer", () -> new SpringLoadedSwordItem(ModToolTier.SPRING, (int) 2, -2.4f, new Item.Properties().group(wTab)));
	
		//2h swords //slow attacks, high damage
	
		//spears //throwable, fast attacks, low damage
	
		//shields
	
		//bows
	public static final RegistryObject<SoldierBowItem> SOLDIER_BOW = ITEMS.register("bow_soldier", () -> new SoldierBowItem((new Item.Properties()).maxDamage(768).group(wTab)));
	public static final RegistryObject<RoyalBowItem> ROYAL_BOW = ITEMS.register("bow_royal", () -> new RoyalBowItem((new Item.Properties()).maxDamage(1536).group(wTab)));
			//arrows
	public static final RegistryObject<IceArrowItem> ICE_ARROW = ITEMS.register("item_arrow_ice", () -> new IceArrowItem(new Item.Properties().group(wTab)));
	
		//rods
	
		//armours
			//helmets
	
			//bodies
	
			//legs
	
			//boots
}