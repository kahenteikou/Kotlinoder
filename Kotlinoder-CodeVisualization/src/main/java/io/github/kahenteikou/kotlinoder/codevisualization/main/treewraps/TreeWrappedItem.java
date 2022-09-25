package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.codevisualization.main.IMainWinController;

public class TreeWrappedItem {
    private String _title;
    private TreeWrappedItemType _type;
    public String getTitle(){
        return _title;
    }
    public TreeWrappedItemType getType(){
        return _type;
    }
    public void setTitle(String title){
        _title = title;
    }
    public void setType(TreeWrappedItemType type){
        _type=type;
    }
    public enum TreeWrappedItemType{
        CLASS,
        METHOD,
        FILE,
        DIRECTORY,
        FIELD,
        ROOTNODE,
        CLSPROPERTIES,
        CLSMETHODS
    }

    @Override
    public String toString() {
        return _title;
    }
    public TreeWrappedItem(String title, TreeWrappedItemType type){
        _title=title;
        _type=type;
    }
    public void onDoubleClick(){
        
    }
    public void onDoubleClick(IMainWinController controller){

    }
}
