## Версия 1.3.1

Новые исправления.

В этой версии были исправлены коллбэки, баг с худами, краш бьютифаера (ага, опять), переименованы несколько методов.

### Полный список изменений:

* Исправления:
    * Был починен setSetting (не опять а снова)
    * Исправлен краш из-за бьютифаера при отсутствии нашорна (да, опять. И всё-таки, зачем вам скриптовый аддон без
      скриптового движка?)
    * UI коллбэки теперь работают и в layout.
    * Метод `disconnect` теперь работает и в мультиплеере.
    * Исправлен баг с худом `CROSSHAIRS` который приводил к исчезанию всего худа
* Изменения в API:
    * MinecraftHUD:
        * Переименован метод `resetAllHUDs` -> `resetAllMinecraftHUDs`
    * ScriptVector:
        * Переименован метод `toAngle` -> `toRotations`
        * добавлены методы `getAngle` и `distance`
* Прочее:
    * Был немного почищен код

## Версия 1.3.0

Обновился маппет - обновляются все! Теперь работает только с маппетом версии `0.9`!

В этой версии было выключено отображение отладочных оверлеев у блоков если игрок не находится в креативе, а также
изменена
совместимая версия маппета.

### Полный список изменений:

* Исправления:
    * Выключен рендер оверлея на блоках если игрок не в креативе.
    * Метод `isWalking` перемещён из `IScriptPlayer` в `IScriptEntity`.

## Версия 1.2.2

Хотфикс :3

В этой версии были исправлены краши сервера при запуске и обычной игры если не установлен скриптовый движок.

### Полный список изменений:

* Исправления:
    * Исправлен краш при запуске сервера.
    * Исправлен краш если не установлен Nashorn (зачем вам аддон без скриптового движка? Вы ебанутые?)

## Версия 1.2.1

Хотфикс спустя 2 недели - это новый рекорд.

В этой версии в очередной раз починили `getSetting` и `setSetting`, исправлен `MinecraftHUD`,
переименованы `ScriptCamera`,
`ScriptCameraShake`, `ScriptArmRender`, добавлены константы и `openWebLink`. Добавлены баги.

### Полный список изменений:

* API:
    *
  Переименованы `ScriptCamera`, `ScriptCameraShake`, `ScriptArmRender` -> `MinecraftCamera`, `MinecraftCameraShake`, `MinecraftArmRender`
    * Добавлены константы
    * Добавлен метод для `IScriptPlayer`:
        * `openWebLink(String url)`
    * Добавлен метод для `IScriptServer`:
        * `isServerInOnlineMode()`
* Исправления:
    * Опять починили `getSetting` и `setSetting`
    * Исправлен `MinecraftHUD`
    * Исправлен баг с моделями
    * исправлена работа методов которые взаимодействуют с НПС.
* Новые триггеры:
    * Добавлен триггер Игрок: при закрытии GUI (Не работает :D)

## Версия 1.2.0

Опа!

В этой версии было расширено скриптовое АПИ (как и в каждой), добавлены `IScriptArmRender`, `IScriptCamera`,
`IScriptCameraShake`, `IMinecraftHUD`,
исправлены `getSetting` и скриптовые векторы, добавлены коллбэки для UI компонентов.

### Полный список изменений:

* API:
    * Добавлен `IScriptArmRender`
    * Добавлен `IScriptCamera`
    * Добавлен `IScriptCameraShake`
    * Добавлен `IMinecraftHUD`
    * Добавлены методы для `IScriptEntity`:
        * `setSneaking(boolean flag)`
        * `setSprinting(boolean flag)`
        * `damage(float health, String damageType)`
    * Добавлены методы для `IScriptEvent`:
        * `stopScript()`
        * `stopScript(String script)`
    * Добавлены методы для `IScriptFactory`:
        * `createUniqueId()`
    * Добавлены методы для `IScriptPlayer`:
        * `soundUpdate()`
        * `isDrink()`
        * `isEat()`
        * `textureUpdate()`
        * `isWalking()`
        * `getFacing()`
        * `disconnect(String reason)`
    * Добавлены методы для `IScriptServer`:
        * `stopServer()`
        * `getDifficulty()`
    * Добавлены методы для `IScriptWorld`:
        * `loadChunk(int x, int z)`
        * `isChunkLoaded(int x, int z)`
        * `getLight(ScriptVector vector, boolean checkNeighbors)`
        * `getLight(int x, int y, int z, boolean checkNeighbors)`
        * `getLight(ScriptVector vector)`
        * `getLight(int x, int y, int z)`
    * Добавлены методы для `UIComponent`:
        * `callback(ScriptObjectMirror function)`
        * `callback(String action, ScriptObjectMirror function)`
    * Добавлены методы для `MappetUIContext`:
        * `handleCallbacks(IScriptEvent event)`
* Исправления:
    * Исправлена работа `IScriptVector`.
    * Исправлена работа метода `getSetting`.
* Прочее:
    * Зачем-то были добавлены текстуры для дней рождений контрибьюторов. До сих пор не понимаю нафиг оно надо, но раз
      ребята захотели - пусть будет. Тем кто использует предметы MappetExtras как игровые - соболезную.

## Версия 1.1.2

Эта версия - хотфикс багов которые нашли в 1.1.1. Исправлены косяки с документацией, локализацией, починили глобальные
триггеры (опять) и апи рендера руки.

### Полный список изменений:

* Исправления:
    * Исправлена ошибка, из-за которой в одиночной игре не работали глобальные триггеры.
    * Исправлена локализация.
    * Исправлена документация.
    * Исправлено апи рендера руки.
    * Починили методы которые работают с клиентской частью игры.

## Версия 1.1.1

Эта версия - хотфикс багов которые нашли в 1.1.0. Исправлены косяки с документацией и вылеты.

### Полный список изменений:

* Исправления:
    * Исправлена ошибка, из-за которой не запускался сервер.
    * Исправлена ошибка, из-за которой игра вылетала при входе в мультиплеер.
    * Исправлена ошибка, из-за которой создавались отдельные записи в документации, вместо того чтобы дополнить
      существующие.

## Версия 1.1.0

Первое полноценное обновление мода.

В этой версии добавлены новые глобальные триггеры, новые инструменты для работы с кодом, были исправлены некоторые
иконки для UI и добавлены новые, а также исправлены баги.

### Полный список изменений:

* Исправления:
    * `getSetting` теперь работает. По крайней мере должен.
    * Переработаны названия иконок, сдвинутые от центра иконки перемещены к центру.
    * Сделали доступным метод который возвращает `IScriptMath`.
* Новые глобальные триггеры:
    * Игрок: При тике
    * Игрок: При ходьбе
    * Игрок: При открытии GUI
    * Живое: При падении
    * Живое: При прыжке
* Новые возможности:
    * Поиск и замена по коду. Можно открыть при помощи иконки в панели или при помощи комбинации `Ctrl+F`. Имеется
      настройка внешнего вида в конфиге.
    * Бьютифаер (улучшалка кода). Применяется при нажатии на иконку в панели. Имеются настройки в конфиге.
* API:
    * Добавлен `IScriptArmRender`, который позволяет менять рендер руки: вращать, двигать, скрывать руку. В будущем
      возможности будут расширяться.
* Прочее:
    * Добавлено несколько новых иконок.
    * Проведён небольшой рефакторинг кода.

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
    * Проведён небольшой рефакторинг кода.

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