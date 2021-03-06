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
package org.allbinary.physics.movement;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.math.BasicDecimal;
import allbinary.logic.math.vector.AxisMathVectorUtil;
import allbinary.math.AngleFactory;

/**
 * 
 * @author user
 */
public class BasicConstantVelocityMovement 
extends Movement 
implements VelocityInterfaceCompositeInterface
{
    // private VelocityProperties velocityProperties;
    private BasicVelocityProperties velocityProperties;

    private BasicDecimal speedBasicDecimal;

    public BasicConstantVelocityMovement()
    {
        this.setSpeedBasicDecimal(ZERO_BIGDECIMAL);
        this.velocityProperties = new BasicVelocityProperties();
        // new VelocityProperties(
        // (int)this.getSpeedBasicDecimal().getUnscaled(),
        // (int)this.getSpeedBasicDecimal().getUnscaled());
    }

    public void init(short angle, BasicDecimal speedBasicDecimal)
    {
        this.speedBasicDecimal = speedBasicDecimal;
        this.velocityProperties.setVelocity(speedBasicDecimal, AngleFactory.getInstance().getInstance(angle));
    }

    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    public void moveOutsideRadius(AllBinaryLayer layer, int radius, short angle)
    {
        int scaleFactorValue = this.speedBasicDecimal.getScaledFactorValue();
        
        int xVector = (int) (axisMathVectorUtil.calculateX(radius, angle) / scaleFactorValue);
        int yVector = (int) (axisMathVectorUtil.calculateY(radius, angle) / scaleFactorValue);

        layer.move(xVector, yVector);
    }

    public void process(AllBinaryGameLayer layer) throws Exception
    {
        layer.move(this.velocityProperties.getVelocityXBasicDecimal().getScaled(),
                this.velocityProperties.getVelocityYBasicDecimal().getScaled());
    }

    public void stop()
    {
        this.velocityProperties.zero();
    }

    public BasicVelocityProperties getVelocityProperties()
    {
        return velocityProperties;
    }

    public void setVelocityProperties(BasicVelocityProperties velocityProperties)
    {
        this.velocityProperties = velocityProperties;
    }

    protected void setSpeedBasicDecimal(BasicDecimal speedBasicDecimal)
    {
        this.speedBasicDecimal = speedBasicDecimal;
    }

    protected BasicDecimal getSpeedBasicDecimal()
    {
        return speedBasicDecimal;
    }

}
