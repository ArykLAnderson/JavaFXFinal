package game;

import game.tiles.*;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class PlayFieldPane extends GridPane{

    private TileType _currentType;
    private TileType[][] _saveGrid;

    public PlayFieldPane() {
        super();
        _saveGrid = new TileType[13][13];
        this.init();
        _currentType = TileType.BLANK;
    }

    private PlayFieldPane(boolean simple) {
        super();
        _saveGrid = new TileType[13][13];
        _currentType = TileType.BLANK;
    }

    public void init() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {

                PlayTile tile;
                if (j == 0 && i == 6) {
                    tile = TileFactory.instance().createTile(TileType.SOURCE);
                    _saveGrid[i][j] = TileType.SOURCE;
                }
                else if (j == 12 && i == 6) {
                    tile = TileFactory.instance().createTile(TileType.SINK);
                    _saveGrid[i][j] = TileType.SINK;
                }
                else {
                    tile = TileFactory.instance().createTile(TileType.BLANK);
                    _saveGrid[i][j] = TileType.BLANK;
                }

                tile.setTilePrefSize();
                tile.setXPos(i);
                tile.setYPos(j);
                tile.setOnMouseClicked(this::replaceTile);

                this.add(tile, i, j, 1, 1);
            }
        }
    }

    public void setTileType(TileType type) {
        _currentType = type;
    }

    public void replaceTile(MouseEvent event) {

        PlayTile source = (PlayTile) event.getSource();

        if (source instanceof SinkTile || source instanceof SourceTile)
            return;

        int x = source.getXPos();
        int y = source.getYPos();

        this.getChildren().remove(source);
        PlayTile newTile = TileFactory.instance().createTile(_currentType);
        _saveGrid[x][y] = _currentType;
        newTile.setTilePrefSize();
        newTile.setXPos(x);
        newTile.setYPos(y);
        newTile.setOnMouseClicked(this::replaceTile);

        this.add(newTile, x, y, 1, 1);
    }

    public void parseSolution(File file) throws IOException, NumberFormatException {

        if (file == null)
            throw new IOException("No file pointer supplied");

        this.getChildren().clear();
        Scanner scanner = new Scanner(new FileInputStream(file));

        scanner.nextLine();
        scanner.nextLine();

        for (int i = 0; i < 13; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 13; j++) {
                char cur = line.charAt(j);
                _saveGrid[j][i] = charToTileMap(cur);
                PlayTile newTile = TileFactory.instance().createTile(charToTileMap(cur));
                newTile.setTilePrefSize();
                newTile.setXPos(j);
                newTile.setYPos(i);
                newTile.setOnMouseClicked(this::replaceTile);
                this.add(newTile, j, i);
            }
        }
    }

    public static char tileToCharMap(TileType type) {

        switch(type) {
            case BLANK: return '0';
            case SOURCE: return '1';
            case SINK: return '2';
            case UP: return '3';
            case DOWN: return '4';
            case LEFT: return '5';
            case RIGHT: return '6';
            case UPSWITCH: return '7';
            case DOWNSWITCH: return '8';
            case RIGHTSWITCH: return 'a';
            case LEFTSWITCH: return '9';
        }

        return ' ';
    }

    public static TileType charToTileMap(char map) {

        switch(map) {
            case '0':
                return TileType.BLANK;
            case '1':
                return TileType.SOURCE;
            case '2':
                return TileType.SINK;
            case '3':
                return TileType.UP;
            case '4':
                return TileType.DOWN;
            case '5':
                return TileType.LEFT;
            case '6':
                return TileType.RIGHT;
            case '8':
                return TileType.DOWNSWITCH;
            case '7':
                return TileType.UPSWITCH;
            case '9':
                return TileType.LEFTSWITCH;
            case 'a':
                return TileType.RIGHTSWITCH;
        }
        return null;
    }
}
