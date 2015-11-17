package gui;
//deprecated
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchPanel extends JPanel {
	private JLabel titleLabel, smartSearchLabel, fullScanLabel, websiteLabel;
	private JTextField smartSearchField, websiteField;
	private JCheckBox fullScanCheck;
	private JButton startButton;
	private FormListener formListener;
	
	public SearchPanel(){
		//set size for panel
		Dimension dim = getPreferredSize();
		dim.width = 200;
		setPreferredSize(dim);
		
		//create components
		titleLabel = new JLabel("Build up your Mailing List");
		websiteLabel = new JLabel("Searchw: ");
		websiteField = new JTextField(10);
		smartSearchLabel = new JLabel("Search: ");
		smartSearchField = new JTextField(10);
		fullScanLabel = new JLabel("Full Scan: ");
		fullScanCheck = new JCheckBox();
		fullScanCheck.setSelected(true);
		startButton = new JButton("Start Scan");
		
		//make fullscan off by default
		//fullScanLabel.setEnabled(false);
		//fullScanCheck.setEnabled(false);
		
		
		//listeners
		//dynamically checks if content is in both fields, if so, it clears the field
		smartSearchField.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent arg0) {
				changed();				
			}
			public void insertUpdate(DocumentEvent arg0) {
				changed();
			}
			public void removeUpdate(DocumentEvent arg0) {
				changed();
				
			}
			public void changed(){
				if(!smartSearchField.getText().isEmpty()){
					websiteField.setText("");
					websiteField.setEnabled(false);
				}
				else{
					websiteField.setEnabled(true);
				}
			}
		});

		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String searchQuery = smartSearchField.getText();
				String searchW = websiteField.getText(); 
				boolean fullSearch = fullScanCheck.isSelected();
				
				FormEvent ev = new FormEvent(this, searchW, searchQuery, fullSearch);
				if(formListener != null){
					formListener.formEventOccured(ev);
				}
			}
			
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Scan");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);		
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	private void layoutComponents(){
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		///////////first row /////////////////
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE; //if u want components to fill grid
		gc.anchor = GridBagConstraints.LINE_END; //specifies the side of cell that control sticks to
		gc.insets = new Insets(0, 0, 0, 5);
		add(websiteLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(websiteField,gc);
///////////next row ////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(smartSearchLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(smartSearchField, gc);

		///////////next row ////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(fullScanLabel, gc);
		
		gc.gridx = 1;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(fullScanCheck, gc);
		
		///////////Next row /////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(startButton, gc);		
	}
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

