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

import allbinary.game.configuration.GameConfigurationCentral;
import allbinary.image.ImageToRotationImageArrayUtil;
import allbinary.math.AngleFactory;

//TWB - Adjustments should be done in the resource creation and not at the animatoin level
public class PooledAllBinaryImageArrayRotationAnimationFactory implements
        AnimationInterfaceFactoryInterface
{

    // private int width;
    // private int height;

    private AllBinaryImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo;

    private int angleIncrement;

    public PooledAllBinaryImageArrayRotationAnimationFactory(Image image)
            throws Exception
    {
        // this(image, image.getWidth(), image.getHeight());
        this.init(image, image.getWidth(), image.getHeight(),
                -(image.getWidth() >> 2), -(image.getHeight() >> 2));
    }

    /*
     * public PooledAllBinaryImageArrayRotationAnimationFactory(MEImage image,
     * int width, int height) throws Exception {
     * 
     * this.init(image, width, height, -(width >> 2), -(height >> 2)); }
     */

    public PooledAllBinaryImageArrayRotationAnimationFactory(Image image,
            int dx, int dy) throws Exception
    {
        this.init(image, image.getWidth(), image.getHeight(), dx, dy);
    }

    protected PooledAllBinaryImageArrayRotationAnimationFactory(Image image,
            int width, int height, int dx, int dy) throws Exception
    {

        this.init(image, width, height, dx, dy);
    }

    private void init(Image image, int width, int height, int dx, int dy)
            throws Exception
    {

        // this.width = width;
        // this.height = height;

        int totalAngle = AngleFactory.getInstance().TOTAL_ANGLE;
        
        this.angleIncrement = totalAngle / GameConfigurationCentral.getInstance().getGameControlFidelity();

        Image[] imageArray = ImageToRotationImageArrayUtil.getInstance().generate(image,
                angleIncrement, totalAngle);

        allBinaryImageRotationAnimationInfo = new AllBinaryImageArrayRotationAnimationInfo(
                imageArray, angleIncrement, totalAngle, dx, dy);
    }

    public Animation getInstance() throws Exception
    {
        // return new AllBinarySpriteRotationAnimation(new MESprite(image,
        // width, height), dx, dy);

        // return new AllBinaryImageRotationAnimation(this.imageArray,
        // AngleInfo.getInstance(angleIncrement), totalAngle, dx, dy);

        return new AllBinaryAdjustedImageArrayRotationAnimation(
                allBinaryImageRotationAnimationInfo);
        //return (AnimationInterface) AllBinaryImageArrayRotationAnimationPool
          //      .getInstance().remove(allBinaryImageRotationAnimationInfo);
    }
}
