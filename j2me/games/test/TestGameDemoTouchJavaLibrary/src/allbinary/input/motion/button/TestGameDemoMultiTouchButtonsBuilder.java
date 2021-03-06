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
package allbinary.input.motion.button;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.graphics.CellPositionFactory;

public class TestGameDemoMultiTouchButtonsBuilder 
extends BaseTouchInput
{
    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));

            final BasicArrayList list = new BasicArrayList();

            TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

            BasicTouchButtonCellPositionFactory basicTouchButtonCellPositionFactory =
                new BasicTouchButtonCellPositionFactory();

            FeaturedAnimationInterfaceFactoryInterfaceFactory
            featuredAnimationInterfaceFactoryInterfaceFactory =
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();
            
            CommonButtons commonButtons = CommonButtons.getInstance();

            BasicTouchInputFactory basicTouchInputFactory = BasicTouchInputFactory.getInstance();
            
            TouchButton UP = new TouchButton(basicTouchInputFactory.UP,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonUpResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON, 
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton DOWN = new TouchButton(basicTouchInputFactory.DOWN,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonDownResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.BOTTOM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );
            
            TouchButton LEFT = new TouchButton(basicTouchInputFactory.LEFT,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonTurnLeftResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.BOTTOM_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton RIGHT = new TouchButton(basicTouchInputFactory.RIGHT,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonTurnRightResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.BOTTOM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton LEFT_STRAFE = new TouchButton(basicTouchInputFactory.SPECIAL_BUTTON_FOUR,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonStrafeLeftResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton RIGHT_STRAFE = new TouchButton(basicTouchInputFactory.SPECIAL_BUTTON_THREE,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonStrafeRightResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_BOTTOM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );
            
            list.add(UP);
            list.add(DOWN);
            list.add(LEFT);
            list.add(RIGHT);
            list.add(LEFT_STRAFE);
            list.add(RIGHT_STRAFE);
            
            if(basicTouchButtonCellPositionFactory.THIRD_FROM_BOTTOM_RIGHT != 
                CellPositionFactory.getInstance().NONE)
            {
                TouchButton WEAPON = new TouchButton(basicTouchInputFactory.SPECIAL_BUTTON_FIVE,
                        featuredAnimationInterfaceFactoryInterfaceFactory.get(
                                TouchButtonGenericActionResource.RESOURCE).getInstance(),
                        commonButtons.NORMAL_BUTTON,
                        basicTouchButtonCellPositionFactory.THIRD_FROM_BOTTOM_RIGHT,
                        touchButtonLocationHelper.getColumnsRemainderHalf(), 
                        touchButtonLocationHelper.getRowsRemainderHalf()
                        );

                list.add(WEAPON);

            }

            TouchButton ZOOM_IN = new TouchButton(
                    basicTouchInputFactory.SPECIAL_BUTTON_ONE,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonGenericActionResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.TOP_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton ZOOM_OUT = new TouchButton(
                    basicTouchInputFactory.SPECIAL_BUTTON_TWO,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonGenericActionResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.TOP_SECOND_FROM_RIGHT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton ROTATE_X = new TouchButton(
                    basicTouchInputFactory.SPECIAL_BUTTON_SIX,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonGenericActionResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.TOP_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton ROTATE_Y = new TouchButton(
                    basicTouchInputFactory.SPECIAL_BUTTON_SEVEN_TESTING_ONLY,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonGenericActionResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.TOP_SECOND_FROM_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            TouchButton ROTATE_Z = new TouchButton(
                    basicTouchInputFactory.SPECIAL_BUTTON_EIGHT_TESTING_ONLY,
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(
                            TouchButtonGenericActionResource.RESOURCE).getInstance(),
                    commonButtons.NORMAL_BUTTON,
                    basicTouchButtonCellPositionFactory.SECOND_FROM_TOP_LEFT,
                    touchButtonLocationHelper.getColumnsRemainderHalf(), 
                    touchButtonLocationHelper.getRowsRemainderHalf()
                    );

            list.add(ZOOM_IN);
            list.add(ZOOM_OUT);
            
            list.add(ROTATE_X);
            list.add(ROTATE_Y);
            list.add(ROTATE_Z);
            
            return list;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_LIST, e));
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
