package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

public class TreeWrappedItem {
    private String _title;
    private Object _data;
    public enum TreeWrappedItemType{
        CLASS,
        METHOD,
        FILE,
        DIRECTORY,
        FIELD
    }
}
