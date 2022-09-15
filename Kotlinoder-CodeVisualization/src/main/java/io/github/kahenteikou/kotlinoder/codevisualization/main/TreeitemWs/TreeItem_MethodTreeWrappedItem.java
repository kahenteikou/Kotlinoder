package io.github.kahenteikou.kotlinoder.codevisualization.main.TreeitemWs;

import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.ClassTreeWrappedItem;
import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.MethodTreeWrappedItem;
import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem;
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;
import javafx.scene.control.TreeItem;

public class TreeItem_MethodTreeWrappedItem extends TreeItem<TreeWrappedItem> {
    public TreeItem_MethodTreeWrappedItem(MethodTreeWrappedItem value) {
        super(value);
    }
    public TreeItem_MethodTreeWrappedItem(MethodDeclaration md){
        super(new MethodTreeWrappedItem(md));
    }
}
