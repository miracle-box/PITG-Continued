package tv.mongotheelder.pitg.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import tv.mongotheelder.pitg.setup.Registration;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn.getPackOutput());
    }

    protected void glassPaneRecipe(Consumer<FinishedRecipe> consumer, Block result, Block ingredient, String group) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(ingredient, 1)
                .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.GLASS))
                .group(group)
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ingredient)
                .requires(result, 1)
                .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.GLASS))
                .group(group)
                .save(consumer, ForgeRegistries.BLOCKS.getKey(ingredient) + "_from_edge_pane");
    }

    protected void dualGlassPaneRecipe(Consumer<FinishedRecipe> consumer, Block result, Block ingredient, String group) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(ingredient, 2)
                .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.GLASS))
                .group(group)
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ingredient, 2)
                .requires(result, 1)
                .unlockedBy("has_glass", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.GLASS))
                .group(group)
                .save(consumer, ForgeRegistries.BLOCKS.getKey(ingredient) + "_from_dual_pane");
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        glassPaneRecipe(consumer, Registration.GLASS_PANE.get(), Blocks.GLASS_PANE, "glass_pane");
        glassPaneRecipe(consumer, Registration.RED_STAINED_GLASS_PANE.get(), Blocks.RED_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.WHITE_STAINED_GLASS_PANE.get(), Blocks.WHITE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.BLUE_STAINED_GLASS_PANE.get(), Blocks.BLUE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.ORANGE_STAINED_GLASS_PANE.get(), Blocks.ORANGE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.MAGENTA_STAINED_GLASS_PANE.get(), Blocks.MAGENTA_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.YELLOW_STAINED_GLASS_PANE.get(), Blocks.YELLOW_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.LIME_STAINED_GLASS_PANE.get(), Blocks.LIME_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.PINK_STAINED_GLASS_PANE.get(), Blocks.PINK_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.GRAY_STAINED_GLASS_PANE.get(), Blocks.GRAY_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.CYAN_STAINED_GLASS_PANE.get(), Blocks.CYAN_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.PURPLE_STAINED_GLASS_PANE.get(), Blocks.PURPLE_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.BROWN_STAINED_GLASS_PANE.get(), Blocks.BROWN_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.GREEN_STAINED_GLASS_PANE.get(), Blocks.GREEN_STAINED_GLASS_PANE, "stained_glass_pane");
        glassPaneRecipe(consumer, Registration.BLACK_STAINED_GLASS_PANE.get(), Blocks.BLACK_STAINED_GLASS_PANE, "stained_glass_pane");

        dualGlassPaneRecipe(consumer, Registration.DUAL_GLASS_PANE.get(), Registration.GLASS_PANE.get(), "glass_pane");
        dualGlassPaneRecipe(consumer, Registration.RED_STAINED_DUAL_GLASS_PANE.get(), Registration.RED_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.WHITE_STAINED_DUAL_GLASS_PANE.get(), Registration.WHITE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.BLUE_STAINED_DUAL_GLASS_PANE.get(), Registration.BLUE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.ORANGE_STAINED_DUAL_GLASS_PANE.get(), Registration.ORANGE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.MAGENTA_STAINED_DUAL_GLASS_PANE.get(), Registration.MAGENTA_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.LIGHT_BLUE_STAINED_DUAL_GLASS_PANE.get(), Registration.LIGHT_BLUE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.YELLOW_STAINED_DUAL_GLASS_PANE.get(), Registration.YELLOW_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.LIME_STAINED_DUAL_GLASS_PANE.get(), Registration.LIME_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.PINK_STAINED_DUAL_GLASS_PANE.get(), Registration.PINK_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.GRAY_STAINED_DUAL_GLASS_PANE.get(), Registration.GRAY_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.LIGHT_GRAY_STAINED_DUAL_GLASS_PANE.get(), Registration.LIGHT_GRAY_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.CYAN_STAINED_DUAL_GLASS_PANE.get(), Registration.CYAN_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.PURPLE_STAINED_DUAL_GLASS_PANE.get(), Registration.PURPLE_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.BROWN_STAINED_DUAL_GLASS_PANE.get(), Registration.BROWN_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.GREEN_STAINED_DUAL_GLASS_PANE.get(), Registration.GREEN_STAINED_GLASS_PANE.get(), "stained_glass_pane");
        dualGlassPaneRecipe(consumer, Registration.BLACK_STAINED_DUAL_GLASS_PANE.get(), Registration.BLACK_STAINED_GLASS_PANE.get(), "stained_glass_pane");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, Registration.GLAZING_TOOL_ITEM.get())
                .requires(Tags.Items.INGOTS_IRON)
                .requires(Tags.Items.DYES_GREEN)
                .requires(Tags.Items.GLASS_PANES)
                .group("tools")
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.GLASS))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.GREEN_DYE_ITEM.get())
                .requires(Tags.Items.DYES_BLUE)
                .requires(Tags.Items.DYES_YELLOW)
                .group("green_dye")
                .unlockedBy("has_yellow_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_DYE))
                .unlockedBy("has_blue_dye", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLUE_DYE))
                .save(consumer);
    }
}
