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
package allbinary.media.graphics.geography.map;

import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;

public class ColorFromEventUtil
{
    private static final ColorFromEventUtil instance = new ColorFromEventUtil();

    public static ColorFromEventUtil getInstance()
    {
        return instance;
    }
    
    private final BasicColor YELLOW = BasicColorFactory.getInstance().YELLOW;
    public final int COLOR_INT = YELLOW.intValue();
     
    public BasicColor getForegroundColor(AllBinaryGameCanvas gameCanvasInterface)
    {
        BasicColor color = YELLOW;

        GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) gameCanvasInterface.getLayerManager();
        
        // draw game menu over demomode - use commands and control input for
        // menu selection

        BasicGeographicMap geographicMapInterface = 
            geographicMapCompositeInterface.getGeographicMapInterface();

        if (geographicMapInterface != null)
        {
            BasicColor foregroundBasicColor = geographicMapInterface.getForegroundBasicColor();

            color = foregroundBasicColor;
        }

        return color;
    }

}
