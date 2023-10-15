## Version 1.0.0

Nobody was waiting for it, but it happened! We, TorayLife and llama_orp, are finally releasing
the first release version of the MappetExtras addon, which expands and complements
the Mappet mod.

In this version you will find many improvements to the in-game documentation,
API for working with Mappet triggers, conditions and blocks, methods for working
with the client, expanded API for entities, and a new tool for cloning NPCs!

### Full list of changes:

* New features:
    * Added NPC Cloner, which allows copying an existing NPC to a special tab. When Shift+RMB turns into NPC Soulstone.
    * Added NPC Soulstone, which allows copying an NPC into an item, and subsequently spawning its copy in the world.
      When Shift+RMB it turns into NPC Cloner.
    * NPC patrol points are now displayed in the debug menu (F3).
    * Automatic check for version relevance added. Chat will have message to update if you have an old version (Can be
      disabled in config).
    * Config added for the mod by pressing key 0 (where all McHorse mod configs are).
* Documentation:
    * Documentation window expanded so method names fit.
    * Ability to navigate via class and method names added if they are present in text and docs.
    * Several inconspicuous fixes made for everything to work properly.
* API:
    * API added for working with files.
        * `getMinecraftDir`, `getWorldDir` methods added for `IScriptFactory`.
        * `IScriptFile` interface and methods added for working with it.
    * API added for working with Mappet triggers, conditions, and blocks.
        * Interfaces added for working with blocks:
            * `IScriptConditionModelTileEntity`, `IScriptConditionModel`
            * `IScriptEmitterTileEntity`
            * `IScriptModelTileEntity`
            * `IScriptTriggerTileEntity`
        * Interfaces added for working with triggers:
            * `IScriptTriggerFactory`
            * `IScriptTrigger`
        * Interfaces added for working with conditions:
            * `IScriptConditionFactory`
            * `IScriptCondition`
            * `IScriptChecker`
    * API added for getting and setting client data:
        * `getPerspective`, `setPerspective`, `getClipboard`, `setClipboard`, `getMousePosition`, `setMousePosition`, `getSetting`, `setSetting`, `getResolution`
          methods added for `IScriptPlayer`.
    * Many methods added for various interfaces:
        * `IScriptEntity`:
            * `getAge`
            * `setGlowing`
            * `isGlowing`
            * `isSpectated`
            * `rotateTo`
            * `jump`
            * `isChild`
            * `isDead`
            * `isSilent`
            * `setSilent`
            * `isAttackable`
            * `isAlive`
            * `isUndead`
            * `getFallDistance`
            * `getEntityId`
            * `setEntityId`
            * `getAIMoveSpeed`
            * `setAIMoveSpeed`
            * `setNoClip`
            * `getNoClip`
        * `IScriptFactory`:
            * `getMappetWorld`
            * `getMappetServer`
            * `getMappetTileEntity`
            * `getMappetInventory`
            * `getClassName`
        * `IScriptPlayer`:
            * `setSpectating`
            * `getLanguage`
            * `isSleeping`
            * `getPing`
            * `getRespawnInvulnerability`
            * `loadResourcePack`
            * `getServer`
        * `IScriptServer`:
            * `isSinglePlayer`
            * `isDedicatedServer`
            * `getOppedPlayerNames`
        * `IScriptVector`:
            * `dotProduct`
            * `crossProduct`
            * `toAngle`
            * `rotate`
            * `interpolation`
            * `vectorMultiply`
            * `divide`
            * `copy`
            * `equals`
            * `distance`
        * `IScriptWorld`:
            * `getBiome`
        * `IScriptNpc`:
            * `addPatrolPoint`
            * `getPatrolPoint`
            * `setPatrolPoint`
            * `getPatrolTrigger`
            * `getInitializationTrigger`
            * `setInitializationTrigger`
            * `getInteractionTrigger`
            * `setInteractionTrigger`
            * `getDamagedTrigger`
            * `setDamagedTrigger`
            * `getDeathTrigger`
            * `setDeathTrigger`
            * `getTickTrigger`
            * `setTickTrigger`
            * `getTargetTrigger`
            * `setTargetTrigger`
            * `getCollisionTrigger`
            * `setCollisionTrigger`
            * `getRespawnTrigger`
            * `setRespawnTrigger`
    * `IScriptMath` interface added containing math related methods.
* UI:
    * Many new icons added for `UIIconComponent`.