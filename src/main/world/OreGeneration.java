package com.github.atheera.swordsoftheend.world;

import com.github.atheera.swordsoftheend.inits.BlockInit;
import com.github.atheera.swordsoftheend.utils.config.OregenConfig;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {

    public static final RuleTest END_STONE = new BlockMatchTest(Blocks.END_STONE);

    public static void addFeaturesToBiomes(final BiomeLoadingEvent event) {

        // Only generates in The End
        if(event.getCategory().equals(Biome.BiomeCategory.THEEND)) {
            generateOre(event.getGeneration(),
                END_STONE,
                BlockInit.BLOCK_ORE_SHADITE.get().defaultBlockState(),
                OregenConfig.shadite_size.get(),
                OregenConfig.shadite_minHeight.get(),
                OregenConfig.shadite_maxHeight.get(),
                OregenConfig.shadite_count.get(),
                OregenConfig.shadite_spawn.get());
        }

        // Only generates in The Nether
        if(event.getCategory().equals(Biome.BiomeCategory.NETHER)) {

        }

        // Only generates in The Overworld
        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            generateOre(event.getGeneration(),
                OreConfiguration.Predicates.NATURAL_STONE,
                BlockInit.BLOCK_ORE_LUMIN.get().defaultBlockState(),
                OregenConfig.lumin_size.get(),
                OregenConfig.lumin_minHeight.get(),
                OregenConfig.lumin_maxHeight.get(),
                OregenConfig.lumin_count.get(),
                OregenConfig.lumin_spawn.get());
            generateOre(event.getGeneration(),
                OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
                BlockInit.BLOCK_ORE_LUMIN.get().defaultBlockState(),
                OregenConfig.deep_lumin_size.get(),
                OregenConfig.deep_lumin_minHeight.get(),
                OregenConfig.deep_lumin_maxHeight.get(),
                OregenConfig.deep_lumin_count.get(),
                OregenConfig.deep_lumin_spawn.get());
            generateOre(event.getGeneration(),
                OreConfiguration.Predicates.NATURAL_STONE,
                BlockInit.BLOCK_ORE_GOLD.get().defaultBlockState(),
                OregenConfig.gold_size.get(),
                OregenConfig.gold_minHeight.get(),
                OregenConfig.gold_maxHeight.get(),
                OregenConfig.gold_count.get(),
                OregenConfig.lumin_spawn.get());
            generateOre(event.getGeneration(),
                OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
                BlockInit.BLOCK_ORE_GOLD.get().defaultBlockState(),
                OregenConfig.deep_gold_size.get(),
                OregenConfig.deep_gold_minHeight.get(),
                OregenConfig.deep_gold_maxHeight.get(),
                OregenConfig.deep_gold_count.get(),
                OregenConfig.deep_lumin_spawn.get());
            generateOre(event.getGeneration(),
                OreConfiguration.Predicates.NATURAL_STONE,
                BlockInit.BLOCK_ORE_MAGIC.get().defaultBlockState(),
                OregenConfig.enchant_size.get(),
                OregenConfig.enchant_minHeight.get(),
                OregenConfig.enchant_maxHeight.get(),
                OregenConfig.enchant_count.get(),
                OregenConfig.enchant_spawn.get());
            generateOre(event.getGeneration(),
                OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
                BlockInit.BLOCK_ORE_DEEP_MAGIC.get().defaultBlockState(),
                OregenConfig.deep_enchant_size.get(),
                OregenConfig.deep_enchant_minHeight.get(),
                OregenConfig.deep_enchant_maxHeight.get(),
                OregenConfig.deep_enchant_count.get(),
                OregenConfig.deep_enchant_spawn.get());
            generateOre(event.getGeneration(),
                    OreConfiguration.Predicates.NATURAL_STONE,
                    BlockInit.BLOCK_ORE_RUBY.get().defaultBlockState(),
                    4, 10, 30, 2, true);
            generateOre(event.getGeneration(),
                    OreConfiguration.Predicates.NATURAL_STONE,
                    BlockInit.BLOCK_ORE_DEEP_RUBY.get().defaultBlockState(),
                    4, 10, 30, 2, true);
            generateOre(event.getGeneration(),
                    OreConfiguration.Predicates.NATURAL_STONE,
                    BlockInit.BLOCK_ORE_SAPPHIRE.get().defaultBlockState(),
                    4, 10, 30, 2, true);
            generateOre(event.getGeneration(),
                    OreConfiguration.Predicates.NATURAL_STONE,
                    BlockInit.BLOCK_ORE_DEEP_SAPPHIRE.get().defaultBlockState(),
                    4, 10, 30, 2, true);
        }



    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest blockType, BlockState state, int veinSize, int minHeight, int maxHeight, int count, boolean enable) {
        if(enable) {
            settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreConfiguration(blockType, state, veinSize))
                     .rangeUniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight))
                     .squared().count(count));
        }
    }



}