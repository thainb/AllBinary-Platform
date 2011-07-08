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
package taghelpers;

import abcs.logic.communication.log.LogFactory;
import javax.servlet.jsp.PageContext;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.logic.communication.log.LogUtil;

import allbinary.logic.control.validate.ValidationComponentInterface;
import java.util.HashMap;

public class ValidationViewHelper extends ViewHelper implements ValidationComponentInterface
{
   private ValidationComponentInterface viewInterface;
   
   public ValidationViewHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      super(hashMap, pageContext);      
      
      viewInterface = (ValidationComponentInterface) this.getViewObject();
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
      {
         LogUtil.put(LogFactory.getInstance("Constructed",this,"Constructor()"));
      }      
   }
         
   public Boolean isValid()
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
         {
            LogUtil.put(LogFactory.getInstance("Started",this,"isValid()"));
         }      
         
         return viewInterface.isValid();
      }
      catch(Exception e)
      {
         String error = "Failed to check valid value: ";
         //if(this.getTransformInfoFactoryInterface()!=null) error += this.getTransformInfoFactoryInterface().getName();
         //else 
            error += "Unknown";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }   

   public String validationInfo()
   {
      try
      {
         return viewInterface.validationInfo();
      }
      catch(Exception e)
      {
         String error = "Failed to retrieve validation info for: ";
         //if(this.getTransformInfoFactoryInterface()!=null) 
           // error += this.getTransformInfoFactoryInterface().getName();
         //else 
            error += "Unknown";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"validationInfo()",e));
         }
         return "Unknown Validation Error";
      }
   }
   
   public Document toValidationInfoDoc()
   {
      try
      {
         return viewInterface.toValidationInfoDoc();
      }
      catch(Exception e)
      {
         String error = "Failed to get validation info doc for: ";
         //if(this.getTransformInfoFactoryInterface()!=null) 
           // error += this.getTransformInfoFactoryInterface().getName();
         //else 
         error += "Unknown";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"toValidationInfoDoc()",e));
         }
         return null;
      }
   }
   
   public Node toValidationInfoNode(Document document)
   {
      try
      {
         return viewInterface.toValidationInfoNode(document);
      }
      catch(Exception e)
      {
         String error = "Failed to get validation node for: ";
         //if(this.getTransformInfoFactoryInterface()!=null) 
           // error += this.getTransformInfoFactoryInterface().getName();
         //else 
            error += "Unknown";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"toValidationInfoNode()",e));
         }
         return null;
      }
   }
}
