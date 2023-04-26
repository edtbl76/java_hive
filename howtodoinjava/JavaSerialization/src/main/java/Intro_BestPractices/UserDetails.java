package Intro_BestPractices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.time.LocalDate;

public class UserDetails implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;
    private String fFirstName;
    private String fLastName;
    private int fAccountNumber;
    private LocalDate fDateOpened;

    public UserDetails(String aFirstName, String aLastName, int aAccountNumber, LocalDate aDateOpened) {
        super();
        setFirstName(aFirstName);
        setLastName(aLastName);
        setAccountNumber(aAccountNumber);
        setDateOpened(aDateOpened);
    }

    public UserDetails() {
        this("FirstName", "LastName", 0, LocalDate.now());
    }

    public final String getFirstName() {
        return fFirstName;
    }

    public final String getLastName() {
        return fLastName;
    }

    public final int getAccountNumber() {
        return fAccountNumber;
    }

    public final LocalDate getDateOpened() {
        return fDateOpened;
    }

    public final void setFirstName(String aNewFirstName) {
        verifyNameProperty(aNewFirstName);
        fFirstName = aNewFirstName;
    }

    public final void setLastName(String aNewLastName) {
        verifyNameProperty(aNewLastName);
        fLastName = aNewLastName;
    }

    public final void setAccountNumber(int aNewAccountNumber) {
        validateAccountNumber(aNewAccountNumber);
        fAccountNumber = aNewAccountNumber;
    }

    public final void setDateOpened(LocalDate aNewDate) {
        LocalDate newDate = aNewDate;
        validateAccountOpenDate(newDate);
        fDateOpened = newDate;
    }

    private void verifyUserDetails() {
        validateAccountNumber(fAccountNumber);
        verifyNameProperty(fFirstName);
        verifyNameProperty(fLastName);
        validateAccountOpenDate(fDateOpened);
    }

    private void verifyNameProperty(String aName) {
        boolean hasContent = (aName != null) && (!aName.equals(""));
        if (!hasContent)
            throw new IllegalArgumentException("Names must be non-null and non-empty");

        StringCharacterIterator iterator = new StringCharacterIterator(aName);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE) {
            boolean isValidChar = (Character.isLetter(character) || Character.isSpaceChar(character)
                    || character == '\'');

            if (!isValidChar) {
                throw new IllegalArgumentException("Names can only contain letters, spaces and apostrophes");
            }
            character = iterator.next();
        }
    }

    private void validateAccountNumber(int aAccountNumber) {
        if (aAccountNumber < 0)
            throw new IllegalArgumentException("Account Number must be >= 0");
    }

    private void validateAccountOpenDate(LocalDate aDateOpened) {
        if (aDateOpened.getYear() < 1970) {
            throw new IllegalArgumentException("Date Opened mus tbe after 1970");
        }
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
        verifyUserDetails();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
    }
}
