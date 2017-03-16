package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

/**
 * Created by aryka on 3/16/2017.
 */
public class UpSwitchTile extends PlayTile {

    public UpSwitchTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.UPSWITCH));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
