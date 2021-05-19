package se.kth.iv1350.saleProcess.util;

import se.kth.iv1350.saleProcess.integration.DatabaseDownException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Logs error messages for the developers.
 */
public class ExceptionLogger {

    /**
     * Writes an entry in the exception log.
     * The exception log is printed to the screen.
     *
     * @param exception The exception that will be logged.
     */
    public void logError(Exception exception){
        String newRow = "\n";

        StringBuilder builder = new StringBuilder();
        builder.append("---Exception Log---");
        builder.append(newRow);
        builder.append(LocalDate.now());
        builder.append(newRow);
        builder.append(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
        builder.append(newRow);
        builder.append(exception.getMessage());
        builder.append(newRow);
        builder.append("-------------------");
        builder.append(newRow);
        System.out.println(builder);
    }

}
