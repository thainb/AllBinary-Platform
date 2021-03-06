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
package allbinary.media.audio;

import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import java.io.InputStream;

import javax.microedition.media.Player;

public class AllBinaryMediaManager
{
   private AllBinaryMediaManager()
   {
   }

    /**
     * @return the muted
     */
    public static boolean isMuted()
    {
        return false;
    }

    /**
     * @param aMuted the muted to set
     */
    public static void setMuted(boolean aMuted)
    {

    }

    public static boolean update()
    {
        return true;
    }
    
    public static void init(SoundsFactoryInterface soundsFactoryInterface)
            throws Exception {
       LogUtil.put(LogFactory.getInstance("Start", "AllBinaryMediaManager None", "init"));
       ProgressCanvasFactory.getInstance().addPortion(50, "Media Manager");

        new Sounds(soundsFactoryInterface).init();
    }

    public static void shutdown(SoundsFactoryInterface soundsFactoryInterface)
            throws Exception {
        
        new Sounds(soundsFactoryInterface).stopAll();

        new Sounds(soundsFactoryInterface).closeAll();

        System.gc();
    }

   public static Player createPlayer(InputStream stream, String type)
   {
      LogUtil.put(LogFactory.getInstance("Start", "AllBinaryMediaPlayer", "creatPlayer(InputStream)"));
      return (Player) new NoPlayer();
   }
   
   public static Player createPlayer(String locator)
   {
      LogUtil.put(LogFactory.getInstance("Start", "AllBinaryMediaPlayer", "creatPlayer(locator)"));
      return (Player) new NoPlayer();
   }
   
   public synchronized static void playTone(int frequency, int time, int volume)
   {
      //"MIDP 1.0 does not have playTone"
   }

   public static String[] getSupportedContentTypes(String protocol)
   {
      return null;
   }
      
   public static String[] getSupportedProtocols(String content_type)
   {
      return null;
   }
}
