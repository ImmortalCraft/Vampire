package net.immortalcraft.vampire.accumulator;

import net.immortalcraft.vampire.VPlayer;

public abstract class VPlayerAccumulator extends Accumulator
{
	protected VPlayer vplayer;
	public VPlayerAccumulator(VPlayer vplayer) { this.vplayer = vplayer; }
}
