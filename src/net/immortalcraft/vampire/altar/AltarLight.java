package net.immortalcraft.vampire.altar;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.mcore.util.MUtil;
import net.immortalcraft.vampire.HolyWaterUtil;
import net.immortalcraft.vampire.Perm;
import net.immortalcraft.vampire.entity.MLang;
import net.immortalcraft.vampire.entity.UConf;
import net.immortalcraft.vampire.entity.UPlayer;
import net.immortalcraft.vampire.util.ResourceUtil;

public class AltarLight extends Altar
{
	public AltarLight()
	{
		this.name = MLang.get().altarLightName;
		this.desc = MLang.get().altarLightDesc;
		
		this.coreMaterial = Material.LAPIS_BLOCK;
		
		this.materialCounts = new HashMap<Material, Integer>();
		this.materialCounts.put(Material.GLOWSTONE, 30);
		this.materialCounts.put(Material.YELLOW_FLOWER, 5);
		this.materialCounts.put(Material.RED_ROSE, 5);
		this.materialCounts.put(Material.DIAMOND_BLOCK, 2);
		
		this.resources = MUtil.list(
			new ItemStack(Material.WATER_BUCKET, 1),
			new ItemStack(Material.DIAMOND, 1),
			new ItemStack(Material.SUGAR, 20),
			new ItemStack(Material.WHEAT, 20)
		);
	}
	
	@Override
	public void use(UPlayer uplayer, Player player)
	{
		UConf uconf = UConf.get(player);
		uplayer.msg("");
		uplayer.msg(this.desc);
		
		if ( ! Perm.ALTAR_LIGHT.has(player, true)) return;
		
		if ( ! uplayer.isVampire() && playerHoldsWaterBottle(player))
		{
			if ( ! ResourceUtil.playerRemoveAttempt(player, uconf.holyWaterResources, MLang.get().altarLightWaterResourceSuccess, MLang.get().altarLightWaterResourceFail)) return;
			ResourceUtil.playerAdd(player, HolyWaterUtil.createItemStack());
			uplayer.msg(MLang.get().altarLightWaterResult);
			uplayer.runFxEnderBurst();
			return;
		}
		
		uplayer.msg(MLang.get().altarLightCommon);
		uplayer.runFxEnder();
		
		if (uplayer.isVampire())
		{
			if ( ! ResourceUtil.playerRemoveAttempt(player, this.resources, MLang.get().altarResourceSuccess, MLang.get().altarResourceFail)) return;
			uplayer.msg(MLang.get().altarLightVampire);
			player.getWorld().strikeLightningEffect(player.getLocation().add(0, 3, 0));
			uplayer.runFxEnderBurst();
			uplayer.setVampire(false);
			return;
		}
		else if (uplayer.isHealthy())
		{
			uplayer.msg(MLang.get().altarLightHealthy);
		}
		else if (uplayer.isInfected())
		{
			uplayer.msg(MLang.get().altarLightInfected);
			uplayer.setInfection(0);
			uplayer.runFxEnderBurst();
		}
	}
	
	protected static boolean playerHoldsWaterBottle(Player player)
	{
		if (player.getItemInHand().getType() != Material.POTION) return false;
		return player.getItemInHand().getDurability() == 0;
	}
	
}
