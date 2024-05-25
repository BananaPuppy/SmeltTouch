package com.schitzpseudonym.smelttouch.mixin;

import java.util.List;
import java.util.Optional;

import com.schitzpseudonym.smelttouch.ModRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("RETURN"))

    // Smelting Enchantment
    private static void getDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfoReturnable<List<ItemStack>> cir) {
        //Cancel the Inject if the Smelting Enchantment is not applicable
        if (EnchantmentHelper.getLevel(ModRegistry.smeltTouch, tool) == 0) { return; }

        List<ItemStack> cirReturnValue = cir.getReturnValue();

        for (int i = 0; i < cirReturnValue.size(); i++) {
            ItemStack droppedItem = cirReturnValue.get(i);
            Optional<SmeltingRecipe> recipe = world.getRecipeManager().listAllOfType(RecipeType.SMELTING).stream().filter((r -> r.getIngredients().get(0).test(droppedItem))).findFirst();

            // If there is a valid smelting recipe
            if(recipe.isPresent()) {
                //getting the ItemStack output of the smelting recipe
                ItemStack smeltedItem = recipe.get().getOutput(null); //null may need to be world.getRegistryManager()
                smeltedItem.setCount(droppedItem.getCount());
                cirReturnValue.set(i, smeltedItem);
                //Spawn recipe output XP
                int expAmount = Math.round(recipe.get().getExperience());
                ExperienceOrbEntity.spawn(world, Vec3d.ofCenter(pos), expAmount);
            }
        }
    }
}
