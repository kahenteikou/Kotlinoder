package io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps;

import io.github.kahenteikou.kotlinoder.instrumentation.Scope;
import javafx.scene.control.TextArea;
import ktast.ast.Node;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeFileWrapper {
    private String _title;
    private File _file;
    private TextArea _area;
    private Node.KotlinFile _ktFile;
    private Map<String,List<Scope>> _scopes;
    public TreeFileWrapper(String title, File file, TextArea area, Node.KotlinFile ktFile, Map<String, List<Scope>> scopes){
        _title = title;
        _file = file;
        _area = area;
        _ktFile = ktFile;
        _scopes = scopes;
    }
    public String getTitle(){
        return _title;
    }
    public File getFile(){
        return _file;
    }
    public TextArea getArea(){
        return _area;
    }
    public Node.KotlinFile getKtFile(){
        return _ktFile;
    }
    public Map<String, List<Scope>> getScopes(){
        return _scopes;
    }
    public void setTitle(String title){
        _title = title;
    }
    public void setFile(File file){
        _file = file;
    }
    public void setArea(TextArea area){
        _area = area;
    }
    public void setKtFile(Node.KotlinFile ktFile){
        _ktFile = ktFile;
    }
    public void setScopes(Map<String, List<Scope>> scopes){
        _scopes = scopes;
    }

    @Override
    public String toString() {
        return _title;
    }
}
