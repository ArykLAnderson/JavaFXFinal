package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

/**
 * Created by aryka on 3/16/2017.
 */
public class DownTile extends PlayTile {

    public DownTile() {

        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.DOWN));
    }
    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
