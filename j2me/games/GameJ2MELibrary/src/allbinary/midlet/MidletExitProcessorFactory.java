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
package allbinary.midlet;

import javax.microedition.midlet.MIDlet;

import allbinary.AppletUtil;
import allbinary.J2MEUtil;
import allbinary.canvas.Processor;

/**
 *
 * @author user
 */
public class MidletExitProcessorFactory
{
    private static final MidletExitProcessorFactory instance =
            new MidletExitProcessorFactory();

    /**
     * @return the instance
     */
    public static MidletExitProcessorFactory getInstance()
    {
        return instance;
    }

    public Processor getInstance(MIDlet midlet)
    {
        if(AppletUtil.isAppletLoader(midlet) || J2MEUtil.isJ2ME())
        {
            return Processor.getInstance();
        }
        else
        {
            return new MidletExitProcessor(midlet);
        }
    }
}
