# Holdlight

Minecraft 1.21.1 NeoForge mod. Hold a torch and the area around you lights up. Hold a lantern, jack o'lantern, glowstone, end rod, redstone torch, anything that emits light as a placed block, same deal. Switch to a sword and it goes dark again.

Same idea as [LambDynamicLights](https://github.com/LambdAurora/LambDynamicLights) (Fabric) or the original [Dynamic Lights](https://www.curseforge.com/minecraft/mc-mods/dynamic-lights) (Forge), just for NeoForge.

## Install

Grab the latest jar from [Releases](https://github.com/olry/holdlight/releases/latest) and drop it into `.minecraft/mods/`. Done. No config, no recipes, no new items.

Requires NeoForge 21.1.x for Minecraft 1.21.1.

## Light levels

The light level matches what the block emits as a placed block:

- Lantern, glowstone, jack o'lantern, sea lantern, shroomlight: 15
- Torch, end rod: 14
- Soul torch, soul lantern: 10
- Redstone torch: 7
- Any modded glowing block: whatever its placed emission is

Both hands are checked. Whichever is brighter wins.

## Servers

Runs on dedicated NeoForge 1.21.1 servers. Connecting clients need NeoForge installed too, same rule as any mod.

## Build from source

Requires JDK 21.

```
./gradlew build       # produces build/libs/holdlight-1.21.1-1.0.0.jar
./gradlew runClient   # dev launcher with the mod loaded
```

First build pulls Gradle, the NeoForge MDK, and Parchment mappings. Takes a few minutes once, then it caches.

## How it works

Each server tick the mod walks every player, checks main hand and off hand for a `BlockItem` whose block has a light emission greater than zero, and places a `minecraft:light` at the player's eye position with that emission level. Last tick's light is cleared if the player moved or stopped holding a glowing item.

`minecraft:light` is the vanilla invisible light block placeable with `/setblock`. It has no collision and is uninteractable in survival, so it can't be mined or griefed. Nothing is persisted. The world only ever holds the current tick's light blocks.

## License

MIT.
