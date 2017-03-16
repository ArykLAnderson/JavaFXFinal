package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

public class LeftTile extends PlayTile {

    public LeftTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.LEFT));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }

}
