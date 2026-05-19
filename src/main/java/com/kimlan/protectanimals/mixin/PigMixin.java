package com.kimlan.protectanimals.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Locale;

@Mixin(Pig.class)
abstract class PigMixin extends Entity {
    protected PigMixin(EntityType<? extends Entity> entityType, Level level) { super(entityType, level); }

    @ModifyExpressionValue(
        method = "thunderHit",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
        )
    )
    private Difficulty setPeacefulIfNamed(Difficulty original) {
        String name = this.getPlainTextName().toLowerCase(Locale.ROOT);

        if (this.hasCustomName() && !name.equals("killable")) {
            return Difficulty.PEACEFUL;
        }

        return original;
    }
}
