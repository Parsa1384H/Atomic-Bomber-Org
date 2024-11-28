package view;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.User;


public class DragAvatarControllerView {

    public void drag(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        dragEvent.consume();
    }

    public void drop(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasFiles()) {
            try {
                User.getLoggedInUser().setAvatarPath(dragboard.getFiles().get(0).getPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dragEvent.setDropCompleted(true);
        dragEvent.consume();
        DragMenu.stage.close();
    }
}
