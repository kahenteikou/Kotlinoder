package io.github.kahenteikou.kotlinoder.codevisualization.main.TreeitemWs;

import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.ClassTreeWrappedItem;
import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem;
import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

public class TreeItem_ClassTreeWrappedItem extends TreeItem<TreeWrappedItem> {

    private static final Image ClsImage=loadImage("clsicon.png");
    private static Image loadImage(String filepath){
        return new Image(TreeItem_ClassTreeWrappedItem.class.getResourceAsStream(filepath));
    }
    public TreeItem_ClassTreeWrappedItem(ClassTreeWrappedItem value) {
        super(value,new javafx.scene.image.ImageView(ClsImage));
    }
    public TreeItem_ClassTreeWrappedItem(ClassDeclaration cd){
        super(new ClassTreeWrappedItem(cd),new javafx.scene.image.ImageView(ClsImage));
    }
}
