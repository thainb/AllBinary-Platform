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
package allbinary.game.resource;

import org.allbinary.util.BasicArrayList;

import allbinary.graphics.PointFactory;
import allbinary.graphics.RelativeRelationship;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.relationship.Relative2DLayerRelationship;

public class FeaturedResourceRelativeRelationshipFactory extends FeaturedResourceFactory
{

   private static FeaturedResourceRelativeRelationshipFactory INSTANCE =
      new FeaturedResourceRelativeRelationshipFactory();

   private FeaturedResourceRelativeRelationshipFactory()
   {
   }

   public static FeaturedResourceRelativeRelationshipFactory getInstance()
   {
      return INSTANCE;
   }

   public BasicArrayList getRelativeRelationshipList(String resource, AllBinaryLayer layer)
      throws Exception
   {

      int size = getList().size();
      for (int index = 0; index < size; index++)
      {
         ResourceRelativeRelationshipFactoryInterface featureInterface = (ResourceRelativeRelationshipFactoryInterface) getList().get(index);
         if (featureInterface.isFeature())
         {
            BasicArrayList list = featureInterface.getResourceRelativeRelationshipList(resource);

            if (list != null)
            {
               return this.duplicate(list, layer);
            }
         }
      }

      throw new Exception(
         "Not available for current feature selection or Resource: " + resource);
   }

   private BasicArrayList duplicate(BasicArrayList list, AllBinaryLayer layer)
      throws Exception
   {
      BasicArrayList newList = new BasicArrayList();
      
      for (int index = 0; index < list.size(); index++)
      {
         RelativeRelationship relativeRelationship = (RelativeRelationship) list.get(index);
         newList.add(new Relative2DLayerRelationship(
            layer, PointFactory.getInstance().getInstance(relativeRelationship.getX(), relativeRelationship.getY()), null));
      }
      return newList;
   }
}
