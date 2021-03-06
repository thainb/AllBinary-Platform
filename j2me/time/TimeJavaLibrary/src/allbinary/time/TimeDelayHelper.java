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
package allbinary.time;

public class TimeDelayHelper
{
    private long startTime = -1;
    private int delay;

    public TimeDelayHelper(int delay)
    {
        this.setDelay(delay);
        this.setStartTime();
    }
    
    public boolean isTime()
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.startTime > this.getDelay())
        {

            this.startTime = currentTime;
            return true;
        }
        return false;
    }

    public boolean isTimeSince(int delay)
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.startTime > delay)
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }
    
    public boolean isTime(long currentTime)
    {
        if (currentTime - this.startTime > this.getDelay())
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }

    public boolean isTimeSince(int delay, long currentTime)
    {
        if (currentTime - this.startTime > this.getDelay())
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }
    
    public long getElapsed()
    {
        return System.currentTimeMillis() - this.startTime;
    }

    public long getElapsed(long currentTime)
    {
        return currentTime - this.startTime;
    }

    public boolean isElapsed(long currentTime, long time)
    {
        if (this.getElapsed(currentTime) > time)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isElapsed(long time)
    {
        if (this.getElapsed() > time)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setDelay(int delay)
    {
        this.delay = delay;
    }

    public int getDelay()
    {
        return delay;
    }
    
    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public long getStartTime() 
    {
        return startTime;
    }

    public void setStartTime()
    {
        this.startTime = System.currentTimeMillis();
    }
}
