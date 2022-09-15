package io.github.kahenteikou.kotlinoder.codevisualization.main.treeviews;

import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FileTreeCellImpl extends TreeCell<TreeWrappedItem> {
    private static final Image ClsImage=loadImage("clsicon.png");
    private static Image loadImage(String filepath){
        return new Image(FileTreeCellImpl.class.getResourceAsStream(filepath));
    }

    @Override
    protected void updateItem(TreeWrappedItem item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            setText(item.getTitle());
            switch(item.getType()){
                case CLASS:
                    setGraphic(new ImageView(ClsImage));
                    break;
                case METHOD:
                    setGraphic(getTreeItem().getGraphic());
                    break;
                case FILE:
                    setGraphic(getTreeItem().getGraphic());
                    break;
                case DIRECTORY:
                    setGraphic(getTreeItem().getGraphic());
                    break;
                case FIELD:
                    setGraphic(getTreeItem().getGraphic());
                    break;
                case ROOTNODE:
                    setGraphic(getTreeItem().getGraphic());
                    break;
            }
        }
    }
}
