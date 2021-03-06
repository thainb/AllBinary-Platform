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
package org.allbinary.business.advertisement;

import allbinary.direction.Direction;
import allbinary.game.state.GameState;

public class AdConfiguration
{
    public Direction[] getValidAdSpots()
    {
        return null;
    }
    
    //Special in gamestate based processing
    public void process(GameState gameState)
    {
    }
    
    //Special Demo processing
    public void process(int state)
    {
    }

    public void setShowAds()
    {
    }
    
    public void setShowAds(boolean showAds)
    {
    }

    public boolean isShowAds()
    {
        return true;
    }
}
