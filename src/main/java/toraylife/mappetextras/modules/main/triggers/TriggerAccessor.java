package toraylife.mappetextras.modules.main.triggers;

import mchorse.mappet.api.triggers.Trigger;

public interface TriggerAccessor {

    Trigger getPlayerTick();

    Trigger getPlayerWalking();

    Trigger getPlayerOpenGui();

    Trigger getPlayerCloseGui();

    Trigger getEntityJumping();

    Trigger getEntityFalling();
    Trigger getPlayerEat();
    Trigger getPlayerDrink();

    Trigger getPlayerDimensionChange();
}
