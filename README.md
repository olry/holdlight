# Holdlight
<img width="567" height="429" alt="image" src="https://github.com/user-attachments/assets/b6092b83-4e12-42dd-84ae-fca7be20ad5c" />

Minecraft 1.21.1 NeoForge mod. Hold a torch and the area around you lights up. Hold a lantern, jack o'lantern, glowstone, end rod, redstone torch, anything that emits light as a placed block, same deal.

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

## License

[MIT](LICENSE).
