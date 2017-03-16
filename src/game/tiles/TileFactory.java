package game.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TileFactory {

    private static TileFactory _instance;

    private TileFactory() {}

    public static TileFactory instance() {

        if (_instance == null)
            _instance = new TileFactory();

        return _instance;
    }

    public PlayTile createTile(TileType type) {

        switch(type) {

            case UP:
                return new UpTile();
            case DOWN:
                return new DownTile();
            case LEFT:
                return new LeftTile();
            case RIGHT:
                return new RightTile();
            case UPSWITCH:
                return new UpSwitchTile();
            case DOWNSWITCH:
                return new DownSwitchTile();
            case LEFTSWITCH:
                return new LeftSwitchTile();
            case RIGHTSWITCH:
                return new RightSwitchTile();
            case SINK:
                return new SinkTile();
            case SOURCE:
                return SourceTile.instance();
            default:
                return new BlankTile();
        }
    }
}
