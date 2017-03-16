package game.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageLookupService {

    private static ImageView getImageView(ImageType imageType, boolean def) {

        ImageView view = new ImageView();

        if (!def) {
            view.setFitHeight(PlayTile.tileSize);
            view.setFitWidth(PlayTile.tileSize);
        }

        view.setImage(getImage(imageType));
        return view;
    }

    public static ImageView getStandardImageView(ImageType imageType) {
        return getImageView(imageType, true);
    }

    public static ImageView getTileImageView(ImageType imageType) {
        return getImageView(imageType, false);
    }

    public static Image getImage(ImageType imageType) {

        switch (imageType) {

            case UP:
                return new Image("icons/up.png");
            case LEFT:
                return new Image("icons/left.png");
            case DOWN:
                return new Image("icons/down.png");
            case RIGHT:
                return new Image("icons/right.png");
            case RIGHTSWITCH:
                return new Image("icons/sw_right.png");
            case LEFTSWITCH:
                return new Image("icons/sw_left.png");
            case UPSWITCH:
                return new Image("icons/sw_up.png");
            case DOWNSWITCH:
                return new Image("icons/sw_down.png");
            case SINK:
                return new Image("icons/sink.png");
            case SOURCE:
                return new Image("icons/src.png");
            case BLUEROBOT:
                return new Image("icons/bluerobot.png");
            case REDROBOT:
                return new Image("icons/redrobot.png");
            case PLAY:
                return new Image("icons/go.png");
            case MOVETOOLS:
                return new Image("icons/Move.png");
            case REMOVE:
                return new Image("icons/x.png");
            case ROBOT:
                return new Image("icons/robot.png");
        }

        return null;
    }
}
