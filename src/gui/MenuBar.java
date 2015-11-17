package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuBar extends JMenuBar {
	JMenuBar menuBar = new JMenuBar();
	public MenuBar(){
		

		JMenu fileMenu = new JMenu("File");
		
		JMenuItem newItem = new JMenuItem("New (To Do)");
		JMenuItem exportDataItem = new JMenuItem("Export Data (To Do)");
		JMenuItem importDataItem = new JMenuItem("Import Data (To Do)");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(newItem);
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Options (To Do)");

		final JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Search Form");
		showFormItem.setSelected(true);
		showFormItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//if(showFormItem.isSelected()){
				/*no longer working due to tabbed pane*/
					//MainFrame.toggleSearcHPanel();
			}
		});

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
	}
	public JMenuBar getMenuBar(){
		return menuBar;
	}
}
