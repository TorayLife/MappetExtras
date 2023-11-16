package toraylife.mappetextras.modules.main.triggers;

import mchorse.mappet.api.triggers.Trigger;

public interface TriggerAccessor {

    Trigger getPlayerTick();

    Trigger getPlayerWalking();

    Trigger getPlayerOpenGui();

    Trigger getEntityJumping();

    Trigger getEntityFalling();
}
