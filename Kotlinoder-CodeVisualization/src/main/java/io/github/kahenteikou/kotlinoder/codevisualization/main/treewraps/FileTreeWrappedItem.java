package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;


import java.io.File;

public class FileTreeWrappedItem extends TreeWrappedItem {

    public FileTreeWrappedItem(String title, TreeWrappedItem type) {
        super(title, type);
    }
    private File _file;
    public File getFile(){
        return _file;
    }
    public void setFile(File f){
        _file=f;
    }
}
