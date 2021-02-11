package com.ngsoft.part1.IdNumber;

/**
 * Validating if a string is a proper id number is a requirenment of a lot of
 * controller level form posts. If you do not know how it works - a simple google search will suffice
 * Please don't try to copy code you find online though... If you can find it - so can I
 * Implement this properly and the IdNumberTest will pass.
 * Try to make youre implementation simple and readable.
 */
public class DefaultIdNumberValidator implements IdNumberValidator {
    @Override
    public boolean isValid(String idNumber) {
        return false;
    }
}
