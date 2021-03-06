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
package allbinary.animation.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.util.CircularIndexUtil;

import allbinary.animation.IndexedAnimation;
import allbinary.graphics.Anchor;
import allbinary.logic.math.PrimitiveIntUtil;

public class AllBinaryImageArrayAnimation extends IndexedAnimation
{
    private Image[] imageArray;

    // private int totalAngle;
    private int totalFrames;

    protected CircularIndexUtil circularIndexUtil;
    
    public AllBinaryImageArrayAnimation(Image[] imageArray) throws Exception
    {
        super();

        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));

        this.setImageArray(imageArray);
    }

    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    public int getSize()
    {
        return this.totalFrames;
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public Image getImage(int index)
    {
        return imageArray[index];
    }

    public Image[] getImageArray()
    {
        return imageArray;
    }

    protected void setImageArray(Image[] imageArray)
    {
        this.imageArray = imageArray;
        this.totalFrames = imageArray.length;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.totalFrames);
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawImage(this.imageArray[this.circularIndexUtil.getIndex()], x, y, anchor);

        /*
         * for(int index = 0; index < NUMBER_OF_FRAMES; index++) {
         * graphics.drawImage(this.getImage(index), 0, index
         * this.getImage(index).getHeight(), Anchor.TOP_LEFT); }
         */
    }

}
