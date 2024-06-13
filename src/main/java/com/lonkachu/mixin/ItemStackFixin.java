package com.lonkachu.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.StackableMod;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/*
@ItemStackFixin
Author: Lonk
This mixin class exists to unpatch a crash that occurs if a block stack is above 99, I'm not quite sure why mojang added this
tbh, it doesn't massively impact how people
 */
@Mixin(ItemStack.class)
public class ItemStackFixin {
    @ModifyConstant
            (
                    method = "method_57371", //This method is a Lambda, they aren't funda.
                    constant = @Constant(intValue = 99)

            )

    private static int getMaxCountPerStack(int constant) {
        return StackableMod.getMaxStackCount();
    }
}
