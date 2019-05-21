package base;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class AssistantCopies {

    private static SheetsQuickstart sheets;

    public void main(String args[]){
        sheets = new SheetsQuickstart();

        try{
            sheets.authenticate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }
}
