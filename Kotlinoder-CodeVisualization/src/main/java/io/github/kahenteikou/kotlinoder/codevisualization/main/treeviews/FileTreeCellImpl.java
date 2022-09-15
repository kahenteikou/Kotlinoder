package io.github.kahenteikou.kotlinoder.codevisualization.main.treeviews;

import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;

public class FileTreeCellImpl extends TreeCell<TreeWrappedItem> {
    private static final Image ClsImage=loadImage("clsicon.png");
    private static Image loadImage(String filepath){
        return new Image(FileTreeCellImpl.class.getResourceAsStream(filepath));
    }

    @Override
    protected void updateItem(TreeWrappedItem item, boolean empty) {
        super.updateItem(item, empty);
        
    }
}
