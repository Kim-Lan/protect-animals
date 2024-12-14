package com.kimlan.protectanimals.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.WaterAnimalEntity;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterAnimalEntity.class)
abstract class WaterAnimalEntityMixin extends LivingEntity {
    public WaterAnimalEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld world, DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.DROWN) ||  (this.hasCustomName() && !(this instanceof Monster))) {
            return true;
        }
        return super.isInvulnerableTo(world, damageSource);
    }
}
