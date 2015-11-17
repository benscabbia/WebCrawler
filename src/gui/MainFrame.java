package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import controller.Controller;

public class MainFrame extends JFrame{
	private MenuBar menuBar;
	private static TextPanel statusPanel;
	private static TextPanel contentPanel;
	private SearchPanel searchPanel;
	private static Controller controller;
	private static SearchPanelSingleSite searchPanelSingleSite;
	private static SearchPanelGoogle searchPanelGoogle;
	
	private JTabbedPane tabbedPaneSearchPanel, tabbedPaneContentPanel;
	
	public MainFrame(){
		super("Smart Mail");
		setLayout(new BorderLayout());
		
		menuBar = new MenuBar();
		setJMenuBar(menuBar.getMenuBar());
		
		tabbedPaneSearchPanel = new JTabbedPane();
		tabbedPaneContentPanel = new JTabbedPane();
		
		statusPanel = new TextPanel();
		contentPanel = new TextPanel();
		searchPanel = new SearchPanel();
		controller = new Controller();
		
		//testing
		searchPanelSingleSite = new SearchPanelSingleSite();
		searchPanelGoogle = new SearchPanelGoogle();
		
		//Search Panel listener
		searchPanelSingleSite.setFormListener(new FormListener(){
			public void formEventOccured(FormEvent e) {
				//String a = e.getSearchQuery();
				String a = e.getSearchW();
				//boolean b = e.isFullSearch();
				//System.out.println(a + b);
				//statusPanel.writeText(a + " " + b);
				controller.getSearch(e);
			}
			
		});
		
		add(statusPanel, BorderLayout.SOUTH); //layout 
		
		tabbedPaneContentPanel.add(contentPanel, "Text Format");
		//add(contentPanel, BorderLayout.CENTER); 
		add(tabbedPaneContentPanel, BorderLayout.CENTER);
		//add(searchPanel, BorderLayout.WEST);
		
		//tabbedPanel works!
		tabbedPaneSearchPanel.add(searchPanelSingleSite, "Single Site");
		tabbedPaneSearchPanel.add(searchPanelGoogle, "Google Search");
		
		//add(searchPanelNew, BorderLayout.WEST);
		add(tabbedPaneSearchPanel, BorderLayout.WEST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setMinimumSize(new Dimension(500,400));
		setVisible(true);
	}
	/*public void writeStatusText(String text){
		//statusPanel.writeText(text);
		System.out.println("Mainframe" + text);
	}*/
	public static Controller getControllerInstance(){
		return controller;
	}
	public static void writeStatusText(String text){
		statusPanel.writeText(text);
	}
	public static void writeContentText(String text){
		contentPanel.writeText(text);
	}
	public static void clearText(){
		contentPanel.clearText();
		statusPanel.clearText();
	}
	public static void toggleSearcHPanel(){
		searchPanelSingleSite.hidePanel();
	}
}
