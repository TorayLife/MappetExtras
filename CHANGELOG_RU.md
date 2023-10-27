## Версия 1.0.1

А вот и первая работа над ошибками.

Эта версия - хотфикс багов которые нашли в 1.0.0. Исправлены косяки с документацией, баги связанные с файлами, добавлена
русская локализация.

### Полный список изменений:

* Исправления:
    * Исправлена ошибка, из-за которой не работали методы `getLanguage` и `getRespawnInvulnerabilityTicks`.
    * Исправлен размер окна документации при открытии её через ссылку внутри документации.
    * Исправлена ошибка, из-за которой некоторые стандартные методы маппета перезаписывались в документации.
    * Исправлена работа `IScriptFile`.
    * Исправлено описание у `IScriptTriggerFactory`.
    * Исправлена ошибка, из-за которой дублировались разделы документации для триггеров и условий.
* Ломающие изменения:
    * Удалён метод `create` у `IScriptFile`. Вместо него добавлены `createFile` и `createDirectory`.
    * Метод `sendToAll` у `IScriptModelTileEntity` изменён, теперь он принимает аргумент `merge` типа `boolean`, который
      отвечает за то будет ли морф изменяться плавно.
* Прочее:
    * Добавлена русская локализация.

## Версия 1.0.0

Никто не ждал, а это случилось! Мы, TorayLife и llama_orp наконец выпускаем
первую релизную версию аддона MappetExtras, который расширяет и дополняет
мод Mappet.

В этой версии вы найдёте множество улучшений внутриигровой документации,
API для работы с триггерами, условиями и блоками Mappet'а, методы для работы
с клиентом, расширение API для сущностей, и новый инструмент для клонирования НПС!

### Полный список изменений:

* Новые возможности:
    * Добавлен NPC Cloner, который позволяет копировать существующего НПС в специальную вкладку. При Shift+ПКМ
      превращается в NPC Soulstone.
    * Добавлен NPC Soulstone, который позволяет копировать НПС в предмет, и в последствии спавнить его копию в мире. При
      Shift+ПКМ превращается в NPC Cloner.
    * В дебаг меню (F3) добавлено отображение патрульных точек НПС.
    * Добавлена автоматическая проверка на актуальность версии. В чате будет сообщение, что нужно обновиться, если у вас
      старая версия (Можно выключить в конфиге).
    * Добавлен конфиг для мода по нажатию клавиши 0 (там где все конфиги от модов McHorse)
* Документация:
    * Окно документации расширено, чтобы названия методов помещались.
    * В документацию добавлена возможность перехода по названиям классов и методов, если они есть в тексте и
      документации.
    * Несколько незаметных исправлений, нужных чтобы всё работало как надо.
* API:
    * Добавлено API для работы с файлами.
        * Для `IScriptFactory` добавлены методы `getMinecraftDir`, `getWorldDir`
        * Добавлен интерфейс `IScriptFile` и методы для работы с ним.
    * Добавлено API для работы с триггерами, условиями, и блоками Mappet'а.
        * Добавлены интерфейсы для работы с блоками:
            * `IScriptConditionModelTileEntity`, `IScriptConditionModel`
            * `IScriptEmitterTileEntity`
            * `IScriptModelTileEntity`
            * `IScriptTriggerTileEntity`
        * Добавлены интерфейсы для работы с триггерами:
            * `IScriptTriggerFactory`
            * `IScriptTrigger`
        * Добавлены интерфейсы для работы с условиями:
            * `IScriptConditionFactory`
            * `IScriptCondition`
            * `IScriptChecker`
    * Добавлено API для получения и установки клиентских данных:
        * Для `IScriptPlayer` добавлены
          методы `getPerspective`, `setPerspective`, `getClipboard`, `setClipboard`, `getMousePosition`, `setMousePosition`, `getSetting`, `setSetting`, `getResolution`
    * Добавлено ОЧЕНЬ много методов для самых разных интерфейсов:
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
    * Добавлен интерфейс `IScriptMath`, который содержит методы связанные с математикой.
* UI:
    * Добавлено ОЧЕНЬ много новых иконок для `UIIconComponent`