package game;

import game.Actor.Challenge;
import game.Actor.TapeCase;
import game.tiles.ImageLookupService;
import game.tiles.ImageType;
import game.tiles.TileType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {

    private enum ToolPos {LEFT, UP, RIGHT}

    public static void main(String[] args) {
        launch(args);
    }

    private BorderPane _root;
    private BorderPane _gameArea;
    private Label _successCondition;
    private Label _currentTapeLabel;
    private ToolPos _currentOrientation;
    private PlayFieldPane _playField;
    private Stage _primaryStage;
    private Challenge _currentChallenge = null;
    private TapeCase _currentTape = null;
    private MenuItem _openChallenge;
    private MenuItem _openSolution;
    private MenuItem _goMenuItem;
    private MenuItem _pauseMenuItem;
    private TileType _curTileType = TileType.BLANK;
    private boolean _running = false;

    @Override
    public void start(Stage primaryStage) throws Exception{

        _primaryStage = primaryStage;
        _root = new BorderPane();
        _gameArea = new BorderPane();
        _playField = new PlayFieldPane();
        _gameArea.setCenter(_playField);
        _gameArea.setLeft(createToolbar(true));
        _currentOrientation = ToolPos.LEFT;

        _root.setCenter(_gameArea);
        _root.setTop(buildMenus());
        _root.setBottom(createGameInfoArea());

        primaryStage.setTitle("game.Actor.Robot Factory");
        primaryStage.setScene(new Scene(_root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Robot Factory - Load a Challenge");
        primaryStage.getIcons().add(ImageLookupService.getImage(ImageType.ICON));
        primaryStage.show();
    }

    private MenuBar buildMenus() {

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");

        MenuItem resetMenuItem = new MenuItem("_Reset"); //R
        resetMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        resetMenuItem.setOnAction(actionEvent -> onReset());

        _openChallenge = new MenuItem("_Open Challenge"); //O
        _openChallenge.setOnAction(actionEvent -> openChallenge());
        _openChallenge.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        _openSolution = new MenuItem("_Load solution"); //L
        _openSolution.setOnAction(actionEvent -> openSolution());
        _openSolution.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

        MenuItem saveSolution = new MenuItem("_Save Solution");
        saveSolution.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveSolution.setOnAction(actionEvent -> onSave());

        MenuItem quitMenuItem = new MenuItem("_Quit"); //Q
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());

        SeparatorMenuItem item = new SeparatorMenuItem();

        fileMenu.getItems().addAll(resetMenuItem, _openChallenge, _openSolution, saveSolution, item, quitMenuItem);

        Menu gameMenu = new Menu("_Game");

        _goMenuItem = new MenuItem("_Go");
        _goMenuItem.setOnAction(actionEvent -> onGo());
        _goMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        _pauseMenuItem = new MenuItem("_Pause");
        _pauseMenuItem.setOnAction(actionEvent -> onPause());
        _pauseMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
        _pauseMenuItem.setDisable(true);

        MenuItem faster = new MenuItem("_Faster");
        faster.setOnAction(actionEvent -> faster());
        faster.setAccelerator(new KeyCodeCombination(KeyCode.UP));

        MenuItem slower = new MenuItem("_Slower");
        slower.setOnAction(actionEvent -> slower());
        slower.setAccelerator(new KeyCodeCombination(KeyCode.DOWN));

        gameMenu.getItems().addAll(_goMenuItem, _pauseMenuItem, faster, slower);

        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
        return menuBar;
    }

    private void slower() {

    }

    private void faster() {

    }

    private void onGo() {
        //TODO
    }

    private void onPause() {
        //TODO
    }

    private void onReset() {
        if (_currentChallenge != null)
            _currentChallenge.reset();
        onPause();
        clearPlayField();
    }

    private void onSave() {
        //TODO stretch
    }

    private void clearPlayField() {
        _playField = new PlayFieldPane();
        Node remove = _gameArea.getCenter();
        _gameArea.getChildren().remove(remove);
        _gameArea.setCenter(_playField);
        _playField.setTileType(_curTileType);
    }

    private void setSuccessCondition(String text) {
        _successCondition.setText(text);
    }

    private void setCurrentTapeLabel(String text) {
        _currentTapeLabel.setText(text);
    }

    private void setCurrentTileType(TileType type) {
        _playField.setTileType(type);
        _curTileType = type;
    }

    private ToolBar createToolbar(boolean vertical) {

        ToggleGroup tilePlacers = new ToggleGroup();
        ToolBar tools = new ToolBar();
        tools.setOrientation(vertical ? Orientation.VERTICAL : Orientation.HORIZONTAL);

        ToggleButton button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.UP));
        button.setTooltip(new Tooltip("Upwards conveyor belt"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.UP));
        tools.getItems().add(button);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.DOWN));
        button.setTooltip(new Tooltip("Downwards conveyor belt"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.DOWN));
        tools.getItems().add(button);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.LEFT));
        button.setTooltip(new Tooltip("Leftwards conveyor belt"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.LEFT));
        tools.getItems().add(button);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.RIGHT));
        button.setTooltip(new Tooltip("Rightwards conveyor belt"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.RIGHT));
        tools.getItems().add(button);

        Separator seperator = new Separator();
        seperator.setOrientation(vertical ? Orientation.HORIZONTAL : Orientation.VERTICAL);
        tools.getItems().add(seperator);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.UPSWITCH));
        button.setTooltip(new Tooltip("Up oriented switch"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.UPSWITCH));
        tools.getItems().add(button);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.DOWNSWITCH));
        button.setTooltip(new Tooltip("Down oriented switch"));
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.DOWNSWITCH));
        button.setToggleGroup(tilePlacers);
        tools.getItems().add(button);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.LEFTSWITCH));
        button.setTooltip(new Tooltip("Left oriented switch"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.LEFTSWITCH));
        tools.getItems().add(button);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.RIGHTSWITCH));
        button.setTooltip(new Tooltip("Right oriented switch"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.RIGHTSWITCH));
        tools.getItems().add(button);

        seperator = new Separator();
        seperator.setOrientation(vertical ? Orientation.HORIZONTAL : Orientation.VERTICAL);
        tools.getItems().add(seperator);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.REMOVE));
        button.setTooltip(new Tooltip("Remove tile"));
        button.setToggleGroup(tilePlacers);
        button.setOnAction(actionEvent -> setCurrentTileType(TileType.BLANK));
        tools.getItems().add(button);

        seperator = new Separator();
        seperator.setOrientation(vertical ? Orientation.HORIZONTAL : Orientation.VERTICAL);
        tools.getItems().add(seperator);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.PLAY));
        button.setTooltip(new Tooltip("Play"));
        tools.getItems().add(button);

        seperator = new Separator();
        seperator.setOrientation(vertical ? Orientation.HORIZONTAL : Orientation.VERTICAL);
        tools.getItems().add(seperator);

        button = new ToggleButton();
        button.setGraphic(ImageLookupService.getStandardImageView(ImageType.MOVETOOLS));
        button.setOnAction(actionEvent -> onCycleToolPos());
        button.setTooltip(new Tooltip("Cycle toolbar position"));
        tools.getItems().add(button);

        tilePlacers.selectToggle(null);

        return tools;
    }

    private ToolBar createGameInfoArea() {

        ToolBar gameInfo = new ToolBar();
        VBox box = new VBox();
        _successCondition = new Label("");
        _currentTapeLabel = new Label("");
        box.getChildren().addAll(_successCondition, _currentTapeLabel);
        gameInfo.getItems().add(box);

        return gameInfo;
    }

    private void onCycleToolPos() {

        Node toolbar;
        switch(_currentOrientation) {

            case LEFT:
                toolbar = _gameArea.getLeft();
                _gameArea.getChildren().remove(toolbar);
                _gameArea.setTop(createToolbar(false));
                _currentOrientation = ToolPos.UP;
                break;
            case UP:
                toolbar = _gameArea.getTop();
                _gameArea.getChildren().remove(toolbar);
                _gameArea.setRight(createToolbar(true));
                _currentOrientation = ToolPos.RIGHT;
                break;
            case RIGHT:
                toolbar = _gameArea.getRight();
                _gameArea.getChildren().remove(toolbar);
                _gameArea.setLeft(createToolbar(true));
                _currentOrientation = ToolPos.LEFT;
                break;
        }
    }

    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Aryk L Anderson, CSCD 370 Final Project, Spring 2016");
        alert.showAndWait();
    }

    private void openChallenge() {

        FileChooser chooser = new FileChooser() ;
        chooser.setTitle("Open a line File");
        chooser.setInitialDirectory(new File("."));

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Challenge Files", "*.rbt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        try {
            _currentChallenge = Challenge.parseChallenge(chooser.showOpenDialog(_primaryStage));
            setSuccessCondition(_currentChallenge.getChallengeInfo());
            if (!_currentChallenge.challengeCompleted())
                _currentTape = _currentChallenge.consumeCase();
            setCurrentTapeLabel(_currentTape.getTapeString());
        } catch (IOException e) {
            //TODO print error message
        } catch (NumberFormatException f) {
            //TODO
        }
    }

    private void openSolution() {

        FileChooser chooser = new FileChooser() ;
        chooser.setTitle("Open a solution File");
        chooser.setInitialDirectory(new File("."));

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Solution Files", "*.grd"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        try {
            PlayFieldPane tryField = new PlayFieldPane();
            tryField.parseSolution(chooser.showOpenDialog(_primaryStage));
            _playField = tryField;
            Node temp = _gameArea.getCenter();
            _gameArea.getChildren().remove(temp);
            _gameArea.setCenter(_playField);
            _playField.setTileType(_curTileType);
        } catch (IOException e) {
            e.printStackTrace(); //TODO
        } catch (NumberFormatException f) {
            f.printStackTrace();
        }
    }
}
