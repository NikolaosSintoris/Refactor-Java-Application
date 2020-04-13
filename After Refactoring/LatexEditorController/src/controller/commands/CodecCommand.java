package controller.commands;
public abstract class CodecCommand implements Command{
	static final int ENCRYPTION = 1;
	static final int DECRYPTION = -1;
	static final int ASCCI_OF_A = 65;
	static final int ASCCI_OF_Z = 90;	
	static final int ASCCI_OF_a = 97;
	static final int ASCCI_OF_z = 122;
	static final int ROT_13_KEY = 13;
	
	public char[] atbashCodec(char[] contentsCharArray) {
		for(int i = 0; i < contentsCharArray.length; i++) {
			int asciiOfCharacter = (int) contentsCharArray[i];
			if ((asciiOfCharacter >= ASCCI_OF_A) && (asciiOfCharacter <= ASCCI_OF_Z)){
				contentsCharArray[i] = (char) (ASCCI_OF_A + ASCCI_OF_Z - asciiOfCharacter);
			}
			if ((asciiOfCharacter >= ASCCI_OF_a) && (asciiOfCharacter <= ASCCI_OF_z)){
				contentsCharArray[i] = (char) (ASCCI_OF_a + ASCCI_OF_z - asciiOfCharacter);
			}
		}
		return contentsCharArray;
	}
	
	public char[] rot13Codec(char[] contentsCharArray, int mode) {
		int encryptionKey = mode * ROT_13_KEY;
		System.out.println(encryptionKey);
		for(int i = 0; i < contentsCharArray.length; i++) {
			int asciiOfCharacter = (int) contentsCharArray[i];
			int shift = asciiOfCharacter + encryptionKey;
			if (checkIfUpperCaseAtoZ(asciiOfCharacter)){
				if(mode == ENCRYPTION) {
					if (shift > ASCCI_OF_Z) {
						contentsCharArray[i] = (char) (((shift - ASCCI_OF_Z) + ASCCI_OF_A) - 1);
					}else {
						contentsCharArray[i] = (char) shift;
					}
				}else if(mode == DECRYPTION){
					if (shift < ASCCI_OF_A) {
						contentsCharArray[i] = (char) ((ASCCI_OF_Z - (ASCCI_OF_A - shift)) + 1);
					}else {
						contentsCharArray[i] = (char) shift;
					}	
				}
			}
			if (checkIfLowerCaseAtoZ(asciiOfCharacter)){
				if(mode == ENCRYPTION) { 
					if (shift > ASCCI_OF_z) {
						contentsCharArray[i] = (char) (((shift - ASCCI_OF_z) + ASCCI_OF_a) - 1);
					}else {
						contentsCharArray[i] = (char) shift;
					}
				}else if(mode == DECRYPTION){ 
					if (shift < ASCCI_OF_a) {
						contentsCharArray[i] = (char) ((ASCCI_OF_z - (ASCCI_OF_a - shift)) + 1);
					}else {
						contentsCharArray[i] = (char) shift;
					}
				}
			}
		}
		return contentsCharArray;
	}
	
	public boolean checkIfUpperCaseAtoZ(int asciiOfCharacter) {
		return ((asciiOfCharacter >= ASCCI_OF_A) && (asciiOfCharacter <= ASCCI_OF_Z));
	}
	
	public boolean checkIfLowerCaseAtoZ(int asciiOfCharacter) {
		return ((asciiOfCharacter >= ASCCI_OF_a) && (asciiOfCharacter <= ASCCI_OF_z));
	}
}
