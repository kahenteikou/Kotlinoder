package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.instrumentation.ClassDeclaration;

public class ClassTreeWrappedItem extends TreeWrappedItem {
    private ClassDeclaration cd;
    public ClassTreeWrappedItem(ClassDeclaration clsd){
        super(clsd.getName(), TreeWrappedItemType.CLASS);
        cd=clsd;
    }
    public ClassDeclaration getClassDeclaration(){
        return cd;
    }
}
