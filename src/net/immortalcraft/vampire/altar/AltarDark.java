package net.immortalcraft.vampire.altar;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.mcore.util.MUtil;
import net.immortalcraft.vampire.InfectionReason;
import net.immortalcraft.vampire.Lang;
import net.immortalcraft.vampire.VPerm;
import net.immortalcraft.vampire.VPlayer;
import net.immortalcraft.vampire.util.FxUtil;
import net.immortalcraft.vampire.util.ResourceUtil;

public class AltarDark extends Altar
{
	public AltarDark()
	{
		this.name = Lang.altarDarkName;
		this.desc = Lang.altarDarkDesc;
		
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
	public void use(VPlayer vplayer, Player player)
	{
		vplayer.msg("");
		vplayer.msg(this.desc);
		
		if ( ! VPerm.ALTAR_DARK.has(player, true)) return;
		
		vplayer.msg(Lang.altarDarkCommon);
		FxUtil.ensure(PotionEffectType.BLINDNESS, player, 12*20);
		vplayer.runFxSmoke();
		
		if (vplayer.isHealthy())
		{
			if ( ! ResourceUtil.playerRemoveAttempt(player, this.resources, Lang.altarResourceSuccess, Lang.altarResourceFail)) return;
			vplayer.msg(Lang.altarDarkHealthy);
			player.getWorld().strikeLightningEffect(player.getLocation().add(0, 3, 0));
			vplayer.runFxSmokeBurst();
			vplayer.addInfection(0.01D, InfectionReason.ALTAR, null);
		}
		else if (vplayer.isVampire())
		{
			vplayer.msg(Lang.altarDarkVampire);
		}
		else if (vplayer.isInfected())
		{
			vplayer.msg(Lang.altarDarkInfected);
		}
	}
}
