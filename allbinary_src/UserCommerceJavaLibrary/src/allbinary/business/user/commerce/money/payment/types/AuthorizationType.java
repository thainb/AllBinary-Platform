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
package allbinary.business.user.commerce.money.payment.types;

public class AuthorizationType
{      
   private String name;
   
   public AuthorizationType(String name)
   {
      this.name = name;
   }

   public String toString()
   {
      return name;
   }
}
