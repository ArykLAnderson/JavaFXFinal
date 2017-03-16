package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

/**
 * Created by aryka on 3/16/2017.
 */
public class LeftSwitchTile extends PlayTile {

    public LeftSwitchTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.LEFTSWITCH));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
