package com.kimlan.protectanimals.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AxolotlEntity.class)
abstract class AxolotlEntityMixin extends AnimalEntity {
    protected AxolotlEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld world, DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.DRY_OUT)) {
            return true;
        }
        return super.isInvulnerableTo(world, damageSource);
    }
}
