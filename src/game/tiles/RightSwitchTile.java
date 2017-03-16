package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

public class RightSwitchTile extends PlayTile {

    public RightSwitchTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.RIGHTSWITCH));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
