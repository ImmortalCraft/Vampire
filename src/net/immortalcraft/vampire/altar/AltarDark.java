package net.immortalcraft.vampire.altar;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.mcore.util.MUtil;
import net.immortalcraft.vampire.InfectionReason;
import net.immortalcraft.vampire.Perm;
import net.immortalcraft.vampire.entity.MLang;
import net.immortalcraft.vampire.entity.UPlayer;
import net.immortalcraft.vampire.util.FxUtil;
import net.immortalcraft.vampire.util.ResourceUtil;

public class AltarDark extends Altar
{
	public AltarDark()
	{
		this.name = MLang.get().altarDarkName;
		this.desc = MLang.get().altarDarkDesc;
		
		this.coreMaterial = Material.GOLD_BLOCK;
		
		this.materialCounts = new HashMap<Material, Integer>();
		this.materialCounts.put(Material.OBSIDIAN, 30);
		this.materialCounts.put(Material.WEB, 5);
		this.materialCounts.put(Material.DEAD_BUSH, 5);
		this.materialCounts.put(Material.DIAMOND_BLOCK, 2);
		
		this.resources = MUtil.list(
			new ItemStack(Material.MUSHROOM_SOUP, 1),
			new ItemStack(Material.BONE, 10),
			new ItemStack(Material.SULPHUR, 10),
			new ItemStack(Material.REDSTONE, 10)
		);
	}
	
	@Override
	public void use(UPlayer uplayer, Player player)
	{
		uplayer.msg("");
		uplayer.msg(this.desc);
		
		if ( ! Perm.ALTAR_DARK.has(player, true)) return;
		
		uplayer.msg(MLang.get().altarDarkCommon);
		FxUtil.ensure(PotionEffectType.BLINDNESS, player, 12*20);
		uplayer.runFxSmoke();
		
		if (uplayer.isHealthy())
		{
			if ( ! ResourceUtil.playerRemoveAttempt(player, this.resources, MLang.get().altarResourceSuccess, MLang.get().altarResourceFail)) return;
			uplayer.msg(MLang.get().altarDarkHealthy);
			player.getWorld().strikeLightningEffect(player.getLocation().add(0, 3, 0));
			uplayer.runFxSmokeBurst();
			uplayer.addInfection(0.01D, InfectionReason.ALTAR, null);
		}
		else if (uplayer.isVampire())
		{
			uplayer.msg(MLang.get().altarDarkVampire);
		}
		else if (uplayer.isInfected())
		{
			uplayer.msg(MLang.get().altarDarkInfected);
		}
	}
	
}
