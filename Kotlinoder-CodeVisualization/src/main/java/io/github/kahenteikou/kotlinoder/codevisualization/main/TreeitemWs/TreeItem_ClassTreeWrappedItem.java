package io.github.kahenteikou.kotlinoder.codevisualization.main.TreeitemWs;

import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

public class TreeItem_ClassTreeWrappedItem extends TreeItem<TreeWrappedItem> {

    private static final Image ClsImage=loadImage("clsicon.png");
    private static Image loadImage(String filepath){
        return new Image(TreeItem_ClassTreeWrappedItem.class.getResourceAsStream(filepath));
    }
}
