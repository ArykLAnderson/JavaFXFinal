package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by aryka on 3/16/2017.
 */
public class BlankTile extends PlayTile {

    public BlankTile() {
        super();
        this.setBackground(new Background(new BackgroundFill(new Color(0.2, 0.5, 0.5, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
