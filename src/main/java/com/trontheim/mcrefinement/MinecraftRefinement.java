package com.trontheim.mcrefinement;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

@Mod(
        modid = MinecraftRefinement.MODID,
        name = MinecraftRefinement.NAME,
        version = MinecraftRefinement.VERSION,
        acceptedMinecraftVersions = MinecraftRefinement.MCVERSION
        // updateJSON = "http://minecraft.valgard-lp.de"
)
public class MinecraftRefinement {

  static final String MODID = "mcrefinement";
  static final String NAME = "Minecraft Refinement";
  static final String VERSION = "0.0.2-alpha";
  static final String MCVERSION = "[1.7.10]";

  private Logger logger;

  @Mod.EventHandler
  public void preinit(FMLPreInitializationEvent event)
  {
    this.logger = event.getModLog();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event)
  {
  }

  @Mod.EventHandler
  public void postinit(FMLPostInitializationEvent event)
  {

    String msgPrefix = "";
    if(!((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"))) {
      msgPrefix = "["+MODID+"]: ";
    }

    this.logger.info(msgPrefix + "Item List: " + Item.itemRegistry.getKeys());
    this.logger.info(msgPrefix + "Block List: " + Block.blockRegistry.getKeys());

    HashMap<String, String> supportedModules = new HashMap<String, String>();
    supportedModules.put("harvestcraft", "Pam's HarvestCraft");
    supportedModules.put("plantmegapack", "Plant Mega Pack");
    supportedModules.put("MoCreatures", "DrZhark's Mo'Creatures Mod");
    supportedModules.put("ExtrabiomesXL", "ExtrabiomesXL");
    supportedModules.put("chisel", "Chisel");
    supportedModules.put("UndergroundBiomes", "Underground Biomes Constructs");

    HashMap<String, Boolean> installedModules = new HashMap<String, Boolean>();
    Iterator modules = supportedModules.entrySet().iterator();
    while(modules.hasNext()) {
      HashMap.Entry module = (HashMap.Entry) modules.next();
      String key = module.getKey().toString();
      Boolean value = Loader.isModLoaded(module.getKey().toString());
      installedModules.put(key, value);
      if(value) {
        this.logger.info(msgPrefix + module.getValue().toString() + " support activated.");
      }
      else {
        this.logger.info(msgPrefix + module.getValue().toString() + " not installed.");
      }
    }

    // Plant Mega Pack
    if(installedModules.get("plantmegapack") && installedModules.get("harvestcraft")) {

      // Items
      Item itemFrom;
      Item itemTo;
      Item itemJuice;

      // Juicer
      Item itemJuicer = GameRegistry.findItem("harvestcraft", "juicerItem");

      // Strawberry
      itemFrom = GameRegistry.findItem("plantmegapack", "berriesStraw");
      itemTo = GameRegistry.findItem("harvestcraft", "strawberryItem");
      itemJuice = GameRegistry.findItem("harvestcraft", "strawberryjuiceItem");
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo, 2), new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemJuice), new ItemStack(itemJuicer), new ItemStack(itemFrom));

      // Blueberry
      itemFrom = GameRegistry.findItem("plantmegapack", "berriesBlue");
      itemTo = GameRegistry.findItem("harvestcraft", "blueberryItem");
      itemJuice = GameRegistry.findItem("harvestcraft", "blueberryjuiceItem");
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo, 2), new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemJuice), new ItemStack(itemJuicer), new ItemStack(itemFrom));

      // Blackberry
      itemFrom = GameRegistry.findItem("plantmegapack", "berriesBlack");
      itemTo = GameRegistry.findItem("harvestcraft", "blackberryItem");
      itemJuice = GameRegistry.findItem("harvestcraft", "blackberryjuiceItem");
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo, 2), new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemJuice), new ItemStack(itemJuicer), new ItemStack(itemFrom));

    }

    // ExtraBiomesXL
    if(installedModules.get("ExtrabiomesXL") && installedModules.get("harvestcraft")) {
      // Items
      Item itemFrom;
      Item itemTo;
      Item itemJuice;

      // Juicer
      Item itemJuicer = GameRegistry.findItem("harvestcraft", "juicerItem");

      // Strawberry
      itemFrom = GameRegistry.findItem("ExtrabiomesXL", "extrabiomes.crop");
      itemTo = GameRegistry.findItem("harvestcraft", "strawberryItem");
      itemJuice = GameRegistry.findItem("harvestcraft", "strawberryjuiceItem");
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo, 2), new ItemStack(itemTo), new ItemStack(itemFrom));
      GameRegistry.addShapelessRecipe(new ItemStack(itemJuice), new ItemStack(itemJuicer), new ItemStack(itemFrom));

      // Strawberry seed
      itemFrom = GameRegistry.findItem("ExtrabiomesXL", "extrabiomes.seed");
      itemTo = GameRegistry.findItem("harvestcraft", "strawberryseedItem");
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo), new ItemStack(itemFrom));

      // Chocolate strawberry
      itemFrom = GameRegistry.findItem("ExtrabiomesXL", "extrabiomes.food");
      itemTo = GameRegistry.findItem("harvestcraft", "chocolatestrawberryItem");
      GameRegistry.addShapelessRecipe(new ItemStack(itemTo), new ItemStack(itemFrom, 1, 1));
    }

    Item haystack = null;
    Item strawberrySeed = null;

    // Mo'Creatures
    if(installedModules.get("MoCreatures"))
    {
      haystack = GameRegistry.findItem("MoCreatures", "haystack");
    }

    // ExtraBiomesXL
    if(installedModules.get("ExtrabiomesXL") && installedModules.get("harvestcraft"))
    {
      strawberrySeed = GameRegistry.findItem("ExtrabiomesXL", "extrabiomes.seed");
    }

    Iterator<IRecipe> recipes = CraftingManager.getInstance().getRecipeList().iterator();
    while(recipes.hasNext()) {
      ItemStack itemStack = recipes.next().getRecipeOutput();

      // remove old hay recipes
      if(itemStack != null && (itemStack.getItem() == Items.wheat || itemStack.getItem() == Item.getItemFromBlock(Blocks.hay_block)))
      {
        recipes.remove();
        continue;
      }

      // remove old melon recipes
      if(itemStack != null && (itemStack.getItem() == Items.melon || itemStack.getItem() == Item.getItemFromBlock(Blocks.melon_block)))
      {
        recipes.remove();
        continue;
      }

      // Mo'Creatures
      if(installedModules.get("MoCreatures"))
      {
        // remove old hay stack recipes
        if(itemStack != null && itemStack.getItem() == haystack)
        {
          recipes.remove();
          continue;
        }
      }

      // ExtraBiomesXL
      if(installedModules.get("ExtrabiomesXL") && installedModules.get("harvestcraft"))
      {
        // remove old hay strawberry seed recipes
        if(itemStack != null && itemStack.getItem() == strawberrySeed)
        {
          recipes.remove();
          continue;
        }
      }
    }

    // add new hay recipes
    GameRegistry.addRecipe(new ItemStack(Blocks.hay_block), "##", "##", '#', Items.wheat);
    GameRegistry.addShapelessRecipe(new ItemStack(Items.wheat, 4), new ItemStack(Blocks.hay_block));

    // add new melon recipes
    GameRegistry.addRecipe(new ItemStack(Blocks.melon_block), "##", "##", '#', Items.melon);
    GameRegistry.addShapelessRecipe(new ItemStack(Items.melon, 4), new ItemStack(Blocks.melon_block));

    // Mo'Creatures
    if(installedModules.get("MoCreatures"))
    {
      // add new hay stack recipes
      GameRegistry.addRecipe(new ItemStack(haystack), "ww", 'w', Items.wheat);
      GameRegistry.addShapelessRecipe(new ItemStack(Items.wheat, 2), new ItemStack(haystack));
    }

    // ExtraBiomesXL
    if(installedModules.get("ExtrabiomesXL") && installedModules.get("harvestcraft"))
    {
      GameRegistry.addShapelessRecipe(new ItemStack(GameRegistry.findItem("harvestcraft", "strawberryseedItem")), new ItemStack(strawberrySeed));
    }

    // Underground Biomes
    if(installedModules.get("chisel") && installedModules.get("UndergroundBiomes")) {
      // Marble
      Item marbleUnderground = GameRegistry.findItem("UndergroundBiomes", "metamorphicStone");
      Item marbleChisel = GameRegistry.findItem("chisel", "marble");
      GameRegistry.addShapelessRecipe(new ItemStack(marbleUnderground, 1, 2), new ItemStack(marbleChisel));
      GameRegistry.addShapelessRecipe(new ItemStack(marbleChisel), new ItemStack(marbleUnderground, 1, 2));

    }
  }

}
