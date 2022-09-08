package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;


import java.io.File;

public class FileTreeWrappedItem extends TreeWrappedItem {

    public FileTreeWrappedItem(String title, TreeWrappedItemType type) {
        super(title, type);
    }
    public FileTreeWrappedItem(File _f){
        super(_f.getName(),TreeWrappedItemType.FILE);
        _file=_f;
    }
    private File _file;
    public File getFile(){
        return _file;
    }
    public void setFile(File f){
        _file=f;
    }
}
