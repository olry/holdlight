package com.example.holdlight;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HoldlightHandler {

    private static final Map<UUID, BlockPos> LAST_LIGHT = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide) return;

        UUID id = player.getUUID();
        BlockPos previous = LAST_LIGHT.get(id);

        int emission = Math.max(
            emissionOf(player.getMainHandItem()),
            emissionOf(player.getOffhandItem())
        );

        if (emission <= 0) {
            removeLight(level, previous);
            LAST_LIGHT.remove(id);
            return;
        }

        BlockPos target = BlockPos.containing(player.getEyePosition());

        if (previous != null && !previous.equals(target)) {
            removeLight(level, previous);
        }

        BlockState here = level.getBlockState(target);
        if (here.isAir() || here.is(Blocks.LIGHT)) {
            level.setBlock(target,
                Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, emission),
                3);
            LAST_LIGHT.put(id, target);
        } else {
            LAST_LIGHT.remove(id);
        }
    }

    private static int emissionOf(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem blockItem) {
            return blockItem.getBlock().defaultBlockState().getLightEmission();
        }
        return 0;
    }

    private static void removeLight(Level level, BlockPos pos) {
        if (pos == null) return;
        if (level.getBlockState(pos).is(Blocks.LIGHT)) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }
}
