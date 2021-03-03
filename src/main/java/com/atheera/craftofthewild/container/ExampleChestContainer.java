package com.atheera.craftofthewild.container;

import java.util.Objects;

import com.atheera.craftofthewild.init.BlockInit;
import com.atheera.craftofthewild.init.ModContainerTypes;
import com.atheera.craftofthewild.tileentity.ExampleChestTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class ExampleChestContainer extends Container {
	
	public final ExampleChestTileEntity tileEntity;
	private final IWorldPosCallable canInteractWithCallable;
	
	public ExampleChestContainer(final int windowId, final PlayerInventory playerInventory, final ExampleChestTileEntity tileEntity) {
		super(ModContainerTypes.EXAMPLE_CHEST.get(), windowId);
		this.tileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
		
		//Main inventory
		int startX = 8;
		int startY = 18;
		int slotSizePlus2 = 18;
		for(int row = 0; row < 4; ++row) {
			for(int col = 0; col < 9; ++col) {
				this.addSlot(new Slot(tileEntity, (row * 9) + col, startX + (col * slotSizePlus2), startY + (row * slotSizePlus2)));
			}
		}
		
		//Main player inventory
		int startPlayerInvY = startY * 5 + 12;
		for(int row = 0; row < 3; ++row) {
			for(int col = 0; col < 9; ++col) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + col, startX + (col * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
			}
		}
		
		//Hotbar
		int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
		for(int col = 0; col < 9; ++col) {
			this.addSlot(new Slot(playerInventory, col, startX + (col * slotSizePlus2), hotbarY));
		}
	}
	
	private static ExampleChestTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if(tileAtPos instanceof ExampleChestTileEntity) {
			return (ExampleChestTileEntity)tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct:" + tileAtPos);
	}
	
	public ExampleChestContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.EXAMPLE_CHEST.get());
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if(index < 36) {
				if(!this.mergeItemStack(stack1, 36, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(!this.mergeItemStack(stack1, 0, 36, false)) {
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}
	
}
















