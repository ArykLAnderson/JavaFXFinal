package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import javafx.scene.layout.Pane;
import game.Actor.Robot;

public abstract class PlayTile extends Pane{

    public static final int tileSize = 40;
    private int _xPos, _yPos;

    public abstract MovementBehavior getBehavior(Robot robot);

    public void setTilePrefSize() {
        this.setPrefSize(tileSize, tileSize);
    }

    public void setXPos(int x) {
        if (x >= 0 && x <=12)
            _xPos = x;
    }

    public void setYPos(int y) {
        if (y >= 0 && y <=12)
            _yPos = y;
    }

    public int getXPos() {
        return _xPos;
    }

    public int getYPos() {
        return _yPos;
    }
}
