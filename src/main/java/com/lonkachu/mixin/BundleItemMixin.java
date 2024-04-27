package com.lonkachu.mixin;

import com.lonkachu.StackableMod;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.DataComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@BundleItemMixinFixin
/*
This class handles a major crash issue that results from bundles assuming the stack size is 64 rather than 127 or whatever custom value has been applied, this class makes some siginficant changes to how bundles work and can be a considerable problem if your mod also utilizes the bundleitem class.


 */

import java.util.List;
import java.util.Stack;

@Mixin(BundleItem.class)
public class BundleItemMixin {

    @Mutable
    @Shadow @Final public static int field_51352; //MAX_STORAGE
//    @Shadow
//    private static int getBundleOccupancy(ItemStack stack) {
//
//        return 0;
//    }


    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void inject(CallbackInfo ci) {
        field_51352 = StackableMod.getMaxStackCount();
    }

    //@appendTooltip
    //We need to remove the 0/64 tooltip from the bundle and replace it with a tooltip that includes the actual stacksize we defined already
    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    private void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        BundleContentsComponent bundleContentsComponent = stack.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (bundleContentsComponent != null) {
            int i = MathHelper.multiplyFraction(bundleContentsComponent.getOccupancy(), StackableMod.getMaxStackCount());
            tooltip.add(Text.translatable("item.minecraft.bundle.fullness", i, StackableMod.getMaxStackCount()).formatted(Formatting.GRAY));
        }

        ci.cancel();
        return;
    }
    //@getItemOccupancy
    //we need to make sure that we are dividing the maxstack we defined by the stack.getMaxCount. It is crucial to ceiling the float as without performing this operation we will be subject to rounding errors that will result in overstacked items.
//    @Inject(method = "getItemOccupancy", at = @At("RETURN"), cancellable = true)
//    private static void getItemOccupancy(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
//        cir.setReturnValue(    (int)Math.ceil((float)StackableMod.MAX_STACK / stack.getMaxCount())  );
//        cir.cancel();
//    }

    //@AddtoBundle @OnstackClicked @getAmountFilled @getItemBarStep
    //we simply need to change these constants from 64 to whatever custom value we need.
//    @ModifyConstant(method = "addToBundle", constant = @Constant(intValue = 64))
//    private static int addToBundleFix(int val) {
//        return StackableMod.MAX_STACK;
//    }


//    @ModifyConstant(method = "onStackClicked", constant = @Constant(intValue = 64))
//    private int onStackClickedFix(int val) {
//        return StackableMod.MAX_STACK;
//    }
//
//    @ModifyConstant(method = "getAmountFilled", constant = @Constant(floatValue = 64.0F))
//    private static float getAmountFilledFix(float fal) {
//        return (float)StackableMod.MAX_STACK;
//    }
//
//    @ModifyConstant(method = "getItemBarStep", constant = @Constant(intValue = 64))
//    private int getItemBarStepFix(int val) {
//        return StackableMod.MAX_STACK;
//    }
}
