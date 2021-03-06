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
package allbinary.game.physics.velocity;

import allbinary.direction.Direction;
import allbinary.logic.math.BasicDecimal;
import allbinary.math.Angle;

public interface BasicVelocityInterface
{

    BasicDecimal getVelocityXBasicDecimal();

    BasicDecimal getVelocityYBasicDecimal();

    void zero();

    void setVelocityXBasicDecimal(BasicDecimal velocityXBasicDecimal);

    void setVelocityYBasicDecimal(BasicDecimal velocityYBasicDecimal);

    void setVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction);

    void addVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction);

    void setVelocity(long magnitude, Direction direction);

    void addVelocity(long magnitude, Direction direction);

    void setVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle);

    void addVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle);

    void setVelocity(long magnitude, Angle angle);

    void addVelocity(long magnitude, Angle angle);

    void addVelocity(long magnitude, short angle);
}
