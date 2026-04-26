package com.example.holdlight;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(HoldlightMod.MODID)
public class HoldlightMod {
    public static final String MODID = "holdlight";

    public HoldlightMod(IEventBus modBus) {
        NeoForge.EVENT_BUS.register(HoldlightHandler.class);
    }
}
