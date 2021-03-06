/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogGameObjects;
import frogcraftrebirth.common.block.*;
import frogcraftrebirth.common.item.*;
import frogcraftrebirth.common.lib.FrogFluid;
import frogcraftrebirth.common.lib.util.ItemFactory;
import frogcraftrebirth.common.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID)
public final class FrogRegistries {

	@SubscribeEvent
	public static void regBlock(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.registerAll(
				new BlockMineral(MapColor.DIRT, "shovel", 0).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("carnallite").setRegistryName("carnallite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("dewalquite").setRegistryName("dewalquite"),
				new BlockMineral(MapColor.STONE, "pickaxe", 2).setHardness(5.0F).setResistance(15.0F).setUnlocalizedName("fluorapatite").setRegistryName("fluorapatite"),
				new BlockHSU(TileHSU.class).setUnlocalizedName("hsu").setRegistryName("hsu"),
				new BlockHSU(TileHSUUltra.class).setUnlocalizedName("uhsu").setRegistryName("uhsu"),
				new BlockMPS().setUnlocalizedName("mobile_power_station").setRegistryName("mobile_power_station"),
				new BlockMachinery(TileAirPump.class).setUnlocalizedName("air_pump").setRegistryName("air_pump"),
				new BlockMachinery(TileLiquefier.class).setUnlocalizedName("liquefier").setRegistryName("liquefier"),
				new BlockMachineryDirectional(TileAdvChemReactor.class).setUnlocalizedName("advanced_chemical_reactor").setRegistryName("advanced_chemical_reactor"),
				new BlockMachineryDirectional(TileAdvBlastFurnace.class).setUnlocalizedName("advanced_blast_furnace").setRegistryName("advanced_blast_furnace"),
				new BlockMachineryDirectional(TileCombustionFurnace.class).setUnlocalizedName("combustion_furnace").setRegistryName("combustion_furnace"),
				new BlockMachineryDirectional(TilePyrolyzer.class).setUnlocalizedName("pyrolyzer").setRegistryName("pyrolyzer"),
				new BlockMachineryDirectional(TileCondenseTower.class).setUnlocalizedName("condense_tower_core").setRegistryName("condense_tower_core"),
				new BlockMechanism(TileCondenseTowerStructure.class).setUnlocalizedName("condense_tower_cylinder").setRegistryName("condense_tower_cylinder"),
				new BlockMechanismDirectional(TileFluidOutputHatch.class).setUnlocalizedName("condense_tower_outlet").setRegistryName("condense_tower_outlet")
		);
		GameRegistry.registerTileEntity(TileMobilePowerStation.class, "frogcraftrebirth:mobile_power_station");
		GameRegistry.registerTileEntity(TileHSU.class, "frogcraftrebirth:hybrid_storage_unit");
		GameRegistry.registerTileEntity(TileHSUUltra.class, "frogcraftrebirth:ultra_hybrid_storage_unit");
		GameRegistry.registerTileEntity(TileAirPump.class, "frogcraftrebirth:air_pump");
		GameRegistry.registerTileEntity(TileCondenseTower.class, "frogcraftrebirth:condense_tower_core");
		GameRegistry.registerTileEntity(TileCondenseTowerStructure.class, "frogcraftrebirth:condense_tower_cylinder");
		GameRegistry.registerTileEntity(TileFluidOutputHatch.class, "frogcraftrebirth:condense_tower_outlet");
		GameRegistry.registerTileEntity(TileCombustionFurnace.class, "frogcraftrebirth:combustion_furnace");
		GameRegistry.registerTileEntity(TilePyrolyzer.class, "frogcraftrebirth:thermal_cracker");
		GameRegistry.registerTileEntity(TileAdvChemReactor.class, "frogcraftrebirth:advanced_chemical_reactor");
		GameRegistry.registerTileEntity(TileLiquefier.class, "frogcraftrebirth:liquefier");
		GameRegistry.registerTileEntity(TileAdvBlastFurnace.class, "frogcraftrebirth:adv_blast_furnace");

		FrogFluids.ammonia = new FrogFluid("ammonia", 694, 240, true, EnumRarity.EPIC);
		FrogFluids.argon = new FrogFluid("argon", 1784, 300, true, EnumRarity.RARE);
		FrogFluids.benzene = new FrogFluid("benzene", 877, 300, true, EnumRarity.EPIC);
		FrogFluids.bromine = new FrogFluid("bromine", 3103, 300, false, EnumRarity.UNCOMMON);
		FrogFluids.carbonOxide = new FrogFluid("carbon_oxide", 1250, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.carbonDioxide = new FrogFluid("carbon_dioxide", 1980, 300, true, EnumRarity.COMMON);
		FrogFluids.chlorine = new FrogFluid("chlorine", 320, 300, true, EnumRarity.RARE);
		FrogFluids.coalTar = new FrogFluid("coal_tar", 2000, 300, false, EnumRarity.RARE).setViscosity(2000);
		FrogFluids.fluorine = new FrogFluid("fluorine", 1696, 300, true, EnumRarity.EPIC);
		FrogFluids.glycerol = new FrogFluid("glycerol", 2000, 300, false, EnumRarity.UNCOMMON);
		FrogFluids.liquefiedAir = new FrogFluid("liquefied_air", 1293, 160, false, EnumRarity.EPIC);
		FrogFluids.methane = new FrogFluid("methane", 656, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.nitricAcid = new FrogFluid("nitric_acid", "nitric_acid_flow", "nitric_acid", 1420, 300, false, EnumRarity.RARE);
		FrogFluids.nitrogen = new FrogFluid("nitrogen", 1251, 160, true, EnumRarity.COMMON);
		FrogFluids.nitrogenOxide = new FrogFluid("nitrogen_oxide", 1340, 300, true, EnumRarity.RARE);
		FrogFluids.oleum = new FrogFluid("oleum", 1820, 300, false, EnumRarity.RARE);
		FrogFluids.potassiumHydroxide = new FrogFluid("potassium_hydroxide", 2120, 300, false, EnumRarity.RARE);
		FrogFluids.sulfuricAcid = new FrogFluid("sulfuric_acid", 1840, 300, false, EnumRarity.RARE);
		FrogFluids.sulfurDioxide = new FrogFluid("sulfur_dioxide", 1640, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.sulfurTrioxide = new FrogFluid("sulfur_trioxide", 1800, 300, true, EnumRarity.RARE);

		regFluids(
				FrogFluids.ammonia,
				FrogFluids.argon,
				FrogFluids.benzene,
				FrogFluids.bromine,
				FrogFluids.carbonOxide,
				FrogFluids.carbonDioxide,
				FrogFluids.chlorine,
				FrogFluids.fluorine,
				FrogFluids.glycerol,
				FrogFluids.liquefiedAir,
				FrogFluids.methane,
				FrogFluids.nitrogen,
				FrogFluids.nitrogenOxide,
				FrogFluids.oleum,
				FrogFluids.potassiumHydroxide,
				FrogFluids.sulfurDioxide,
				FrogFluids.sulfurTrioxide
		);
		regFluids(registry,
				FrogFluids.coalTar,
				FrogFluids.sulfuricAcid
		);
		FluidRegistry.registerFluid(FrogFluids.nitricAcid);
		Block nitricAcidBlock = new BlockNitricAcid(FrogFluids.nitricAcid).setRegistryName("nitric_acid");
		registry.register(nitricAcidBlock);
		FrogFluids.nitricAcid.setBlock(nitricAcidBlock);
		FluidRegistry.addBucketForFluid(FrogFluids.nitricAcid);
    }

	@SubscribeEvent
	public static void regItem(RegistryEvent.Register<Item> event) {
		final ItemFactory factory = new ItemFactory();
		event.getRegistry().registerAll(
				new ItemFrogBlock(FrogGameObjects.CARNALLITE).setRegistryName("carnallite"),
				new ItemFrogBlock(FrogGameObjects.DEWALQUITE).setRegistryName("dewalquite"),
				new ItemFrogBlock(FrogGameObjects.FLUORAPATITE).setRegistryName("fluorapatite"),
				new ItemFrogBlock(FrogGameObjects.HSU).setRegistryName("hsu"),
				new ItemFrogBlock(FrogGameObjects.UHSU).setRegistryName("uhsu"),
				new ItemFrogBlock(FrogGameObjects.AIR_PUMP).setRegistryName("air_pump"),
				new ItemFrogBlock(FrogGameObjects.LIQUEFIER).setRegistryName("liquefier"),
				new ItemFrogBlock(FrogGameObjects.ADV_CHEM_REACTOR).setRegistryName("advanced_chemical_reactor"),
				new ItemFrogBlock(FrogGameObjects.ADV_BLAST_FURNACE).setRegistryName("advanced_blast_furnace"),
				new ItemFrogBlock(FrogGameObjects.COMBUSTION_FURNACE).setRegistryName("combustion_furnace"),
				new ItemFrogBlock(FrogGameObjects.PYROLYZER).setRegistryName("pyrolyzer"),
				new ItemFrogBlock(FrogGameObjects.CONDENSE_TOWER_CORE).setRegistryName("condense_tower_core"),
				new ItemFrogBlock(FrogGameObjects.CONDENSE_TOWER_CYLINDER).setRegistryName("condense_tower_cylinder"),
				new ItemFrogBlock(FrogGameObjects.CONDENSE_TOWER_OUTLET).setRegistryName("condense_tower_outlet"),
				new ItemMPS((BlockMPS) FrogGameObjects.MPS).setRegistryName("mobile_power_station"),
				new ItemAmmoniaCoolant("60K", 6000).setRegistryName("ammonia_coolant_60k"),
				new ItemAmmoniaCoolant("180K", 18000).setRegistryName("ammonia_coolant_180k"),
				new ItemAmmoniaCoolant("360K", 36000).setRegistryName("ammonia_coolant_360k"),
				new ItemDecayBattery().setUnlocalizedName("uranium_decay_battery").setRegistryName("uranium_decay_battery"),
				new ItemDecayBattery().setUnlocalizedName("thorium_decay_battery").setRegistryName("thorium_decay_battery"),
				new ItemDecayBattery().setUnlocalizedName("plutonium_decay_battery").setRegistryName("plutonium_decay_battery"),
				new ItemJinkela().setRegistryName("jinkela"),
				factory.create("heating_module"),
				factory.create("electrolysis_module"),
				factory.create("ammonia_catalyst_module"),
				factory.create("sulfur_trioxide_module"),
				factory.create("aluminium_ingot"),
				factory.create("magnalium_ingot"),
				factory.create("titanium_ingot"),
				factory.create("magnesium_ingot"),
				factory.create("aluminium_dust"),
				factory.create("magnalium_dust"),
				factory.create("titanium_dust"),
				factory.create("magnesium_dust"),
				factory.create("tiny_aluminium_dust"),
				factory.create("tiny_magnalium_dust"),
				factory.create("tiny_titanium_dust"),
				factory.create("tiny_magnesium_dust"),
				factory.create("aluminium_plate"),
				factory.create("magnalium_plate"),
				factory.create("titanium_plate"),
				factory.create("magnesium_plate"),
				factory.create("dense_aluminium_plate"),
				factory.create("dense_magnalium_plate"),
				factory.create("dense_titanium_plate"),
				factory.create("dense_magnesium_plate"),
				factory.create("aluminium_casing"),
				factory.create("magnalium_casing"),
				factory.create("titanium_casing"),
				factory.create("magnesium_casing"),
				factory.create("crushed_carnallite_ore"),
				factory.create("crushed_dewalquite_ore"),
				factory.create("crushed_fluorapatite_ore"),
				factory.create("purified_carnallite_ore"),
				factory.create("purified_dewalquite_ore"),
				factory.create("purified_fluorapatite_ore"),
				factory.create("carnallite_dust"),
				factory.create("dewalquite_dust"),
				factory.create("fluorapatite_dust"),
				factory.create("tiny_carnallite_dust"),
				factory.create("tiny_dewalquite_dust"),
				factory.create("tiny_fluorapatite_dust"),
				factory.create("ammonium_nitrate_dust"),
				factory.create("calcite_dust"),
				factory.create("calcium_silicate_dust"),
				factory.create("gypsum_dust"),
				factory.create("quicklime_dust"),
				factory.create("silica_dust"),
				factory.create("slaked_lime_dust"),
				factory.create("urea_dust"),
				factory.create("tiny_ammonium_nitrate_dust"),
				factory.create("tiny_calcite_dust"),
				factory.create("tiny_calcium_silicate_dust"),
				factory.create("tiny_gypsum_dust"),
				factory.create("tiny_quicklime_dust"),
				factory.create("tiny_silica_dust"),
				factory.create("tiny_slaked_lime_dust"),
				factory.create("tiny_urea_dust"),
				factory.create("aluminium_oxide_dust"),
				factory.create("calcium_fluoride_dust"),
				factory.create("magnesium_bromide_dust"),
				factory.create("potassium_chloride_dust"),
				factory.create("sodium_chloride_dust"),
				factory.create("titanium_oxide_dust"),
				factory.create("vanadium_oxide_dust"),
				factory.create("phosphorus"),
				factory.create("soap"),
				new ItemFlammable(18000).setUnlocalizedName("frogcraftrebirth.briquette").setRegistryName("briquette"),
				new ItemFlammable(1600).setUnlocalizedName("frogcraftrebirth.shattered_coal_coke").setRegistryName("shattered_coal_coke"),
				new ItemFlammable(200).setUnlocalizedName("frogcraftrebirth.lipid").setRegistryName("lipid"),
				new ItemPotassium().setUnlocalizedName("potassium").setRegistryName("potassium")
		);
	}

	@SubscribeEvent
	public static void onMissingBlock(RegistryEvent.MissingMappings<Block> event) {
		event.getMappings().forEach(RegistryEvent.MissingMappings.Mapping::ignore);
	}

	@SubscribeEvent
	public static void onMissingItem(RegistryEvent.MissingMappings<Item> event) {
		event.getMappings().forEach(RegistryEvent.MissingMappings.Mapping::ignore);
	}

	private static void regFluids(Fluid... fluids) {
		for (Fluid f : fluids) {
			FluidRegistry.registerFluid(f);
		}
	}

	private static void regFluids(IForgeRegistry<Block> registry, Fluid... fluids) {
		for (Fluid fluid : fluids) {
			FluidRegistry.registerFluid(fluid);
			Block b = new BlockFluidClassic(fluid, Material.WATER).setRegistryName(fluid.getName());
			registry.register(b);
			fluid.setBlock(b);
			FluidRegistry.addBucketForFluid(fluid);
		}
	}

}
