package toraylife.mappetextras.modules.client.scripts.user;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.scripting.scripts.user.IScriptFile;

import java.io.IOException;

public interface IScriptArmRender {
    /**
     * Rotates the arm (main or off). angle - angle in degrees (positive angle = counterclockwise rotation), and x, y and z - vector around which the rotation is performed.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *       armRender.rotate(-90, 0, 1, 0); // Rotate 90 degrees clockwise around the vertical axis
     *    }
     *  }</pre>
     */
    void setRotate(double angle, double x, double y, double z);
    /**
     * Returns NBT Rotate. Keys: angle, x, y, z.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *       c.send(armRender.angle); // returns angle arm
     *       c.send(armRender.x); // returns x arm
     *       c.send(armRender.y); // returns y arm
     *       c.send(armRender.z); // returns z arm
     *    }
     *  }</pre>
     *  @return nbt armRender
     */
    NBTTagCompound getRotate();

    /**
     * Moves the arm through the coordinates
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *       armRender.setPosition(0.3, 0, 0);
     *    }
     *  }</pre>
     */
    void setPosition(double x, double y, double z);
    /**
     * Moves the arm through the coordinates
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *       armRender.setPosition(mappet.vector(0.3, 0, 0));
     *    }
     *  }</pre>
     */
    void setPosition(ScriptVector pos);
    /**
     * Return coordinates arm
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *       const position = armRender.getPosition();
     *
     *       c.send("x: "+position.x+", y:"+position.y+", z:"+position.z) // coordinates
     *    }
     *  }</pre>
     *  /**
     * @return ScriptVector pos
     */
    ScriptVector getPosition();

    /**
     * Returns whether hand rendering is enabled
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *       c.send(armRender.isRender()) // render: boolean
     *    }
     *  }</pre>
     *
     * @return render boolean
     */
    boolean isRender();
    /**
     * Enable arm rendering
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       const armRender = c.player.getArmRender(0); //0 - main, 1 - off
     *
     *       armRender.setRender(false)
     *    }
     *  }</pre>
     */
    void setRender(boolean render);

}