package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;

public class MethodTreeWrappedItem extends TreeWrappedItem{
    private MethodDeclaration md;
    public MethodTreeWrappedItem(MethodDeclaration methd){
        super(methd.getName(),TreeWrappedItemType.METHOD);
        md=methd;
    }
    public MethodDeclaration getMethodDeclaration(){
        return md;
    }
}
