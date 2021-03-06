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
package allbinary.game.midlet;

/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/19/02
 *
 *
 *Modified By         When       ?
 *
 */
/**
 *Detailed description This class is the main MIDlet for all GameMidlets
 *it sets the main canvas and starts a thread for the specified canvas.
 *
 *@author Travis Berthelot
 *Date: 11/19/02
 *
 */
import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.game.GameAdState;
import org.allbinary.graphics.ResizableListenerHandler;
import org.allbinary.graphics.ScreenListenerHandler;
import org.allbinary.input.AllBinarySensorManager;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.canvas.FullScreenUtil;
import allbinary.canvas.GameStatisticsFactory;
import allbinary.debug.DebugFactory;
import allbinary.debug.DebugInterface;
import allbinary.game.GameInfo;
import allbinary.game.GameMode;
import allbinary.game.GameTypeFactory;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.configuration.GameOptionsForm;
import allbinary.game.configuration.GameOptionsFormFactory;
import allbinary.game.configuration.InGameFeatures;
import allbinary.game.configuration.InGameOptionsForm;
import allbinary.game.configuration.InGameOptionsFormFactory;
import allbinary.game.configuration.LoadGameForm;
import allbinary.game.configuration.event.ChangedGameFeatureListener;
import allbinary.game.configuration.event.GameFeatureEventHandler;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.GameFeatureFormUtil;
import allbinary.game.configuration.feature.MainFeatureFactory;
import allbinary.game.configuration.persistance.GamePersistanceSingleton;
import allbinary.game.configuration.persistance.KeyValuePersistance;
import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.game.displayable.canvas.BasicPaintablesCanvas;
import allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import allbinary.game.displayable.canvas.GameEventHandlerUtil;
import allbinary.game.displayable.canvas.GameInputMappingCanvas;
import allbinary.game.displayable.canvas.GameInputMappingInstructionsCanvas;
import allbinary.game.displayable.canvas.MenuListener;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.layer.hud.basic.event.GameNotificationEventHandler;
import allbinary.game.paint.AboutPaintable;
import allbinary.game.paint.help.HelpPaintable;
import allbinary.game.score.HighScoreCommands;
import allbinary.game.score.HighScoreCommandsFactory;
import allbinary.game.score.displayable.HighScoreTextBox;
import allbinary.game.score.displayable.HighScoresCanvas;
import allbinary.game.state.GameState;
import allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.ColorChangeEventHandler;
import allbinary.graphics.displayable.MyCanvas;
import allbinary.graphics.displayable.command.MyCommandsFactory;
import allbinary.graphics.displayable.screen.CommandForm;
import allbinary.input.event.VirtualKeyboardEventHandler;
import allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;
import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.media.audio.AllBinaryMediaManager;
import allbinary.midlet.MidletStrings;
import allbinary.midlet.ProgressMidlet;
import allbinary.thread.ThreadUtil;
import allbinary.time.TimeDelayHelper;

