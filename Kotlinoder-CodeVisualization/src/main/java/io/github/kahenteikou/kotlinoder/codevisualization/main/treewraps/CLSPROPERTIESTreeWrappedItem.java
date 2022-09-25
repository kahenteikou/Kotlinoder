package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.codevisualization.main.IMainWinController;
import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration;

public class CLSPROPERTIESTreeWrappedItem extends TreeWrappedItem{
    private ClassDeclaration cd;
    public CLSPROPERTIESTreeWrappedItem(String title, TreeWrappedItemType type,ClassDeclaration cd2) {
        super(title, type);
        cd=cd2;
    }
    public CLSPROPERTIESTreeWrappedItem(ClassDeclaration cd2){
        super("Properties", TreeWrappedItemType.CLSPROPERTIES);
        cd=cd2;
    }

    @Override
    public void onDoubleClick() {
        System.out.println("Double clicked CLSProp");
    }

    @Override
    public void onDoubleClick(IMainWinController controller) {
        System.out.println("Double clicked CLSProp II");

    }
}
