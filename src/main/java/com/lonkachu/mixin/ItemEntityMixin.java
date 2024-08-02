package com.lonkachu.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.StackableMod;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @ModifyConstant(method = "merge(Lnet/minecraft/entity/ItemEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V", constant = @Constant(intValue = 64))
    private static int merge(int val) {
        return StackableMod.getMaxStackCount();
    }

    //TODO Potentially remove this ModifyConstant, I just couldn't think of any way to remove it gracefully.
}
