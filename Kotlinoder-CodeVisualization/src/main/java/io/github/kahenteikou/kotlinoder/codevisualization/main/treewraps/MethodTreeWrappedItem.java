package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.codevisualization.main.IMainWinController;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.ClassEditorTabController;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.MethodEditorTabController;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS;
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class MethodTreeWrappedItem extends TreeWrappedItem{
    private MethodDeclaration md;
    public MethodTreeWrappedItem(MethodDeclaration methd){
        super(methd.getName(),TreeWrappedItemType.METHOD);
        md=methd;
    }
    public MethodDeclaration getMethodDeclaration(){
        return md;
    }

    @Override
    public void onDoubleClick(IMainWinController controller) {
        System.out.println("Double clicked method II");
        FXMLLoader loader=new FXMLLoader(new STUBCLS().getClass().getResource("MethodEditorTab.fxml"));
        try{
            loader.load();
            Tab tab=new Tab("Method");
            tab.setContent(loader.getRoot());
            MethodEditorTabController tabCon=loader.getController();
            //tabCon.setClsInfo(cd);
            controller.add_tab(tab,String.format("PROP_%s",md.getName()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
