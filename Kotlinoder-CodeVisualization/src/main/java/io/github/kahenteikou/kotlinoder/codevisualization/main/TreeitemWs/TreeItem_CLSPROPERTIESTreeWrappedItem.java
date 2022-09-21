package io.github.kahenteikou.kotlinoder.codevisualization.main.TreeitemWs;

import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.CLSPROPERTIESTreeWrappedItem;
import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem;
import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration;
import javafx.scene.control.TreeItem;

public class TreeItem_CLSPROPERTIESTreeWrappedItem extends TreeItem<TreeWrappedItem> {
    public TreeItem_CLSPROPERTIESTreeWrappedItem(CLSPROPERTIESTreeWrappedItem value) {
        super(value);
    }
    public TreeItem_CLSPROPERTIESTreeWrappedItem(ClassDeclaration cd2){
        super(new CLSPROPERTIESTreeWrappedItem(cd2));
    }
}
