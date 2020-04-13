package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;

class CreateCommandTest {
	private LatexEditorController latexEditorController;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private CreateCommand createCommand;
	
	public CreateCommandTest() throws IOException {
		documentManager = new DocumentManager();
		latexEditorController = new LatexEditorController();
		versionsManager = new VersionsManager(latexEditorController);
		createCommand = new CreateCommand(documentManager, versionsManager, latexEditorController);
	}
	@Test
	void test1() {
		
		latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
		
		createCommand.execute();
		String contents = "\\documentclass[11pt,twocolumn,a4paper]{article}\n"+
				"\\usepackage{graphicx}\n\n" +
				
				"\\begin{document}\n\n" +
				
				"\\title{Article: How to Structure a LaTeX Document}\n"+
				"\\author{Author1 \\and Author2 \\and ...}\n"+
				"\\date{\\today}\n\n"+

				"\\maketitle\n\n"+

				"\\section{Section Title 1}\n\n"+

				"\\section{Section Title 2}\n\n"+

				"\\section{Section Title.....}\n\n"+

				"\\section{Conclusion}\n\n"+

				"\\section*{References}\n\n"+

				"\\end{document}\n";
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		assertEquals(contents, actualContents);
	}

	@Test
	void test2() {
		latexEditorController.getCurrentDocument().setDocumentType("letterTemplate");
		createCommand.execute();
		String contents = "\\documentclass{letter}\n"+
				"\\usepackage{hyperref}\n"+
				"\\signature{Sender's Name}\n"+
				"\\address{Sender's address...}\n"+
				"\\begin{document}\n\n"+

				"\\begin{letter}{Destination address....}\n"+
				"\\opening{Dear Sir or Madam:}\n\n"+

				"I am writing to you .......\n\n\n"+


				"\\closing{Yours Faithfully,}\n\n"+

				"\\ps\n\n"+

				"P.S. text .....\n\n"+

				"\\encl{Copyright permission form}\n\n"+

				"\\end{letter}\n"+
				"\\end{document}\n";
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test3() {
		latexEditorController.getCurrentDocument().setDocumentType("reportTemplate");
		createCommand.execute();
		String contents = "\\documentclass[11pt,a4paper]{report}\n\n"+
		
				"\\usepackage{graphicx}\n\n"+
				
				"\\begin{document}\n\n"+ 
				
				"\\title{Report Template: How to Structure a LaTeX Document}\n"+
				"\\author{Author1 \\and Author2 \\and ...}\n"+
				"\\date{\\today}\n"+
				"\\maketitle\n\n"+

				"\\begin{abstract}\n"+
				"Your abstract goes here...\n"+
				"...\n"+
				"\\end{abstract}\n\n"+

				"\\chapter{First Chapter}\n\n"+
				
				"\\section{Section Title 1}\n"+
				"\\section{Section Title 2}\n"+
				"\\section{Section Title.....}\n\n"+

				"\\chapter{....}\n\n"+

				"\\chapter{Conclusion}\n\n\n"+


				"\\chapter*{References}\n\n"+

				"\\end{document}\n";
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	@Test
	void test4() {
		latexEditorController.getCurrentDocument().setDocumentType("bookTemplate");
		createCommand.execute();
		String contents = "\\documentclass[11pt,a4paper]{book}\n"+
				"\\usepackage{graphicx}\n\n"+
				
				"\\begin{document}\n\n"+
				
				"\\title{Book: How to Structure a LaTeX Document}\n"+
				"\\author{Author1 \\and Author2 \\and ...}\n"+
				"\\date{\\today}\n\n"+

				"\\maketitle\n\n"+

				"\\frontmatter\n\n"+

				"\\chapter{Preface}\n\n"+

				"\\mainmatter\n\n"+
				
				"\\chapter{First chapter}\n\n"+
				
				"\\section{Section Title 1}\n"+
				"\\section{Section Title 2}\n"+
				"\\section{Section Title.....}\n\n"+

				"\\chapter{....}\n\n"+

				"\\chapter{Conclusion}\n\n"+

				"\\chapter*{References}\n\n"+

				"\\backmatter\n\n"+
				
				"\\chapter{Last note}\n\n"+

				"\\end{document}\n";
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
	
	@Test
	void test5() {
		latexEditorController.getCurrentDocument().setDocumentType("emptyTemplate");
		createCommand.execute();
		String contents = "";
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
}
