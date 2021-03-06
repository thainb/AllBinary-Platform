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
package org.allbinary.image.opengles;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.opengles.TextureFactory;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.displayable.event.DisplayChangeEvent;

public class OpenGLESGL11ExtImage extends OpenGLESImage
{
    // private IntBuffer rectParams;
    private int a;
    private final int[] rectangle;

    public OpenGLESGL11ExtImage(Image image)
    {
        super(image);

        this.onDisplayChangeEvent(null);
        rectangle = new int[]
        { 0, this.getHeight(), this.getWidth(), -this.getHeight() };

    }
    
    public OpenGLESGL11ExtImage(GL10 gl, Image image, boolean matchColor)
    {
        super(gl, image, matchColor);

        this.onDisplayChangeEvent(null);

        rectangle = new int[]
        { 0, this.getHeight(), this.getWidth(), -this.getHeight() };
        
        this.update(gl);
    }

    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onResize"));
            
            this.a = DisplayInfoSingleton.getInstance().getLastHeight() - this.getHeight();
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "onResize", e));
        }
    }
    
    public void set(GL10 gl)
    {
        this.onDisplayChangeEvent(null);
        
        if (super.initTexture(gl))
        {
            // IntBuffer rectBB = IntBuffer.allocate(rect.length);
            // rectBB.order();
            // rectParams = rectBB;
            // rectParams.put(rect);

            //if(!this.matchColor)
            //{
                //gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                  //      GL10.GL_REPLACE);
                //((GL11) gl).glTexEnvi(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                  //      GL10.GL_REPLACE);
            //}

            TextureFactory.getInstance().load(GL10.GL_TEXTURE_2D, 0, this, 0);
            
            ((GL11) gl).glTexParameteriv(GL10.GL_TEXTURE_2D,
                    GL11Ext.GL_TEXTURE_CROP_RECT_OES, rectangle, 0);

            gl.glDisable(GL10.GL_TEXTURE_2D);

            int error = gl.glGetError();
            if (error != GL10.GL_NO_ERROR)
            {
                PreLogUtil.put("GLError: " + error, this, CommonStrings.getInstance().CONSTRUCTOR);
            }
        }
    }

    public void draw(GL10 gl, int x, int y, int z)
    {
        // gl.glPushMatrix();
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);

        ((GL11Ext) gl).glDrawTexiOES(x, a - y, z, this.getWidth(), this
                .getHeight());

        gl.glDisable(GL10.GL_TEXTURE_2D);

        // gl.glPopMatrix();
    }
}