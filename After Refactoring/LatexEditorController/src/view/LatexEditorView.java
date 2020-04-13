package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import controller.LatexEditorController;
import model.Document;

public class LatexEditorView {
	static final int ATBASH_KEY = 25;
	static final int ROT_13_KEY = 13;
	private LatexEditorController latexEditorController;
	private String typedText;
	private String selectedVersionStrategy;
	private JFrame frame;
	private JEditorPane editorPane = new JEditorPane();
	private String selectedCommandName;
	private int encryptionKey;

	
	public LatexEditorView(LatexEditorController latexEditorController) throws IOException {
		this.latexEditorController = latexEditorController;
		latexEditorController.setLatexEditorView(this);
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 823, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 805, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem newFileMenu = new JMenuItem("New file");
		newFileMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseTemplate chooseTemplate = new ChooseTemplate(latexEditorController, "main");
				frame.dispose();
			}
		});
		fileMenu.add(newFileMenu);
		
		JMenuItem saveMenu = new JMenuItem("Save");
		saveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typedText = editorPane.getText();
				try {
					latexEditorController.enact("edit");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		fileMenu.add(saveMenu);
		JMenuItem addChapterMenuItem = new JMenuItem("Add chapter");
		JMenu commandsMenu = new JMenu("Commands");
		JMenuItem loadFileMenuItem = new JMenuItem("Load file");
		loadFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					latexEditorController.setFilename(filename);
					try {
						latexEditorController.enact("load");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					commandsMenu.setEnabled(true);
					addChapterMenuItem.setEnabled(true);
					if(latexEditorController.getCurrentDocument().getDocumentType().equals("letterTemplate")) {
						commandsMenu.setEnabled(false);
					}
					if(latexEditorController.getCurrentDocument().getDocumentType().equals("articleTemplate")) {
						addChapterMenuItem.setEnabled(false);
					}
					editorPane.setText(latexEditorController.getCurrentDocument().getContents());
				}
			}
		});
		fileMenu.add(loadFileMenuItem);
		
		JMenuItem saveMenuFile = new JMenuItem("Save file");
		saveMenuFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename + ".tex";
					}
					latexEditorController.setFilename(filename);
					try {
						latexEditorController.enact("save");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		fileMenu.add(saveMenuFile);
		
		JMenu encryptedFileMenu = new JMenu("Encrypted file");
		fileMenu.add(encryptedFileMenu);
		
		JMenuItem loadEncryptedFileMenuItem = new JMenuItem("Load encrypted file");
		loadEncryptedFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					latexEditorController.setFilename(filename);
					try {
						latexEditorController.enact("loadEncrypted");
					} catch (IOException e) {
						e.printStackTrace();
					}
					commandsMenu.setEnabled(true);
					addChapterMenuItem.setEnabled(true);
					if(latexEditorController.getCurrentDocument().getDocumentType().equals("letterTemplate")) {
						commandsMenu.setEnabled(false);
					}
					if(latexEditorController.getCurrentDocument().getDocumentType().equals("articleTemplate")) {
						addChapterMenuItem.setEnabled(false);
					}
					editorPane.setText(latexEditorController.getCurrentDocument().getContents());
				}
			}
		});
		encryptedFileMenu.add(loadEncryptedFileMenuItem);
		
		JMenu saveEncryptedMenu = new JMenu("Save file as encrypted");
		encryptedFileMenu.add(saveEncryptedMenu);
		
		JMenuItem atbashMenuItem = new JMenuItem("Atbash");
		atbashMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename + ".tex";
					}
					latexEditorController.setFilename(filename);
					encryptionKey = ATBASH_KEY;
					try {
						latexEditorController.enact("saveEncrypted");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		saveEncryptedMenu.add(atbashMenuItem);
		
		JMenuItem rot13MenuItem = new JMenuItem("Rot-13");
		rot13MenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				int option = filechooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename+".tex";
					}
					latexEditorController.setFilename(filename);
					encryptionKey = ROT_13_KEY;
					try {
						latexEditorController.enact("saveEncrypted");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		saveEncryptedMenu.add(rot13MenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		menuBar.add(commandsMenu);
		if(latexEditorController.getCurrentDocument().getDocumentType().equals("letterTemplate")) {
			commandsMenu.setEnabled(false);
		}
		
		addChapterMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "chapter";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		commandsMenu.add(addChapterMenuItem);
		if(latexEditorController.getCurrentDocument().getDocumentType().equals("articleTemplate")) {
			addChapterMenuItem.setEnabled(false);
		}
		
		JMenu addSection = new JMenu("Add Section");
		commandsMenu.add(addSection);
		
		JMenuItem addSectionMenuItem = new JMenuItem("Add section");
		addSectionMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "section";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addSection.add(addSectionMenuItem);
		
		JMenuItem addSubSectionMenuItem = new JMenuItem("Add subsection");
		addSubSectionMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "subsection";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addSection.add(addSubSectionMenuItem);
		
		JMenuItem addSubsubsectionMenuItem = new JMenuItem("Add subsubsection");
		addSubsubsectionMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "subsubsection";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addSection.add(addSubsubsectionMenuItem);
		
		JMenu addEnumerationListMenu = new JMenu("Add enumeration list");
		commandsMenu.add(addEnumerationListMenu);
		
		JMenuItem addItemizeMenuItem = new JMenuItem("Itemize");
		addItemizeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "itemize";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addEnumerationListMenu.add(addItemizeMenuItem);
		
		JMenuItem addEnumerateMenuItem = new JMenuItem("Enumerate");
		addEnumerateMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "enumerate";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addEnumerationListMenu.add(addEnumerateMenuItem);
		
		JMenuItem addTableMenuItem = new JMenuItem("Add table");
		addTableMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "table";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		commandsMenu.add(addTableMenuItem);
		
		JMenuItem addFigureMenuItem = new JMenuItem("Add figure");
		addFigureMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCommandName = "figure";
				try {
					latexEditorController.enact("addLatex");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		commandsMenu.add(addFigureMenuItem);
		
		JMenu strategyMenu = new JMenu("Strategy");
		menuBar.add(strategyMenu);
		
		JMenu enableMenu = new JMenu("Enable");
		strategyMenu.add(enableMenu);
		
		JCheckBoxMenuItem volatileCheckBoxMenuItem = new JCheckBoxMenuItem("Volatile");
		JCheckBoxMenuItem stableCheckBoxMenuItem = new JCheckBoxMenuItem("Stable");
		stableCheckBoxMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedVersionStrategy = "stable";
				if(latexEditorController.getVersionsManager().isEnabled() == false) {
					try {
						latexEditorController.enact("enableVersionsManagement");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					try {
						latexEditorController.enact("changeVersionsStrategy");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				volatileCheckBoxMenuItem.setSelected(false);
				stableCheckBoxMenuItem.setEnabled(false);
				volatileCheckBoxMenuItem.setEnabled(true);
			}
		});

		volatileCheckBoxMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedVersionStrategy = "volatile";
				if(latexEditorController.getVersionsManager().isEnabled() == false) {
					try {
						latexEditorController.enact("enableVersionsManagement");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					try {
						latexEditorController.enact("changeVersionsStrategy");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				stableCheckBoxMenuItem.setSelected(false);
				volatileCheckBoxMenuItem.setEnabled(false);
				stableCheckBoxMenuItem.setEnabled(true);
			}
		});
		enableMenu.add(volatileCheckBoxMenuItem);
		enableMenu.add(stableCheckBoxMenuItem);
		
		JMenuItem disableMenuItem = new JMenuItem("Disable");
		disableMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					latexEditorController.enact("disableVersionsManagement");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		strategyMenu.add(disableMenuItem);
		
		JMenuItem rollBackMenuItem = new JMenuItem("Rollback");
		rollBackMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					latexEditorController.enact("rollbackToPreviousVersion");
				} catch (IOException e) {
					e.printStackTrace();
				}
				Document document = latexEditorController.getCurrentDocument();
				editorPane.setText(document.getContents());
			}
		});
		strategyMenu.add(rollBackMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 783, 467);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(editorPane);
		editorPane.setText(latexEditorController.getCurrentDocument().getContents());
	}
	
	public String getStrategy() {
		return selectedVersionStrategy;
	}
	
	public void setStrategy(String selectedVersionStrategy) {
		this.selectedVersionStrategy = selectedVersionStrategy;
	}
	
	public String getTypedText() {
		return typedText;
	}
	
	public void setTypedText(String text) {
		this.typedText = text;
	}
	
	public JEditorPane getEditorPane() {
		return editorPane;
	}
	
	public String getSelectedCommandName() {
		return selectedCommandName;
	}
	
	public int getEncryptionKey(){
		return encryptionKey;
	}
	
	public void setEncryptionKey(int encryptionKey){
		this.encryptionKey = encryptionKey;
	}
}
