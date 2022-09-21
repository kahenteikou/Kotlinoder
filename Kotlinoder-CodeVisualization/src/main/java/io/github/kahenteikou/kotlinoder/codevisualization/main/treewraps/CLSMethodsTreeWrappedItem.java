package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

public class CLSMethodsTreeWrappedItem extends TreeWrappedItem{

    public CLSMethodsTreeWrappedItem(String title, TreeWrappedItemType type) {
        super(title, type);
    }
    public CLSMethodsTreeWrappedItem(){
        super("Properties", TreeWrappedItemType.CLSMETHODS);
    }
}
