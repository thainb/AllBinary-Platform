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
package abcs.logic.java.exception;

import java.io.ByteArrayOutputStream;

public class ExceptionUtil
{  
   private ExceptionUtil()
   {
   }
   
   public static final Exception PRETEND_EXCEPTION = new Exception("Not Really An Exception");
   
   public static String getStackTrace(Throwable e)
   {
      ByteArrayOutputStream bs = new ByteArrayOutputStream();
      
      e.printStackTrace();
      
      if(bs.toString() != null)
      {
          return new String(bs.toString());
      }
      return "No Stack Trace";
   }
}
