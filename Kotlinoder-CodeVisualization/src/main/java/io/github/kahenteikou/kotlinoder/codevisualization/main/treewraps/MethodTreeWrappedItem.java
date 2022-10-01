package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.codevisualization.main.IMainWinController;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.ClassEditorTabController;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.MethodEditorTabController;
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS;
import io.github.kahenteikou.kotlinoder.instrumentation.CompilationUnitDeclaration;
import io.github.kahenteikou.kotlinoder.instrumentation.MethodDeclaration;
import io.github.kahenteikou.kotlinoder.instrumentation.Scope;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import org.apache.logging.log4j.LogManager;


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
            Tab tab=new Tab(String.format("Method - %s",md.getName()));
            tab.setContent(loader.getRoot());
            MethodEditorTabController tabCon=loader.getController();
            tabCon.setMethodinfo(md);
            Scope currentS=md;
            String pkgName="";
            while(true){
                if(currentS instanceof CompilationUnitDeclaration){
                    pkgName= ((CompilationUnitDeclaration) currentS).getPackageName();
                    break;
                }
                if(currentS==null){
                    break;
                }
                currentS=currentS.getParent();

            }
            controller.add_tab(tab,String.format("METHOD_%s_%s_%s",pkgName,md.getClass().getName(),md.getName()));
            LogManager.getLogger("MethodEditorTabController").debug(String.format("METHOD_%s_%s_%s",pkgName,md.getClass().getName(),md.getName()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
