package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

public class TreeWrappedItem {
    private String _title;
    private TreeWrappedItem _type;
    public String getTitle(){
        return _title;
    }
    public TreeWrappedItem getType(){
        return _type;
    }
    public void setTitle(String title){
        _title = title;
    }
    public void setType(TreeWrappedItem type){
        _type=type;
    }
    public enum TreeWrappedItemType{
        CLASS,
        METHOD,
        FILE,
        DIRECTORY,
        FIELD
    }

    @Override
    public String toString() {
        return _title;
    }
    public TreeWrappedItem(String title, TreeWrappedItem type){
        _title=title;
        _type=type;
    }
}
