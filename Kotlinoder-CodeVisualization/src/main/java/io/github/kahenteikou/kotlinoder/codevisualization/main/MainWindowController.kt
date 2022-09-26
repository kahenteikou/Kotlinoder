package io.github.kahenteikou.kotlinoder.codevisualization.main

import io.github.kahenteikou.kotlinoder.codevisualization.main.TreeitemWs.TreeItem_ClassTreeWrappedItem
import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS
import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.FileTreeWrappedItem
import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem
import io.github.kahenteikou.kotlinoder.instrumentation.*
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import javafx.stage.FileChooser
import javafx.util.Callback
import ktast.ast.psi.Parser
import org.apache.logging.log4j.LogManager
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

class MainWindowController : Initializable,IMainWinController {
    @FXML
    lateinit var filetreePane: AnchorPane
    private var filetreeitems:MutableList<TreeItem<TreeWrappedItem>> = ArrayList()
    lateinit private var treeViewFile:TreeView<TreeWrappedItem>
    @FXML
    lateinit var mainTabPane: TabPane
    private var tabitems_str:MutableList<String> = ArrayList()
    private fun changeProperty(dest:TreeWrappedItem){

    }
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        LogManager.getLogger("Launcher").info("Start!")
        filetreeitems.add(TreeItem(TreeWrappedItem("root",TreeWrappedItem.TreeWrappedItemType.ROOTNODE)))
        treeViewFile=TreeView<TreeWrappedItem>(filetreeitems.first())
        treeViewFile.minHeight=0.0
        treeViewFile.minWidth=0.0
        AnchorPane.setBottomAnchor(treeViewFile,0.0)
        AnchorPane.setLeftAnchor(treeViewFile,0.0)
        AnchorPane.setRightAnchor(treeViewFile,0.0)
        AnchorPane.setTopAnchor(treeViewFile,0.0)
        treeViewFile.selectionModel.selectedItemProperty().addListener { observableValue: ObservableValue<out TreeItem<TreeWrappedItem>?>?, oldValue: TreeItem<TreeWrappedItem>?, newValue: TreeItem<TreeWrappedItem>? ->
            run {
                System.out.println("E926")
                if (observableValue != null) {
                    if (observableValue.value != null) {
                        if (observableValue.value?.value != null) {
                            if (observableValue.value?.value?.type == TreeWrappedItem.TreeWrappedItemType.CLASS) {
                                println("CLASS")
                                //changeProperty(observableValue.value?.value!!)
                            }
                        }
                    }
                }
            }

        }
        treeViewFile.onMouseClicked=EventHandler(){
        if (it.clickCount==2){
                var itemkun=treeViewFile.selectionModel.selectedItem
                itemkun.value.onDoubleClick(this)
            }
        }
        filetreePane.children.add(treeViewFile)
        var tabClsLoader:FXMLLoader= FXMLLoader(STUBCLS().javaClass.getResource("ClassEditorTab.fxml"))
        try{
            tabClsLoader.load()
        }
        catch(e:Exception){
            LogManager.getLogger("Launcher").error("Error loading ClassEditorTab.fxml")
        }
        var tab:Tab=Tab("A2")
        tab.content=tabClsLoader.getRoot()
        mainTabPane.tabs.add(tab)
    }

    @FXML
    fun onLoadAction(e:ActionEvent){
        loadTextFile(null)
    }
    @FXML
    fun onSaveAction(e:ActionEvent){

    }
    @FXML
    fun onSaveAsAction(e:ActionEvent){

    }
    @FXML
    fun onCloseAction(e:ActionEvent){

    }
    @FXML
    fun onAboutAction(e:ActionEvent){

    }
    private fun add_File(f:File){
        var currentFileitem=TreeItem<TreeWrappedItem>(FileTreeWrappedItem(f))

        filetreeitems.first().children.add(currentFileitem)
        //UIBinding.scopes.clear();
        var editorkun:TextArea=TextArea()
        editorkun.text=String(Files.readAllBytes(Paths.get(f!!.absolutePath)),Charsets.UTF_8)
        var filekun= Parser.parseFile(editorkun.text!!)
        currentFileitem.isExpanded=true
        var extraretkuns:MutableMap<String,MutableList<Scope>> = HashMap()
        KotlinVisualizationTransformationVisitEx(filekun,extraretkuns)
        for((k,v) in extraretkuns){
            for(sc2 in v){
                if(sc2 is CompilationUnitDeclaration){
                    for(sc in sc2.getDeclaredClasses()) {
                        var clsNode = TreeItem_ClassTreeWrappedItem(sc as ClassDeclaration)
                        currentFileitem.children.add(clsNode)
                    }
                }
            }
        }
        filetreeitems.first().isExpanded=true

    }
    private fun loadTextFile(f: File?){
        try{
            if(f==null){
                var mdFilt: FileChooser.ExtensionFilter = FileChooser.ExtensionFilter("Kotlin files (*.kt)", "*.kt")
                var allFsFilt= FileChooser.ExtensionFilter("All files (*.*)", "*.*")
                var chooser= FileChooser()
                chooser.title="Open kotlin file"
                chooser.extensionFilters.add(mdFilt)
                chooser.extensionFilters.add(allFsFilt)
                chooser.initialDirectory= Paths.get("").toAbsolutePath().toFile()
                var fkun=chooser.showOpenDialog(null).absoluteFile
                LogManager.getLogger("Launcher").info(fkun.name)
                filetreeitems.first().children.clear()
                add_File(fkun)
            }
        }catch (e:Exception){
            LogManager.getLogger("Launcher").error("Error loading file",e)
        }
    }

    override fun add_tab(tab: Tab?,tab_id:String?) {
        if(tab!=null){
            if(tab_id!=null) {
                if (!tabitems_str.contains(tab_id)) {
                    tabitems_str.add(tab_id)
                    mainTabPane.tabs.add(tab)
                }
            }
        }
    }

}