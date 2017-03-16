package game;

import game.tiles.*;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class PlayFieldPane extends GridPane{

    private TileType _currentType;

    public PlayFieldPane() {
        super();
        this.init();
        _currentType = TileType.BLANK;
    }

    public void init() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {

                PlayTile tile;
                if (j == 0 && i == 6)
                    tile = TileFactory.instance().createTile(TileType.SOURCE);
                else if (j == 12 && i == 6)
                    tile = TileFactory.instance().createTile(TileType.SINK);
                else
                    tile = TileFactory.instance().createTile(TileType.BLANK);

                tile.setTilePrefSize();
                tile.setXPos(i);
                tile.setYPos(j);
                tile.setOnMouseClicked(this::replaceTile);

                this.add(tile, i, j, 1, 1);
            }
        }
    }

    public void setTileType(TileType type) {
        _currentType = type;
    }

    public void replaceTile(MouseEvent event) {

        PlayTile source = (PlayTile) event.getSource();

        if (source instanceof SinkTile || source instanceof SourceTile)
            return;

        int x = source.getXPos();
        int y = source.getYPos();

        this.getChildren().remove(source);
        PlayTile newTile = TileFactory.instance().createTile(_currentType);
        newTile.setTilePrefSize();
        newTile.setXPos(x);
        newTile.setYPos(y);
        newTile.setOnMouseClicked(this::replaceTile);

        this.add(newTile, x, y, 1, 1);
    }

//    private void onDragged(MouseEvent event) {
//
//        ImageView view = (ImageView) event.getSource();
//        Dragboard board = view.startDragAndDrop(TransferMode.COPY);
//        board.setDragView(view.getImage(), 0.5*view.getImage().getWidth(), 0.5*view.getImage().getWidth());
//        ClipboardContent content = new ClipboardContent();
//        content.putString((String)view.getUserData());
//        board.setContent(content);
//    }
//
//    private void onDragOver(DragEvent event) {
//
//        ImageView target = (ImageView) event.getTarget();
//        String data = (String) target.getUserData();
//
//        if (target != event.getSource())
//            return;
//
//        if (!data.equals("BLANK"))
//            return;
//
//        Dragboard dragboard = event.getDragboard();
//        if (dragboard.getString() == null)
//            return;
//
//        if (dragboard.getString().equals("X") || dragboard.getString().equals("O")) {
//            event.acceptTransferModes(TransferMode.COPY);
//        }
//    }
//
//    private void onDragDropped(DragEvent event) {
//        ImageView view = (ImageView) event.getSource();
//        Dragboard dragboard = event.getDragboard();
//
//        if (dragboard.getString().equalsIgnoreCase("x")) {
//            ((ImageView)event.getTarget()).setImage(map(ImageType.x).getImage());
//        } else if (dragboard.getString().equalsIgnoreCase("o")){
//            ((ImageView)event.getTarget()).setImage(map(ImageType.o).getImage());
//        } else
//            return;
//
//        event.setDropCompleted(true);
//    }
}
