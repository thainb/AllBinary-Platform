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
package allbinary.graphics.paint;

import javax.microedition.lcdui.Graphics;


public class StringLocationPaintable extends LocationPaintable
{
    private final String value;
    
    public StringLocationPaintable(String value)
    {
        this.value = value;
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawString(this.value, x, y, 0);
    }
}
