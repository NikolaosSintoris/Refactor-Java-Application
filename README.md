# Refactor-Java-Application
Refactor an existing Latex Editor Project   

The goal of this project was to refactor and extend an existing Java application.   

Here are the refactoring problems followed by their solutions:

Issue #1:   
LatexEditorView class: This class is strange. Its name and responsibilities do not match. The view package contains classes that have to do with the GUI and the data visualization. LatexEditorView does not have anything to do with these. Instead, it contains application logic and specifically it has methods that realize some basic commands like saving a document to a file, loading a document from a file, creating a new version of the current document using the VersionsManager. An idea to solve this problem is to redistribute the responsibilities of the class to the right classes using the Move Method and the Move Field refactorings. In particular, the code that saves a document to a file (saveToFile()) can be moved to the SaveCommand class, the code that loads a document from a file (loadFromFile()) can be moved to the LoadCommand class. The code that creates versions (saveContents) can be moved to the EditCommand class or to the AddLatexCommand class. Along with the code you may need to change/move fields of LatexEditorView to other classes. Other methods and fields that possibly remain can be moved to the LatexEditorController class which is supposed to serve as middle layer that decouples the GUI from the model classes.

Solution #1:      
By using the Move Method and Move Field refactorings i moved:
ï‚· saveToFile() method and the corresponding fields to the SaveCommand class. Especially i merged the saveToFile() with the execute() method.
ï‚· loadFromFile() method and the corresponding fields to the LoadCommand class. Especially i merged the loadFromFile() with the execute() method.
ï‚· saveContents() method and the corresponding fields to the EditCommand and the AddLatexCommand classes. Especially i merged the saveContents() with the execute() method of each class.

Issue #2:   
MainWindow class: This is the main GUI class. However, this class also contains a Large Method that should be part of the application logic. Specifically, the editContents() method is a misplaced responsibility that can be moved to the AddLatexCommand class using the Move Method refactoring. The editContents() method also has a lot of Duplicate Code that can be removed.

Sollution #2:   
By using the Move Method and Move Field refactorings i moved the editContents() method and the corresponding fields to the AddLatexCommand class. Especially i merged the editContents () with the execute() method. I additionally refactored the contents of the method by storing the contents of each command in separate files in disk.

Issue #3:   
DocumentManager class: In this class i have Duplicate Code in the constructor. Another problem is Long Method. In particular, getContents() is a huge method that also has a clear Duplicate Code problem. An idea to deal with these issues is to use the Substitute Algorithm refactoring. Specifically, you can store the latex template names and their contents in a map class field and loop over the elements of the map to create the required Document objects. Another possibility is to have the templates as files in the disk and loop over the file contents to create the required Document objects.

Sollution #3:   
To deal with this issue, i created a DocumentTemplateNames.txt file that contains all the document template names. Then, in the constructor of the DocumentManager class i read the document template names from the file and with a for loop, we loop over the elements of the map to create the required Document objects.

Issue #4:   
VersionsManager class: This class has several problems. The simplest problem is Dead Code, methods that do nothing and are not used anywhere. Remove the useless methods. Although the class is not very big in terms of lines of code, it can be seen as a Large Class because it has many unrelated responsibilities. From the name of the class it is clear that its main responsibility if version management. However, it also has methods for saving and loading documents to/from files. Another problem that is evident is Message Chains. In particular, VersionsManager is a Middle Man with several methods (getType, saveToFile(), loadFromFile(), saveContents()....) that simply delegate invocations to corresponding methods of the LatexEditorView class. A typical way to get rid of these problems is to remove the delegating methods using the Remove Middle Man refactoring.

Sollution #4:   
Firstly, i removed the methods setPreviousVersion(), rollBackToPreviousVersion() to get rid of the Dead Code. Also, i simplified the responsibilities of the class and dealt with the Middle Man by removing the following methods: getType(), saveToFile(), loadFromFile(), saveContents(). I also simplified the enableStrategy() method and removed the enable() method as the two(2) of them were similar after the simplification.

Issue #5:    
Command classes: A problem that is easy to spot here is that the different classes that implement the Command interface have Duplicate Code. In some cases, this appears in the form of same fields between the classes. In other cases there are also common methods between the classes. An idea to solve the problem is to use refactorings like Extract Super Class or Inline Class.

Solution #5:    
To deal with this issue i used the Extract Super Class refactoring by creating an abstract class named EditContents that has as a method the common code between AddLatexCommand and EditCommand classes. Specifically, this method has the code that was previously located at the saveContents() method.

Issue #6:   
LatexEditorController class: A problem here is in the constructor of the class, which has a lot of Duplicate Code. The same commands are repeated to populate a HashMap. An idea to deal with this issue is to use the Substitute Algorithm refactoring. Specifically, you can store the string command names in a map class field and loop over the elements of the map to create the required command objects. Another possibility is to have the command names in a properties file in the disk and loop over the file contents to create the required Command objects.

Solution #6:   
To deal with this issue, i created a CommandObjects.txt file that contains all the LaTex command names. Then, in the constructor of the LaTexEditorController class i read the commands from the file and with a for loop we replaced the continuous command.put() operations with a single one.

After all this refactorings, i noticed that the LatexEditorView had most of the responsibilities of the LatexEditorController, so i merged them together under the LatexEditorcontrolller class. Also, before the merge, i moved the contents of the LatexEditorView that correspond with the View part of the application to the MainWindow class, wich was named as the â€œnewâ€ LatexEditorview class. Finally, i did the necessary chages in order the above refactorings become functional.

At last, i extend the application by adding new functionalities and respective tests.

Extention #1:   
As a user I want to save an encrypted form of the latest version of the Latex document on disk storage. The application should let me select a particular encryption strategy that is going to be used before
saving an encrypted form of the Latex document. In particular, the application should support at least the following strategies:
	* Atbash: The Atbash cipher is formed by taking the alphabet and mapping it to its reverse, so that the first letter becomes the last letter, the second letter becomes the second to last letter, and so on.
	* Rot-13: Rot-13 is a letter substitution cipher that replaces a letter with the 13th letter after it, in the alphabet. Rot-13 is a special case of the Caesar cipher, which was developed in ancient Rome.

Extention #2:   	
As a user I want to load an encrypted form the latest version of the Latex document from stable storage. The application should decrypt the document to enable further document editing actions.
