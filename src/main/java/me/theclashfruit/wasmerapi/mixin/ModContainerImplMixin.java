package me.theclashfruit.wasmerapi.mixin;

import me.theclashfruit.wasmerapi.ModContainerMixinInterface;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.impl.ModContainerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModContainerImpl.class)
public class ModContainerImplMixin implements ModContainerMixinInterface {
    @Shadow @Final @Mutable
    private ModMetadata info;

    @Override
    public void setMetadata(ModMetadata metadata) {
        this.info = metadata;
    }
}
