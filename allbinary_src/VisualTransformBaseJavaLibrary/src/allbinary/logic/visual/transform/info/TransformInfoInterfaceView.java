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
package allbinary.logic.visual.transform.info;

import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TransformInfoInterfaceView implements DomNodeInterface
{
   private TransformInfoInterface transformInfoInterface;

   public TransformInfoInterfaceView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      this.transformInfoInterface = transformInfoInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      return ModDomHelper.createNameValueNodes(
         document, TransformInfoData.getInstance().NAME, transformInfoInterface.toHashMap());
   }
}
