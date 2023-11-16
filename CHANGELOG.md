## Version 1.1.0

The first full-fledged update of the mod.

This version adds new global triggers, new tools for working with code, fixed some
UI icons and added new ones, as well as bug fixes.

### Full list of changes:

* Fixes:
    * `getSetting` now works. At least it should.
    * Reworked icon names, shifted off-center icons moved to the center.
    * Made available a method that returns `IScriptMath`.
* New global triggers:
    * Player: When ticking
    * Player: When walking
    * Player: When opening a GUI
    * Living: When falling
    * Living: When jumping
* New features:
    * Search and replace by code. Can be opened using the icon in the panel or using the `Ctrl+F` combination. There is
      customization of appearance in config.
    * Beautifier (code enhancer). Applied by clicking on an icon in the panel. There are settings in config.
* API:
    * Added `IScriptArmRender` which allows you to change the arm renderer: rotate, move, hide the arm. Features will be
      expanded in the future.
* Other:
    * Added some new icons.
    * Small code refactoring was performed.

## Version 1.0.1

Here is the first work on bugs.

This version is a hotfix of bugs found in 1.0.0. Fixed bugs with documentation, bugs related to files, added Russian
localisation.

### Full list of changes:

* Fixes:
    * Fixed bug that caused `getLanguage` and `getRespawnInvulnerabilityTicks` methods not working.
    * Fixed the size of the documentation window when opening it via a link within the documentation.
    * Fixed a bug that caused some standard Mappet methods to be overwritten in the documentation.
    * Fixed `IScriptFile`.
    * Fixed `IScriptTriggerFactory` description.
    * Fixed a bug that caused the documentation sections for triggers and conditions to be duplicated.
* Breaking changes:
    * Removed the `create` method from `IScriptFile`. Added `createFile` and `createDirectory` in its place.
    * The `sendToAll` method of `IScriptModelTileEntity` has been changed to accept a `merge` argument of
      type `boolean`, which is responsible for whether the morph will change smoothly.
* Other:
    * Added Russian localisation.
    * Small code refactoring was performed.

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