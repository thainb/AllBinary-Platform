/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.game.layer.hud.basic.life;

import javax.microedition.lcdui.Graphics;

import allbinary.animation.Animation;
import allbinary.game.graphics.hud.BasicHud;
import allbinary.game.life.Life;
import allbinary.graphics.displayable.event.DisplayChangeEvent;
import allbinary.graphics.paint.PaintableInterface;

public class LivesHudWidget extends BasicHud
    implements PaintableInterface
{
    private final Life lifeInterface;
    
    private int[] xArray;
    private final Animation animationInterface;

    /*
    public LivesGraphic(AnimationInterface animationInterface, int lives, int maxlives,
    int location, int direction) throws Exception {

    this(animationInterface, lives, maxlives, location, direction, BasicColor.GREEN);

    }
     */
    //, BasicColor basicColor
    public LivesHudWidget(Animation animationInterface, Life lifeInterface,
        int location, int direction) throws Exception
    {
        super(location, direction, 12, lifeInterface.getMaxlives() * 10, 2);

        this.lifeInterface = lifeInterface;
        
        this.xArray = new int[this.getLifeInterface().getMaxlives()];
        this.update();

        this.animationInterface = animationInterface;
    }

    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        super.onDisplayChangeEvent(displayChangeEvent);
        this.update();
    }
    
    private final void update()
    {
        if(this.xArray != null)
        {
            int maxLives = this.getLifeInterface().getMaxlives();
            for (int index = 0; index < maxLives; index++)
            {
                this.xArray[index] = this.getX() + (index * 10);
            }
        }
    }

    public void setX(int x)
    {
        super.setX(x);
        this.update();
    }

    public void paint(Graphics graphics)
    {
        for (short index = this.getLifeInterface().get(); --index >= 0;)
        {
            this.animationInterface.paint(graphics, xArray[index], this.getY());
        }
    }

    public void paintThreed(Graphics graphics)
    {
    }
    
    public Life getLifeInterface()
    {
        return lifeInterface;
    }
}
