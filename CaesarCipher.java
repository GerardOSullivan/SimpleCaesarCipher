import javax.swing.*;

public class CaesarCipher {

    public static void main(String[] args) {
        getInputAndRunCaesar();
    }

    public static void getInputAndRunCaesar(){
        int shift = 0;
        String message;

        message = JOptionPane.showInputDialog(null,"Please enter the text you wish to encrypt","Message to Encrypt",JOptionPane.PLAIN_MESSAGE);

        while(message.matches(".*\\d.*") || message.equals("")){
            JOptionPane.showInputDialog(null,"Invalid message to be encrypted. Please try again","Error",JOptionPane.ERROR_MESSAGE);
            message = JOptionPane.showInputDialog(null,"Please enter the text you wish to encrypt","Message to Encrypt",JOptionPane.PLAIN_MESSAGE);
        }
        while(shift==0){
            try{
                shift = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter the the number of shift spaces","Number of shift spaces",JOptionPane.PLAIN_MESSAGE));
            }catch(NumberFormatException e){
                JOptionPane.showInputDialog(null,"Only Integers above 0 excepted. Please try again","Error",JOptionPane.ERROR_MESSAGE);
            }
        }


        JOptionPane.showMessageDialog(null,"Encrypted message: "+ encrypt(message,shift) +"\n" +
                "Original message: " + decrypt(encrypt(message,shift),shift),"Encrypted and Decrypted Message",JOptionPane.INFORMATION_MESSAGE);

    }

    public static String encrypt(String message, int shift){

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        message = message.toUpperCase();
        
        String encryptedMessage = "";
        int charPosition, keyValue;

        for(int i = 0; i < message.length(); i++){
            switch(message.charAt(i)){
                case ' ': encryptedMessage += ' ';
                          break;
                
                case '.': encryptedMessage += '.';
                          break;
                    
                case ',': encryptedMessage += ",";
                          break;
                    
                case '!': encryptedMessage += "!";
                          break;
                    
                case '?': encryptedMessage += "?";
                          break;
                default: charPosition = alphabet.indexOf(message.charAt(i));
                         keyValue = (shift + charPosition) % 26;

                         if(keyValue < 0){
                             keyValue = alphabet.length() + keyValue;
                         }
                         encryptedMessage += alphabet.charAt(keyValue);
                         break;
            }
        }

        return encryptedMessage;
    }

    public static String decrypt(String encryptedMessage, int shift){

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        encryptedMessage = encryptedMessage.toUpperCase();
        
        String originalMessage = "";
        int charPosition, keyValue;

        for(int i = 0; i < encryptedMessage.length(); i++){
            switch(encryptedMessage.charAt(i)){
                case ' ': originalMessage += ' ';
                          break;
                
                case '.': originalMessage += '.';
                          break;
                    
                case ',': originalMessage += ",";
                          break;
                    
                case '!': originalMessage += "!";
                          break;
                    
                case '?': originalMessage += "?";
                          break;
                default: charPosition = alphabet.indexOf(encryptedMessage.charAt(i));

                         keyValue = (charPosition - shift) % 26;

                         if(keyValue < 0){
                             keyValue = alphabet.length() + keyValue;
                         }

                         originalMessage += alphabet.charAt(keyValue);
                         break;
            }
        }
        return originalMessage;
    }
    
}
