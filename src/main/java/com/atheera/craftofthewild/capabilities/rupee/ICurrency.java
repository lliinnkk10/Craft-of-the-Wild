package com.atheera.craftofthewild.capabilities.rupee;

public interface ICurrency {
	
	public int getAmount();
	public void setAmount(int amount);
	public void addOrSubtractAmount(int amount);
	
}