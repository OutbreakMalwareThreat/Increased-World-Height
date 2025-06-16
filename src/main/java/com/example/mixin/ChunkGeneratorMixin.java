package com.example.mixin;

import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ChunkGenerator.class)
public abstract class ChunkGeneratorMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getMinimumY() {
        return -4096;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getWorldHeight() {
        return 8193;
    }
}