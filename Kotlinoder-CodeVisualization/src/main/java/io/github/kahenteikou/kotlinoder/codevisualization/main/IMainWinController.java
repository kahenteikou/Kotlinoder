package io.github.kahenteikou.kotlinoder.codevisualization.main;

import javafx.scene.control.Tab;

public interface IMainWinController {
    public void add_tab(Tab tab,String tab_id,boolean focus);
    public void add_tab(Tab tab,String tab_id);
}
