package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class TextPanel extends JPanel{
	private JTextArea textArea;
	
	public TextPanel(){
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(0,100));
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		add(new JScrollPane(textArea));
	}
	public void writeText(String text){
		textArea.append(text);
	}
	public void clearText(){
		textArea.setText("");
	}
}
