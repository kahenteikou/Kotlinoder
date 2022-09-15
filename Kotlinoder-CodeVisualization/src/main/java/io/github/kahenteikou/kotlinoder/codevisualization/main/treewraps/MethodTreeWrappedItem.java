package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;

public class MethodTreeWrappedItem extends TreeWrappedItem{
    public MethodTreeWrappedItem(MethodDeclaration md){
        super(md.getName(),TreeWrappedItemType.METHOD);
    }
}