public class GameMidlet extends ProgressMidlet
    implements CommandListener //, GameMidletEventListener
{
    private GameCanvasRunnableInterface allbinaryGameCanvasRunnableInterface;
    private Thread thread;
    private GameState currentGameState = GameState.NO_GAME_STATE;
    private final TimeDelayHelper gameStartTimeHelper = new TimeDelayHelper(2200);

    // Screens/Canvas
    //private CommandForm saveGameForm;
    private LoadGameForm loadGameForm;
    private final DebugInterface debugInterface;
    
    //private GameOptionsForm gameOptionsForm;

    public GameMidlet()
    {
        //LogUtil.put(LogFactory.getInstance(
          //      "GameMidlet::GameMidlet", this, CommonStrings.getInstance().CONSTRUCTOR));

        //For BB can be used for J2ME as well
        SmallIntegerSingletonFactory.getInstance().init(0x291, 6);
        //This can be used for J2ME but not BB
        //SmallIntegerSingletonFactory.init(0x101, 6);

        ProgressCanvasFactory.getInstance().init(this);

        GameFeatureEventHandler.getInstance().addListener(
                ChangedGameFeatureListener.getInstance());

        GamePersistanceSingleton.getInstance().clear();

        this.debugInterface = DebugFactory.getInstance();
        
        this.init();        
    }

    protected void init()
    {
    }

    protected void setDemo() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    protected void createGame() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    protected void mediaShutdown() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public void stopAll()
    {
    }

    protected void pauseApp()
    {
        this.pauseAppBackground(true);
        
        GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();
        
        gameAdState.getAdvertisements().stopAll();
    }
    
    protected void pauseAppBackground(boolean background)
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "pauseApp"));
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "pauseApp");
        
        if (getGameCanvasRunnableInterface() != null)
        {
            getGameCanvasRunnableInterface().pause();
            /*
            if (!getGameCanvasRunnableInterface().isPaused())
            {
                getGameCanvasRunnableInterface().pause();
            }
             */
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("<<<<<< Null", this, "pauseApp"));
        }
        
        AllBinarySensorManager.getInstance().shutdown();
    }

    protected void unPauseApp()
    {
        this.unPauseAppBackground(true);
        
        GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();
        
        gameAdState.getAdvertisements().startAll();
    }
    
    protected void unPauseAppBackground(boolean background)
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "unPauseApp"));
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "unPauseApp");
        
        AllBinarySensorManager.getInstance().init();

        GameCanvasRunnableInterface gameCanvasRunnableInterface =
            this.getGameCanvasRunnableInterface();

        if (gameCanvasRunnableInterface != null)
        {
            gameCanvasRunnableInterface.unPause();
            /*
            if (gameCanvasRunnableInterface.isPaused())
            {
                gameCanvasRunnableInterface.unPause();
            }
            */
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("<<<<<< Null", this, "pauseApp"));
        }
    }
    
    protected void destroyApp(boolean unconditional)
    {
        try
        {
            PreLogUtil.put(GameStatisticsFactory.getInstance().toString(), this, "GameMidlet::destroyApp");

            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "GameMidlet::destroyApp"));

            GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();
            
            gameAdState.getAdvertisements().stopAll();
            
            if (!this.isDestroyed())
            {
                this.stopGameCanvasRunnableInterface();
                this.setGameCanvasRunnableInterface(null);
                //if(!AppletUtil.isAppletLoader(this))
                //{
                this.mediaShutdown();
                //}
            }
            else
            {
                LogUtil.put(LogFactory.getInstance(
                    "Midlet Managment Error: Midlet Should Only Be Destroyed Once",
                    this, "GameMidlet::destroyApp"));
            }

            super.destroyApp(true);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "destroyApp", e));
        }
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, this, "GameMidlet::destroyApp"));
        PreLogUtil.put(CommonStrings.getInstance().END, this, "destoryApp");
    }
 
    protected void startApp()
    {
        try
        {
            GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();
            
            gameAdState.getAdvertisements().startAll();
            
            final String START_APP = "startApp";
            
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, START_APP));
            //PreLogUtil.put(CommonStrings.getInstance().START, this, START_APP);

            GameCanvasRunnableInterface gameCanvasRunnableInterface =
                this.getGameCanvasRunnableInterface();

            //If the first time/null then start demo or unpausing
            //thus start up only occurs one time ever otherwise this is just unpause
            if (gameCanvasRunnableInterface == null)
            {
                this.setDemo();
            }
            else
            {
                LogUtil.put(LogFactory.getInstance("GameCanvasRunnableInterface is available", this, START_APP));

                //Starting midlet after a previous start means that we only unpause the
                //GameCanvasRunnableInterface if it is currently displayed
                if (gameCanvasRunnableInterface == this.getCurrentDisplayable())
                {
                    this.unPauseAppBackground(false);
                }
                else
                {
                    LogUtil.put(LogFactory.getInstance("GameCanvasRunnableInterface is not current displayable", this, START_APP));
                }
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "startApp", e));
            destroyApp(false);
            //TWB - Only remove from context when multiple midlets share the same emulator
            notifyDestroyed();
        }
    }
    
    public synchronized void commandAction(Command command, Displayable displayable)
    {
        try
        {
            final String COMMAND_ACTION = MidletStrings.getInstance().COMMAND_ACTION;
            
            //PreLogUtil.put(COMMAND_NAME + command.getLabel() +
              //  " Displayable: " + displayable, this,
                //"GameMidlet::commandAction");

            GameCommandsFactory gameCommandsFactory = 
                GameCommandsFactory.getInstance();

            if (command == gameCommandsFactory.SHOW_GAME_CANVAS)
            {
                //TWB - If progress concurrency is a problem then this is probably the cause
                if(this.getDisplay().getCurrent() != this.getGameCanvasRunnableInterface())
                {
                    this.setDisplay((Displayable) this.getGameCanvasRunnableInterface());
                }

                this.unPauseAppBackground(false);
            }
            else if (command == gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS)
            {
                //this.closeMenuListener();
                MenuListener menuListener = (MenuListener) displayable;
                menuListener.close();

                this.setDisplay((Displayable)
                    this.getGameCanvasRunnableInterface());
                this.unPauseAppBackground(false);
            }
            else if (command == gameCommandsFactory.EXIT_COMMAND)
            {
                //TWB - Handle exit for android differently
                if(GameMidletEventHandler.getInstance().getEventListenerInterfaceList().size() == 0)
                {
                    this.exit(true);
                }
                
                GameMidletEventHandler.getInstance().fireEvent(null);
            }
            else if (command == gameCommandsFactory.EXIT_WITHOUT_PROGRESS_COMMAND)
            {
                this.exit(false);
            }
            else if (command == gameCommandsFactory.START_COMMAND ||
                command == gameCommandsFactory.RESTART_COMMAND ||
                command == gameCommandsFactory.CONTINUE_COMMAND)
            {
                // GameState gameState = GameStateFactory.getInstance(command);
                if (currentGameState != GameState.PLAYING_GAME_STATE)
                {
                    if (this.gameStartTimeHelper.isTime())
                    {
                        this.createGame();
                        currentGameState = GameState.PLAYING_GAME_STATE;
                    }
                    else
                    {
                        LogUtil.put(LogFactory.getInstance(
                            "Starting Game Too Often", this, MidletStrings.getInstance().COMMAND_ACTION));
                    }

                }
                else
                {
                    LogUtil.put(LogFactory.getInstance("Already in playing state", this, COMMAND_ACTION));
                }

            }
            else if (command == gameCommandsFactory.QUIT_COMMAND)
            {
                if (this.gameStartTimeHelper.isTime())
                {
                    this.stopGameCanvasRunnableInterface();
                    this.setDemo();
                }
            }
            else if (command == MyCommandsFactory.getInstance().RESUME_COMMAND)
            {
                this.unPauseAppBackground(false);
            }
            else if (command == MyCommandsFactory.getInstance().PAUSE_COMMAND)
            {
                this.pauseAppBackground(false);
            }
            else if (command == MyCommandsFactory.getInstance().SET_DISPLAYABLE)
            {
                this.pauseAppBackground(false);

                this.setDisplay(displayable);
            }
            else if (command == gameCommandsFactory.SET_MENU_DISPLAYABLE)
            {
                this.pauseAppBackground(false);

                MenuListener menuListener = (MenuListener) displayable;
                menuListener.open();

                this.setDisplay(displayable);
            }
            else if (command == gameCommandsFactory.START_TRACE)
            {
                this.debugInterface.start();
                ((AllBinaryGameCanvas) this.getGameCanvasRunnableInterface()).addCommand(
                    gameCommandsFactory.STOP_TRACE);
                ((AllBinaryGameCanvas) this.getGameCanvasRunnableInterface()).removeCommand(
                    gameCommandsFactory.START_TRACE);
            }
            else if (command == gameCommandsFactory.STOP_TRACE)
            {
                this.pauseAppBackground(false);
                this.debugInterface.stop();
                this.unPauseAppBackground(false);
                ((AllBinaryGameCanvas) this.getGameCanvasRunnableInterface()).addCommand(
                    gameCommandsFactory.START_TRACE);
                ((AllBinaryGameCanvas) this.getGameCanvasRunnableInterface()).removeCommand(
                    gameCommandsFactory.STOP_TRACE);
            }
            else if (command == gameCommandsFactory.DEFAULT_OPTIONS)
            {
                Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof GameOptionsForm)
                {
                    GameFeatureFormUtil.getInstance().setDefault(
                            (CommandForm) tempDisplayable);
                }
            }
            else if (command == GameInputMappingInstructionsCanvas.DISPLAY)
            {
                Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof GameInputMappingCanvas)
                {
                    GameInputMappingCanvas gameInputMappingCanvas = (GameInputMappingCanvas) tempDisplayable;
                    gameInputMappingCanvas.update();
                }

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE, this.getInputMappingInstructionsCanvas());

                //this.setDisplay((Displayable) this.getInputMappingInstructionsCanvas());
            }
            else if (HighScoreCommandsFactory.getInstance().isHighScoreCommand(command))
            {
                Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof HighScoresCanvas)
                {
                    HighScoresCanvas highScoresCanvas =
                        (HighScoresCanvas) tempDisplayable;

                    highScoresCanvas.updateCommand(command);
                }
            }
            else if (command == HighScoreCommands.getInstance().DISPLAY)
            {
                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    this.createHighScoresCanvas());
            }
            else if (command == GameInputMappingInstructionsCanvas.CLOSE)
            {
                MenuListener menuListener = (MenuListener) displayable;
                menuListener.close();

                this.commandAction(GameInputMappingCanvas.DISPLAY, null);
            }
            else if (command == GameInputMappingCanvas.DISPLAY)
            {
                this.pauseAppBackground(false);

                this.commandAction(gameCommandsFactory.SET_MENU_DISPLAYABLE, this.getInputMappingCanvas());

                //this.setDisplay((Displayable) this.getInputMappingCanvas());

            }
            else if (command == GameInputMappingCanvas.DEFAULT)
            {
                Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof GameInputMappingCanvas)
                {
                    GameInputMappingCanvas gameInputMappingCanvas =
                        (GameInputMappingCanvas) tempDisplayable;
                    gameInputMappingCanvas.setDefault();
                }
            }
            else if (command == InGameOptionsForm.DISPLAY)
            {
                this.pauseAppBackground(false);

                ProgressCanvas progressCanvas =
                    ProgressCanvasFactory.getInstance();

                progressCanvas.addPortion(50, "In Game Options");

                AllBinaryGameLayerManager layerManager = this.createGameLayerManager();
                
                InGameOptionsFormFactory.init(this,
                    new InGameFeatures(), "Options In Game",
                    layerManager.getBackgroundBasicColor(),
                    layerManager.getForegroundBasicColor()
                    );
                
                InGameOptionsForm inGameOptionsForm = 
                    InGameOptionsFormFactory.getInstance();

                inGameOptionsForm.setItemStateListener(
                        this.getGameCanvasRunnableInterface());

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    inGameOptionsForm);
            }
            else if (command == InGameOptionsForm.DEFAULT)
            {
                GameFeatureFormUtil.getInstance().setDefault(
                        InGameOptionsFormFactory.getInstance());
            }
            else if (command == gameCommandsFactory.DISPLAY_OPTIONS)
            {
                AllBinaryMediaManager.setMuted(true);
                this.stopAll();

                MainFeatureFactory mainFeatureFactory =
                    MainFeatureFactory.getInstance();
                Features features = Features.getInstance();

                isFullScreen = features.isFeature(
                    mainFeatureFactory.FULL_SCREEN);
                
                //PreLogUtil.put("Open isFullScreen: " + isFullScreen, this, MidletStrings.getInstance().COMMAND_ACTION);

                ResizableListenerHandler.getInstance().fireEvent(true);
                
                this.setResized(false);

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    this.getGameOptionsForm());
            }
            else if (command == gameCommandsFactory.CLOSE_OPTIONS)
            {
                //PreLogUtil.put("Close", this, MidletStrings.getInstance().COMMAND_ACTION);
                ResizableListenerHandler.getInstance().fireEvent(false);

                this.commandAction(
                    gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS,
                    displayable);

                StringBuilder stringBuffer = new StringBuilder();
                stringBuffer.append("Close isFullScreen/change: ");
                stringBuffer.append(isFullScreen);
                stringBuffer.append(FullScreenUtil.isScreenChange(isFullScreen));
                stringBuffer.append(" isResized: ");
                stringBuffer.append(this.isResized());

                PreLogUtil.put(stringBuffer.toString(), this, COMMAND_ACTION);

                //Restart canvas if screen change
                if(this.isResized() ||
                   FullScreenUtil.isScreenChange(isFullScreen))
                {
                    //PreLogUtil.put("Resized/Changed", this, MidletStrings.getInstance().COMMAND_ACTION);

                    MainFeatureFactory mainFeatureFactory =
                        MainFeatureFactory.getInstance();

                    ScreenListenerHandler.getInstance().fire(
                        Features.getInstance().isFeature(mainFeatureFactory.FULL_SCREEN)
                        );

                    this.commandAction(
                        gameCommandsFactory.QUIT_COMMAND, displayable);
                }

                AllBinaryMediaManager.setMuted(false);
            }
            else if (command == gameCommandsFactory.DISPLAY_LOAD_FORM)
            {
                this.pauseAppBackground(false);

                KeyValuePersistance keyValuePersistance = 
                    GamePersistanceSingleton.getInstance();
                
                keyValuePersistance.clear();
                keyValuePersistance.loadAll();

                if(this.getLoadGameForm() == null)
                {
                    AllBinaryGameLayerManager layerManager = this.createGameLayerManager();
                    
                    this.setLoadGameForm(new LoadGameForm(this, "Load Game",
                            layerManager.getBackgroundBasicColor(),
                            layerManager.getForegroundBasicColor()));
                }
                else
                {
                    this.getLoadGameForm().update();
                }

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE, this.getLoadGameForm());

                //this.setDisplay((Displayable) this.getLoadGameForm());
            }
            else if (command == gameCommandsFactory.LOAD_FILE)
            {
                int index = this.getLoadGameForm().getSelectedId();

                if (index != -1)
                {
                    KeyValuePersistance keyValuePersistance = 
                        GamePersistanceSingleton.getInstance();
                    
                    this.setStartStateHashtable(keyValuePersistance.get(index));

                    MenuListener menuListener = this.getLoadGameForm();
                    menuListener.close();

                    PreLogUtil.put(
                            BasicMotionGesturesHandler.getInstance().toString(), this, COMMAND_ACTION);
                    
                    this.commandAction(gameCommandsFactory.START_COMMAND, null);

                    PreLogUtil.put(
                            BasicMotionGesturesHandler.getInstance().toString(), this, COMMAND_ACTION);
                }
            }
            else if (command == gameCommandsFactory.DELETE_FILE)
            {
                int index = this.getLoadGameForm().getSelectedText();

                if (index != -1)
                {
                    KeyValuePersistance keyValuePersistance = 
                        GamePersistanceSingleton.getInstance();
                    
                    keyValuePersistance.delete(index);
                    keyValuePersistance.clear();
                    keyValuePersistance.loadAll(1);
                    this.getLoadGameForm().update();
                }
            }
            /*
            else if (command == GameCommands.DISPLAY_SAVE_FORM)
            {
            this.pauseApp();
            this.setDisplay((Displayable) this.getSaveGameForm());
            }*/
            else if (command == gameCommandsFactory.SAVE)
            {
                KeyValuePersistance keyValuePersistance = 
                    GamePersistanceSingleton.getInstance();
                
                this.pauseAppBackground(false);
                //String name = ((SaveGameForm) this.getSaveGameForm()).getText();
                keyValuePersistance.save(
                    this.getCurrentStateHashtable());

                //this.setDisplay((Displayable) getGameCanvasRunnableInterface());

                this.unPauseAppBackground(false);
            }
            else if (command == HighScoreTextBox.SUBMIT_TEXTBOX_COMMAND)
            {
                LogUtil.put(LogFactory.getInstance("Submitted Score", this, COMMAND_ACTION));

                this.getGameCanvasRunnableInterface().setHighScoreSubmitted(true);

                this.commandAction(gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS, displayable);
            }
            else if (command == gameCommandsFactory.DISPLAY_ABOUT)
            {
                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    this.getAboutCanvas());
            }
            else if (command == gameCommandsFactory.TOGGLE_KEYBOARD)
            {
                VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                    VirtualKeyboardEventHandler.getInstance();
                
                virtualKeyboardEventHandler.fireEvent(virtualKeyboardEventHandler.SHOW_EVENT);
            }
            
            //no else
            if (command != gameCommandsFactory.START_COMMAND && command != gameCommandsFactory.RESTART_COMMAND)
            {
                currentGameState = GameState.NO_GAME_STATE;
            }

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, MidletStrings.getInstance().COMMAND_ACTION, e));
            if (command != GameCommandsFactory.getInstance().EXIT_COMMAND)
            {
                this.exit(false);
            }
        }
    }

    private boolean isFullScreen;

    /*
    private void closeMenuListener() throws Exception
    {
    Display display = this.getDisplay();
    if(display != null)
    {
    Displayable displayable = display.getCurrent();
    if(displayable != null)
    {
    if(displayable instanceof GameCommandCanvas)
    {
    MenuListener menuListener = (MenuListener) displayable;
    menuListener.close();
    }
    }
    }
    }
     */
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    protected MyCanvas getInputMappingInstructionsCanvas()
        throws Exception
    {
        return new GameInputMappingInstructionsCanvas(this, this.createGameLayerManager());
    }

    protected MyCanvas getInputMappingCanvas()
        throws Exception
    {
        //Paintable[] paintableArray = {this.getHelpPaintable()}; 
        //return new BasicPaintablesCanvas(this, this.createGameLayerManager(), paintableArray);
        return new GameInputMappingCanvas(this, this.createGameLayerManager(), this.getHelpPaintable());
    }

    protected MyCanvas getAboutCanvas() throws Exception
    {
        return new BasicPaintablesCanvas(this, this.createGameLayerManager(),
            AboutPaintable.getInstance().getPaintableArrayInstance());
    }

    protected HelpPaintable getHelpPaintable()
        throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    protected HighScoresCanvas createHighScoresCanvas() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    protected void startGameCanvasRunnableInterface() throws Exception
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this,
        // "startGameCanvasRunnableInterface"));
        thread = new Thread(this.getGameCanvasRunnableInterface());

        LogUtil.put(LogFactory.getInstance(
                "Thread Priority: " + thread.getPriority(), this,
                "startGameCanvasRunnableInterface"));

        // canvasThread.setPriority(Thread.NORM_PRIORITY + 2);
        //thread.setPriority(Thread.MAX_PRIORITY);

        this.getGameCanvasRunnableInterface().setThread(thread);

        thread.start();
    }

    protected void stopGameCanvasRunnableInterface() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "stopGameCanvasRunnableInterface"));

        GameNotificationEventHandler.getInstance().removeAllListeners();
        ColorChangeEventHandler.getInstance().removeAllListeners();
        GameEventHandlerUtil.removeAllListeners();
        
        // TWB- Depends on the phone - emulators like it without but i think my
        // sprint phone needed a null canvas
        // this.setDisplay((Displayable) new ProgressCanvas(this));
        
        GameCanvasRunnableInterface gameCanvasRunnableInterface = 
            this.getGameCanvasRunnableInterface();
        if (gameCanvasRunnableInterface != null)
        {
            LogUtil.put(LogFactory.getInstance("Set Running False", this,
                "stopGameCanvasRunnableInterface"));
            gameCanvasRunnableInterface.setRunning(false);
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("StopGame - Could Not Stop", this,
                "stopGameCanvasRunnableInterface"));
        }

        // Wait for the thread to end then continue
        ThreadUtil.getInstance().join(this.thread);
        
        if(Features.getInstance().isFeature(MainFeatureFactory.getInstance().LOAD_ALL))
        {
            ProgressCanvasFactory.getInstance().addPortion(50, "Stopped Game Runnable");
        }
        else
        {
            ProgressCanvasFactory.getInstance().addPortion(50, "Stopped Main Runnable");
        }
        

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, this, "stopGameCanvasRunnableInterface"));
    }

    public GameCanvasRunnableInterface getGameCanvasRunnableInterface()
    {
        return allbinaryGameCanvasRunnableInterface;
    }

    protected void setGameCanvasRunnableInterface(
        GameCanvasRunnableInterface myGameCanvasInterface)
    {
        this.allbinaryGameCanvasRunnableInterface = myGameCanvasInterface;
    }

    protected AllBinaryGameLayerManager createGameLayerManager()
    {
        GameInfo gameInfo = new GameInfo(GameTypeFactory.getInstance().SINGLE_PLAYER,
            GameMode.SERVER, 1, 1);

        return new AllBinaryGameLayerManager(
                BasicColorFactory.getInstance().BLACK,
                BasicColorFactory.getInstance().WHITE, 
                gameInfo);
    }

    //protected void setGameOptionsForm(GameOptionsForm gameOptionsForm)
    //{
        //this.gameOptionsForm = gameOptionsForm;
    //}
    
    protected CommandForm getGameOptionsForm()
    {
        AllBinaryGameLayerManager layerManager = this.createGameLayerManager();

        return GameOptionsFormFactory.getInstance().init(this, "Game Options",
                layerManager.getBackgroundBasicColor(),
                layerManager.getForegroundBasicColor());
        //return GameOptionsForm.getInstance();
        //return gameOptionsForm;
    }

    public void save() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "save"));

        Hashtable hashtable = this.getCurrentStateHashtable();
        GamePersistanceSingleton.getInstance().save(hashtable);
    }

    public Hashtable getCurrentStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "getCurrentStateHashtable"));
        Hashtable hashtable = new Hashtable();

        if (this.getGameCanvasRunnableInterface() != null)
        {
            Hashtable currentHashtable = this.getGameCanvasRunnableInterface().getCurrentStateHashtable();
            Enumeration enumeration = currentHashtable.keys();
            while (enumeration.hasMoreElements())
            {
                Object key = enumeration.nextElement();
                hashtable.put(key, currentHashtable.get(key));
            }
        }

        return hashtable;
    }

    public void setLoadGameForm(LoadGameForm loadGameForm)
    {
        this.loadGameForm = loadGameForm;
    }

    public LoadGameForm getLoadGameForm()
    {
        return loadGameForm;
    }
    
    private boolean resized;

    public void setResized(boolean resized)
    {
        this.resized = resized;
    }

    public boolean isResized()
    {
        return resized;
    }
    
}
