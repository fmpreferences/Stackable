package com.lonkachu.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.StackableMod;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Clearable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Inventory.class)
public interface InventoryStackMixin extends Clearable {




    /**
     * @author Lonk
     *
     * This needs to change the constant of 99 into the variable we want to replace as the maximum stack size,
     * technically, this might be best described as just 2.147B, but I'm unsure if this makes a significant distance
     * This rewrite is to stop us from requiring overwrites and inject and early returns that are ultimately bad for
     * mod compatibility.
     */
    @ModifyConstant(
            method = "getMaxCountPerStack",
            constant = @Constant(intValue = 99)
    )
    default int getMaxCountPerStack(int constant) {
        return StackableMod.getMaxStackCount();
    }


}
