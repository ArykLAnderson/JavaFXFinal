package game.tiles;

import game.Actor.MovementBehavior.MovementBehavior;
import game.Actor.Robot;

public class RightTile extends PlayTile {

    public RightTile() {
        super();
        this.getChildren().add(ImageLookupService.getTileImageView(ImageType.RIGHT));
    }

    @Override
    public MovementBehavior getBehavior(Robot robot) {
        return null;
    }
}
