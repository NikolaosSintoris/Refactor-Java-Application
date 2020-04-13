package view;



import javax.swing.JFrame;
import javax.swing.JRadioButton;

import controller.LatexEditorController;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ChooseTemplate {
	private JFrame frame;
	private LatexEditorController latexEditorController;
	private String previousWindow;

	public ChooseTemplate(LatexEditorController latexEditorController, String previousWindow) {
		this.latexEditorController = latexEditorController;
		this.previousWindow = previousWindow;
		initialize();
		frame.setVisible(true);
	}

	private void diselectRadioButtons(JRadioButton radioButton1, JRadioButton radioButton2, 
									    JRadioButton radioButton3,JRadioButton radioButton4) {
		if(radioButton1.isSelected()) {
			radioButton2.setSelected(false);
			radioButton3.setSelected(false);
			radioButton4.setSelected(false);
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton bookJRadioButton = new JRadioButton("Book");
		JRadioButton articleJRadioButton = new JRadioButton("Article");
		JRadioButton reportJRadioButton = new JRadioButton("Report");
		JRadioButton letterJRadioButton = new JRadioButton("Letter");
		
		bookJRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(bookJRadioButton, articleJRadioButton, reportJRadioButton, letterJRadioButton);
			}
		});
		bookJRadioButton.setBounds(42, 51, 127, 25);
		frame.getContentPane().add(bookJRadioButton);
		
		JLabel chooseTemplateLabel = new JLabel("Choose template. (Leave empty for blank document)");
		chooseTemplateLabel.setBounds(42, 13, 332, 16);
		frame.getContentPane().add(chooseTemplateLabel);
		
		articleJRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(articleJRadioButton, bookJRadioButton, reportJRadioButton, letterJRadioButton);
			}
		});
		articleJRadioButton.setBounds(42, 137, 127, 25);
		frame.getContentPane().add(articleJRadioButton);
		
		reportJRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(reportJRadioButton, articleJRadioButton, bookJRadioButton, letterJRadioButton);	
			}
		});
		reportJRadioButton.setBounds(213, 51, 127, 25);
		frame.getContentPane().add(reportJRadioButton);
		
		letterJRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons( letterJRadioButton, reportJRadioButton, articleJRadioButton, bookJRadioButton);
			}
		});
		letterJRadioButton.setBounds(213, 137, 127, 25);
		frame.getContentPane().add(letterJRadioButton);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bookJRadioButton.isSelected()) {
					latexEditorController.getCurrentDocument().setDocumentType("bookTemplate");
				}
				else if(reportJRadioButton.isSelected()) {
					latexEditorController.getCurrentDocument().setDocumentType("reportTemplate");
				}
				else if(articleJRadioButton.isSelected()) {
					latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
				}
				else if(letterJRadioButton.isSelected()) {
					latexEditorController.getCurrentDocument().setDocumentType("letterTemplate");
				}
				else {
					latexEditorController.getCurrentDocument().setDocumentType("emptyTemplate");
				}
				try {
					latexEditorController.enact("create");
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					LatexEditorView latexEditorView = new LatexEditorView(latexEditorController);
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				frame.dispose();
			}
		});
		createButton.setBounds(213, 196, 97, 25);
		frame.getContentPane().add(createButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(previousWindow.equals("main")) {
					try {
						LatexEditorView latexEditorView = new LatexEditorView(latexEditorController);
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					frame.dispose();
				}
				else {
					try {
						OpeningWindow openingWindow = new OpeningWindow();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					frame.dispose();
				}
			}
		});
		backButton.setBounds(46, 196, 97, 25);
		frame.getContentPane().add(backButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
