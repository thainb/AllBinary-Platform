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
package allbinary.graphics;

import abcs.logic.basic.string.CommonSeps;
import allbinary.math.PositionStrings;

public class GPoint 
{
   private final int x;
   private final int y;

   public GPoint(GPoint point)
   {
      this.x = point.getX();
      this.y = point.getY();
   }
   
   protected GPoint(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public String toString()
   {
       return toStringStatic(this.getX(), this.getY());
   }
   
   private static final String POINT_LABEL = "Point: ";
   
   public static String toStringStatic(int x, int y)
   {
      StringBuilder stringBuffer = new StringBuilder();

      PositionStrings positionStrings = 
          PositionStrings.getInstance();

      stringBuffer.append(POINT_LABEL);
      stringBuffer.append(positionStrings.X_LABEL);
      stringBuffer.append(x);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(positionStrings.Y_LABEL);
      stringBuffer.append(y);

      return stringBuffer.toString();
   }
}
