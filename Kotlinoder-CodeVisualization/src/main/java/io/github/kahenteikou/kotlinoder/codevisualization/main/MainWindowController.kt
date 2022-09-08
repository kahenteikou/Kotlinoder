package io.github.kahenteikou.kotlinoder.codevisualization.main

import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS
import io.github.kahenteikou.kotlinoder.codevisualization.main.treewraps.TreeWrappedItem
import io.github.kahenteikou.kotlinoder.instrumentation.KotlinVisualizationTransformationVisit
import io.github.kahenteikou.kotlinoder.instrumentation.KotlinVisualizationTransformationVisitEx
import io.github.kahenteikou.kotlinoder.instrumentation.Scope
import io.github.kahenteikou.kotlinoder.instrumentation.UIBinding
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TextArea
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.layout.FlowPane
import javafx.stage.FileChooser
import ktast.ast.psi.Parser
import org.apache.logging.log4j.LogManager
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

class MainWindowController : Initializable {
    @FXML
    lateinit var filetreePane:FlowPane
    private var filetreeitems:MutableList<TreeItem<TreeWrappedItem>> = ArrayList()
    lateinit private var treeViewFile:TreeView<TreeWrappedItem>
    @FXML
    lateinit var mainTabPane: TabPane
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        LogManager.getLogger("Launcher").info("Start!")
        filetreeitems.add(TreeItem(TreeWrappedItem("root",TreeWrappedItem.TreeWrappedItemType.ROOTNODE)))
        treeViewFile=TreeView<TreeWrappedItem>(filetreeitems.first())
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
        var currentFileitem=TreeItem(f.name)
        filetreeitems.first().children.add(currentFileitem)

        //UIBinding.scopes.clear();
        var editorkun:TextArea=TextArea()
        editorkun.text=String(Files.readAllBytes(Paths.get(f!!.absolutePath)),Charsets.UTF_8)
        var filekun= Parser.parseFile(editorkun.text!!)
        currentFileitem.isExpanded=true
        var extraretkuns:MutableMap<String,MutableList<Scope>> = HashMap()
        KotlinVisualizationTransformationVisitEx(filekun,extraretkuns)
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

}