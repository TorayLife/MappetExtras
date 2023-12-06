package toraylife.mappetextras.modules.client.scripts.user;

import toraylife.mappetextras.modules.client.scripts.code.ScriptCameraShake;

public interface IScriptCamera {
    /**
     * Get the camera shake controller for this player.
     * Can be used to shake the player's camera.
     *
     * @return The ScriptCameraShake instance
     */
    public ScriptCameraShake getShake();
}
