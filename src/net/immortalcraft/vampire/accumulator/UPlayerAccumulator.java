package net.immortalcraft.vampire.accumulator;

import net.immortalcraft.vampire.entity.UPlayer;

public abstract class UPlayerAccumulator extends Accumulator
{
	protected UPlayer uplayer;
	public UPlayerAccumulator(UPlayer uplayer) { this.uplayer = uplayer; }
}
