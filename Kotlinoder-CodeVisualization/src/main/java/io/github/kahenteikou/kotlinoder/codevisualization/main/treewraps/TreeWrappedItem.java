package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

public class TreeWrappedItem {
    private String _title;
    private Object _data;
    private TreeWrappedItem _type;
    public String getTitle(){
        return _title;
    }
    public Object getData(){
        return _data;
    }
    public TreeWrappedItem getType(){
        return _type;
    }
    public void setTitle(String title){
        _title = title;
    }
    public void setData(Object dt){
        _data=dt;
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
    public TreeWrappedItem(String title, Object data, TreeWrappedItem type){
        _title=title;
        _data=data;
        _type=type;
    }
}
