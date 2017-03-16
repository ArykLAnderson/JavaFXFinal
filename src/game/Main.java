package game;

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
import javafx.stage.Stage;


public class Main extends Application {

    private enum ToolPos {LEFT, UP, RIGHT}

    public static void main(String[] args) {
        launch(args);
    }

    private BorderPane _root;
    private BorderPane _gameArea;
    private Label _successCondition;
    private Label _currentTape;
    private ToolPos _currentOrientation;
    private PlayFieldPane _playField;


    @Override
    public void start(Stage primaryStage) throws Exception{

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
        primaryStage.show();
    }

    private MenuBar buildMenus() {

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");

        MenuItem quitMenuItem = new MenuItem("_Quit");
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(quitMenuItem);

        Menu viewMenu = new Menu("_Game");
        ToggleGroup group = new ToggleGroup();
        RadioMenuItem defaultMenuItem = new RadioMenuItem("_JavaFX");
        defaultMenuItem.setToggleGroup(group);
        defaultMenuItem.setSelected(true);
        defaultMenuItem.setOnAction(actionEvent -> _root.getStylesheets().clear());

        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu);
        return menuBar;
    }

    private void setSuccessCondition(String text) {
        _successCondition.setText(text);
    }

    private void setCurrentTape(String text) {
        _currentTape.setText(text);
    }

    private void setCurrentTileType(TileType type) {
        _playField.setTileType(type);
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
        _successCondition = new Label("Success Condition");
        _currentTape = new Label("Current TapeCase");
        box.getChildren().addAll(_successCondition, _currentTape);
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
}
