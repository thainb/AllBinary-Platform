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
package allbinary.animation;

import javax.microedition.lcdui.Image;

import allbinary.animation.image.AllBinaryImageArrayBaseRotationAnimation;
import allbinary.math.AngleFactory;
import allbinary.math.AngleInfo;

public class AllBinaryImageArrayRotationAnimation extends
        AllBinaryImageArrayBaseRotationAnimation
{
    // , 10, AngleIncrementInfo.TOTAL_ANGLE
    // , angleIncrement, AngleIncrementInfo.TOTAL_ANGLE

    private int expectedTotalFrames;

    protected AllBinaryImageArrayRotationAnimation(Object object)
            throws Exception
    {
        super(((AllBinaryImageArrayRotationAnimationInfo) object).getImageArray(),
                ((AllBinaryImageArrayRotationAnimationInfo) object).getAngleInfo());

        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().CONSTRUCTOR,
        // this, "AllBinaryImageRotationAnimation"));
        AllBinaryImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo = (AllBinaryImageArrayRotationAnimationInfo) object;

        this.init(allBinaryImageRotationAnimationInfo.getImageArray(),
                allBinaryImageRotationAnimationInfo.getAngleInfo(),
                allBinaryImageRotationAnimationInfo.getTotalAngle());
    }

    public AllBinaryImageArrayRotationAnimation(Image[] imageArray,
            AngleInfo angleInfo, int totalAngle) throws Exception
    {
        super(imageArray, angleInfo);

        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));

        this.init(imageArray, angleInfo, totalAngle);
    }
    
    public AllBinaryImageArrayRotationAnimation(Image[] imageArray)
            throws Exception
    {
        this(imageArray, AngleInfo.getInstance(10), AngleFactory.getInstance().TOTAL_ANGLE);
    }

    public AllBinaryImageArrayRotationAnimation(Image[] imageArray,
            AngleInfo angleInfo) throws Exception
    {
        this(imageArray, angleInfo, AngleFactory.getInstance().TOTAL_ANGLE);
    }

    private void init(Image[] imageArray, AngleInfo angleInfo, int totalAngle)
            throws Exception
    {

        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START,
        // this, "AllBinaryImageRotationAnimation"));

        this.expectedTotalFrames = totalAngle
                / this.angleInfo.getAngleIncrementInfo().getAngleIncrement();

        this.init(imageArray);

        if (expectedTotalFrames != this.getSize())
        {
            throw new Exception("Wrong Number of Frames");
        }
    }

    public void init(Image[] imageArray) throws Exception
    {

        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START,
        // this, "AllBinaryImageRotationAnimation"));

        this.setImageArray(imageArray);

        this.angleInfo.adjustAngle(0);
    }

    /*
    public void nextRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.next());
    }

    public void previousRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.previous());
    }

    public void setFrame(int index)
    {
        super.setFrame(index);
        this.angleInfo.adjustAngle(this.circularIndexUtil.getIndex());
    }
    
    public void setFrame(Direction direction)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "setFrame"));
        Angle angle = directionUtil.getFrameAngle(direction);
        this.adjustFrame(angle);
    }

    public void setFrame(Angle angle)
    {
        this.adjustFrame(angle);
    }

    public void adjustFrame(Angle angle)
    {
        this.adjustFrame(angle.getValue());
    }

    private final FrameUtil frameUtil = FrameUtil.getInstance();

    public void adjustFrame(short angle)
    {
        this.setFrame(frameUtil.getFrameForAngle(angle, this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
    }
    */
}
