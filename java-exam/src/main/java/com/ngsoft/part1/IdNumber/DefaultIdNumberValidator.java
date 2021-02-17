package com.ngsoft.part1.IdNumber;

import org.springframework.util.StringUtils;

import java.util.stream.IntStream;

/**
 * Validating if a string is a proper id number is a requirenment of a lot of
 * controller level form posts. If you do not know how it works - a simple google search will suffice
 * Please don't try to copy code you find online though... If you can find it - so can I
 * Implement this properly and the IdNumberTest will pass.
 * Try to make youre implementation simple and readable.
 */
public class DefaultIdNumberValidator implements IdNumberValidator {

    private static final int MAX_DIGITS = 9;

    @Override
    public boolean isValid(String idNumber) {
        if (StringUtils.isEmpty(idNumber) || !isLengthValid(idNumber)) {
            throw new IllegalArgumentException("The input should be digits of the ID number.");
        }
        if (!isDecimalDigits(idNumber)) {
            return false;
        }
        return isIdValid(idNumber);
    }

    private boolean isLengthValid(String idNumber) {
        return idNumber.length() <= MAX_DIGITS;
    }

    private boolean isDecimalDigits(String idNumber) {
        for (int i = 0; i < idNumber.length(); i++) {
            if (Character.digit(idNumber.charAt(i), 10) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isIdValid(String idNumber) {
        idNumber = normalizeIdNumber(idNumber);
        int sum = calculateDigitsSum(idNumber);
        return isControlDigitCorrect(idNumber, sum);
    }

    private String normalizeIdNumber(String idNumber) {
        StringBuilder sb = new StringBuilder(idNumber);
        int length = idNumber.length();
        if (length < MAX_DIGITS) {
            for (int i = 0; i < MAX_DIGITS - length; i++) {
                addPrefixDigit(sb);
            }
        }
        return sb.toString();
    }

    private void addPrefixDigit(StringBuilder sb) {
        sb.insert(0, (char) 0);
    }

    private int calculateDigitsSum(String idNumber) {
        char[] id = new char[idNumber.length() - 1];
        idNumber.getChars(0, idNumber.length() - 1, id, 0);
        return IntStream.range(1, id.length)
                .mapToObj(i -> calculateDigit(id[i], i))
                .mapToInt(i -> i)
                .sum();
    }

    private int calculateDigit(char ch, int i) {
        int digit = getDecimalDigit(ch);
        int mul = digit * (i % 2 + 1);
        return mul < 10 ? mul : (mul%10) + (mul/10);
        // When I looked at Wikipedia in order to know the control digit algorithm,
        // I saw another nice way: (mul <= 9) ? mul : mul - 9
    }

    private int getDecimalDigit(char ch) {
        return  Character.digit(ch, 10);
    }

    private boolean isControlDigitCorrect(String idNumber, int digitsSum) {
        return getDecimalDigit(idNumber.charAt(idNumber.length() - 1)) == calculateControlDigit(digitsSum);
    }

    private int calculateControlDigit(int sum) {
        return 10 - (sum % 10);
    }
}
