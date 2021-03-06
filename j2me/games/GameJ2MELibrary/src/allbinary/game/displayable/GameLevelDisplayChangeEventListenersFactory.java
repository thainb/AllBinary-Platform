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
package allbinary.game.displayable;

import org.allbinary.util.BasicArrayList;

import allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import allbinary.graphics.displayable.event.DisplayChangeEventListener;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GameLevelDisplayChangeEventListenersFactory
{
    private static final GameLevelDisplayChangeEventListenersFactory instance = new GameLevelDisplayChangeEventListenersFactory();

    public static GameLevelDisplayChangeEventListenersFactory getInstance()
    {
        return instance;
    }

    private final BasicArrayList list = new BasicArrayList();

    public final void add(DisplayChangeEventListener displayChangeEventListener)
    {
        //PreLogUtil.put(CommonStrings.getInstance().START_LABEL + displayChangeEventListener, this, "add");

        this.list.add(displayChangeEventListener);
        DisplayChangeEventHandler.getInstance().addListener(displayChangeEventListener);
    }
    
    public final void clear()
    {
        BasicEventHandler displayChangeEventHandler = 
            DisplayChangeEventHandler.getInstance();
        
        DisplayChangeEventListener displayChangeEventListener;
        
        for(int index = this.list.size(); --index >= 0;)
        {
            displayChangeEventListener = (DisplayChangeEventListener) this.list.get(index);
            
            displayChangeEventHandler.removeListener(displayChangeEventListener);
        }
        
        this.list.clear();
    }
}
