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
package allbinary.game.ai;

import allbinary.ai.ArtificialIntelligenceInterface;
import allbinary.game.ai.sequence.SequenceAI;
import allbinary.game.identification.GroupInterfaceCompositeInterface;
import allbinary.game.input.GameInput;
import allbinary.game.layer.identification.GroupLayerManagerListener;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;

public class NumberOnOwnGroupSequenceAI extends SequenceAI
{   
   private Integer[] numberOnSameTeam;

   public NumberOnOwnGroupSequenceAI(Integer[] numberOnSameTeam,
      ArtificialIntelligenceInterface[] artificialIntelligenceInterface,
      AllBinaryLayer ownerLayerInterface,
      GameInput gameInput)
   {
      super(artificialIntelligenceInterface, ownerLayerInterface, gameInput);
      this.numberOnSameTeam = numberOnSameTeam;
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      GroupInterfaceCompositeInterface groupInterfaceCompositeInterface =
         this.getOwnerLayerInterface();

      int index = this.getIndex();
      if (numberOnSameTeam.length > index)
      {
         int size = GroupLayerManagerListener.getInstance().getGroupSize(
                 groupInterfaceCompositeInterface.getGroupInterface());
         
         if (numberOnSameTeam[index].intValue() > size)
         {
            this.next();
         }
      }
      super.processAI(allBinaryLayerManager);
   }
}