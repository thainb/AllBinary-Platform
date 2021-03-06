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
package allbinary.game.ai.scroller;

import javax.microedition.lcdui.Canvas;

import allbinary.game.ai.BasicAI;
import allbinary.game.input.GameInput;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.time.TimeDelayHelper;

public class TimedFireAI extends BasicAI
{
    protected final TimeDelayHelper maxFireDelayTimeHelper;
    
    public TimedFireAI(int delay, 
            AllBinaryLayer ownerLayerInterface,
            GameInput gameInput)
        throws Exception
    {
        super(ownerLayerInterface, gameInput);
        
        this.maxFireDelayTimeHelper = new TimeDelayHelper(delay);
    }
    
    public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {

        TimeFiredInterface timeFiredInterface = 
            (TimeFiredInterface) this.getOwnerLayerInterface();
        
        if (this.maxFireDelayTimeHelper.isTime() &&
                //Fire only if owner has not fired from a different behavoir/AI
                timeFiredInterface.getLastFireTime() + this.maxFireDelayTimeHelper.getDelay() < this.maxFireDelayTimeHelper.getStartTime())
        {
            super.processAI(Canvas.KEY_NUM1);
        }
    }
}
