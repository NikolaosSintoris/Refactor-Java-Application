package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import controller.LatexEditorController;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class OpeningWindow {
	private JFrame frame;
	private LatexEditorController latexEditorController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningWindow openingWindow = new OpeningWindow();
					openingWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OpeningWindow() throws IOException {
		this.latexEditorController = new LatexEditorController();
		latexEditorController.setVersionsManager(latexEditorController.getVersionsManager());
		initialize();
		frame.setVisible(true);
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton createNewDocumentButton = new JButton("Create New Document");
		createNewDocumentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseTemplate chooseTemplate = new ChooseTemplate(latexEditorController, "opening");
				frame.dispose();
			}
		});
		createNewDocumentButton.setBounds(109, 77, 215, 36);
		frame.getContentPane().add(createNewDocumentButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(109, 137, 215, 25);
		frame.getContentPane().add(exitButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
