package com.atheera.craftofthewild;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.atheera.craftofthewild.capabilities.rupee.CurrencyCapability;
import com.atheera.craftofthewild.init.EntityInit;
import com.atheera.craftofthewild.init.ItemInit;
import com.atheera.craftofthewild.init.SoundInit;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("craftofthewild")
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class Main {
	
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "craftofthewild";
    public static Main instance;

    public Main() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::enqueueIMC);
    	modEventBus.addListener(this::processIMC);
    	modEventBus.addListener(this::doClientStuff);
    	
    	SoundInit.SOUNDS.register(modEventBus);
    	ItemInit.ITEMS.register(modEventBus);
    	EntityInit.ENTITY_TYPES.register(modEventBus);
    	//CapabilityManager.INSTANCE.register(RupeeCapability.class, null, null);
    	
        MinecraftForge.EVENT_BUS.register(this);
        instance = this;
    }

    private void setup(final FMLCommonSetupEvent event) { 
    	CurrencyCapability.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {  }

    private void enqueueIMC(final InterModEnqueueEvent event) {  }

    private void processIMC(final InterModProcessEvent event) {  }
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    	
    }

    //Event used to attach the currency to all players
    @SubscribeEvent
    public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
    	event.addCapability(new ResourceLocation(Main.MOD_ID, "currency"), new CurrencyCapability());
    }
    
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents { @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        }
    }
    
    public static final ItemGroup COTW_I_IG = new ItemGroup("cotw_item_tab") { @Override public ItemStack createIcon() { return new ItemStack(ItemInit.RUPEE_GREEN.get()); } };
    public static final ItemGroup COTW_F_IG = new ItemGroup("cotw_food_tab") { @Override public ItemStack createIcon() { return new ItemStack(ItemInit.DURIAN.get()); } };
    public static final ItemGroup COTW_W_IG = new ItemGroup("cotw_weapon_tab") { @Override public ItemStack createIcon() { return new ItemStack(ItemInit.SWORD_MASTER.get()); } };
    public static final ItemGroup COTW_A_IG = new ItemGroup("cotw_armor_tab") { @Override public ItemStack createIcon() { return new ItemStack(ItemInit.SWORD_MASTER.get()); } };
    //public static final ItemGroup CTOW_B_IH = new ItemGroup("cotw_block_tab") { @Override public ItemStack createIcon() { return new ItemStack(BlockInit..get()); } };
    
}