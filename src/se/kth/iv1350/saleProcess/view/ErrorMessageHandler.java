package se.kth.iv1350.saleProcess.view;

/**
 * Manages error messages for the user interface.
 */
public class ErrorMessageHandler {

    /**
     * Displays the error message.
     *
     * @param exceptionMessage The error message.
     */

    void showErrorMessage(String exceptionMessage){
        String newRow = "\n";

        StringBuilder builder = new StringBuilder();
        builder.append("---User Interface---");
        builder.append(newRow);
        builder.append("Error: ");
        builder.append(exceptionMessage);
        builder.append(newRow);
        builder.append("--------------------");
        builder.append(newRow);
        System.out.println(builder);
    }
}
