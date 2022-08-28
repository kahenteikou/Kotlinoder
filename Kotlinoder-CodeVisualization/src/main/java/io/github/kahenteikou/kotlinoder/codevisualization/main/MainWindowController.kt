package io.github.kahenteikou.kotlinoder.codevisualization.main

import io.github.kahenteikou.kotlinoder.codevisualization.main.tabs.STUBCLS
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.TabPane
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.layout.FlowPane
import javafx.stage.FileChooser
import org.apache.logging.log4j.LogManager
import java.io.File
import java.net.URL
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

class MainWindowController : Initializable {
    @FXML
    lateinit var filetreePane:FlowPane
    private var filetreeitems:MutableList<TreeItem<String>> = ArrayList()
    lateinit private var treeViewFile:TreeView<String>
    @FXML
    lateinit var mainTabPane: TabPane
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        LogManager.getLogger("Launcher").info("Start!")
        filetreeitems.add(TreeItem("root"))
        treeViewFile=TreeView<String>(filetreeitems.first())
        filetreePane.children.add(treeViewFile)
        var tabClsLoader:FXMLLoader= FXMLLoader(STUBCLS().javaClass.getResource("ClassEditorTab.fxml"))
        try{
            tabClsLoader.load()
        }
        catch(e:Exception){
            LogManager.getLogger("Launcher").error("Error loading ClassEditorTab.fxml")
        }
        mainTabPane.tabs.add(tabClsLoader.getRoot())
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
        filetreeitems.first().children.add(TreeItem(f.name))
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
                filetreeitems.clear()
                add_File(fkun)
            }
        }catch (e:Exception){
            LogManager.getLogger("Launcher").error("Error loading file",e)
        }
    }

}