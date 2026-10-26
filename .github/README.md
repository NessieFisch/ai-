# PvP Client (Fabric 26.2)

Visual / QoL Minecraft client with:

- Right Shift ClickGUI
- Module system (NoHurtCam, LowFire, SmallTotem, ArmorHud, etc.)
- Top bar (FPS, mode, time, speed)
- Music widget (ready for Spotify / YouTube Music)

## Requirements

- Minecraft 26.2
- Fabric Loader ≥ 0.19.3
- Java 25
- Fabric API

## How to build

```bash
./gradlew build
```

The jar will be in `build/libs/`.

## Controls

- **Right Shift** → Open / close the ClickGUI

## Current modules

### Visuals
- NoHurtCam
- LowFire
- SmallTotem
- NoDeathAnimation

### HUD
- ArmorHud
- EffectTimer

### Misc
- FullBright

## Next steps

1. Add Mixins for NoHurtCam, LowFire, SmallTotem (real visual effects)
2. Connect `NowPlaying.update(...)` to System Media Session (Windows SMTC / Linux playerctl) or Spotify Web API
3. Add config saving (JSON)
4. Skybox changer

## Notes

This is a **client-side visual / QoL** mod.  
It does **not** include combat cheats (aim assist, auto crystal, reach, etc.).
