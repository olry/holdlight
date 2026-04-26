# Holdlight (NeoForge 1.21.1)

When a player holds a glowing block in either hand (torch, lantern, jack-o'-lantern, glowstone, sea lantern, shroomlight, end rod, redstone torch, etc.) the area around them lights up at that block's natural emission level. The light follows the player and disappears when they swap to a non-glowing item.

No new items, no recipes. Pure behavior mod.

## Build

Requires JDK 21.

```
./gradlew build        # produces build/libs/holdlight-1.21.1-1.0.0.jar
./gradlew runClient    # launches a dev client with the mod loaded
```

First run downloads NeoForge MDK + Parchment mappings, which takes a few minutes.

## How it works

`HoldlightHandler` runs on `PlayerTickEvent.Post` server-side. Each tick it picks the higher light-emission level between main hand and offhand (using the held item's block emission, so any modded glowing block works automatically), places `Blocks.LIGHT` at the player's eye position with that level, and clears last tick's light if the player moved or stopped holding a glowing item. Nothing is persisted; the world only ever contains the current tick's light blocks.

`minecraft:light` is uninteractable in survival, so the placed lights can't be mined or griefed.
