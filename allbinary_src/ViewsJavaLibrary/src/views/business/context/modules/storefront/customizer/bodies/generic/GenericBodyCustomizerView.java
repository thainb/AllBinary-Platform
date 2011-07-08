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
package views.business.context.modules.storefront.customizer.bodies.generic;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import views.business.context.modules.storefront.HttpStoreComponentView;
import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.page.PageData;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import allbinary.logic.visual.transform.StoreTransformer;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.template.customizer.bodies.GenericBodyValidation;
import allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;

public class GenericBodyCustomizerView extends HttpStoreComponentView 
   implements DomNodeInterface
{
   protected GenericBodyValidation body;
   
   public GenericBodyCustomizerView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public void addDomNodeInterfaces() throws Exception
   {
      this.addDomNodeInterface((DomNodeInterface) this.body);
      this.addDomNodeInterface((DomNodeInterface) this);
   }      
   
   private static final String NAME = "None";
   
   public Node toXmlNode(Document document) throws Exception
   {
         String pageName = 
        	 TransformTemplateCustomizerUtil.getInstance().getPageNameHack(
            this.getTransformInfoInterface().getName(),
            this.getWeblisketSession().getStoreName());
         
         if(StringValidationUtil.getInstance().isEmpty(pageName))
         {
        	 pageName = NAME;
         }

         return ModDomHelper.createNameValueNodes(document, PageData.getInstance().NAME, pageName);
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();

         String success = DomDocumentHelper.toString(this.getDoc());

         String result =
            new StoreTransformer(this.getTransformInfoInterface()).translate(success);
         return StringEscapeUtils.unescapeHtml(result);
      }
      catch(Exception e)
      {
         String error = "Failed to view Generic Body";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
}
