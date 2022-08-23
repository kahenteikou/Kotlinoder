package io.github.kahenteikou.kotlinoder.codevisualization.main

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TreeView
import javafx.stage.FileChooser
import org.apache.logging.log4j.LogManager
import java.io.File
import java.net.URL
import java.nio.file.Paths
import java.util.*

class MainWindowController : Initializable {
    @FXML
    lateinit var fileClassTreeView:TreeView<String>
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        LogManager.getLogger("Launcher").info("Start!")

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
                add_File(fkun)
            }
        }catch (e:Exception){
            LogManager.getLogger("Launcher").error("Error loading file",e)
        }
    }

}