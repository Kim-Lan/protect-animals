package com.kimlan.protectanimals.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.animal.fish.WaterAnimal;
import net.minecraft.world.entity.animal.AgeableWaterCreature;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.nautilus.Nautilus;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Locale;

@Mixin(Mob.class)
abstract class MobMixin extends Entity {
    public MobMixin(EntityType<?> type, Level level) { super(type, level); }

    @ModifyReturnValue(
        method = "isInvulnerableTo",
        at = @At("RETURN")
    )
    private boolean modifyIsInvulnerableTo(boolean isInvulnerableTo) {
        String name = this.getPlainTextName().toLowerCase(Locale.ROOT);

        boolean isAnimal = (Object) this instanceof Animal
            || (Object) this instanceof WaterAnimal
            || (Object) this instanceof AgeableWaterCreature
            || (Object) this instanceof Allay;

        return isAnimal && this.hasCustomName() && !name.equals("killable") && !((Object) this instanceof Enemy) ? true : isInvulnerableTo;
    }

    @Inject(
            method = "isInvulnerableTo",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectIsInvulnerableTo(ServerLevel level, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        boolean isWaterAnimal = (Object) this instanceof AgeableWaterCreature
            || (Object) this instanceof WaterAnimal
            || (Object) this instanceof Nautilus
            || (Object) this instanceof Axolotl;

        if (isWaterAnimal && (damageSource.is(DamageTypes.DRY_OUT) || damageSource.is(DamageTypes.DROWN))) {
            cir.setReturnValue(true);
        }

        if ((Object) this instanceof Bee && damageSource.is(DamageTypes.DROWN)) {
            cir.setReturnValue(true);
        }
    }
}
