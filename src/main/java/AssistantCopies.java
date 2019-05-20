import base.SheetsQuickstart;

import java.io.IOException;
import java.security.GeneralSecurityException;

class AssistantCopies {
    private static SheetsQuickstart sheets;

    public static void main(String[] args) {
        sheets = new SheetsQuickstart();

        try {
            sheets.authenticate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
