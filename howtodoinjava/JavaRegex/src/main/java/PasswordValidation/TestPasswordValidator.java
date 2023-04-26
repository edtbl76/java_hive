package PasswordValidation;


import org.junit.Assert;
import org.junit.Test;

public class TestPasswordValidator {

    @Test
    public void testNormalPassword() {
        PasswordValidator validator = PasswordValidator.buildValidator(false, false, false, 6, 14);
        Assert.assertTrue(validator.validatePassword("howtodoinjava"));
        Assert.assertTrue(validator.validatePassword("howtodoin"));
        Assert.assertTrue(validator.validatePassword("howto"));
    }

    @Test
    public void testForceNumeric() {
        PasswordValidator validator  = PasswordValidator.buildValidator(false, false, true, 6, 16);
        Assert.assertTrue(validator.validatePassword("howtodoinjava12"));
        Assert.assertTrue(validator.validatePassword("34howtodoinjava"));
        Assert.assertTrue(validator.validatePassword("howtodo56injava"));
        Assert.assertTrue(validator.validatePassword("howtodoinjava"));
    }

    @Test
    public void testForceCapitalLetter() {
        PasswordValidator validator = PasswordValidator.buildValidator(false, true, false, 6, 16);
        Assert.assertTrue(validator.validatePassword("edwarD"));
        Assert.assertTrue(validator.validatePassword("eDWARd"));
        Assert.assertTrue(validator.validatePassword("Edward"));
        Assert.assertTrue(validator.validatePassword("edward"));
    }

    @Test
    public void testForceSpecialCharacter() {
        PasswordValidator validator = PasswordValidator.buildValidator(true, false,  false, 6, 16 );
        Assert.assertTrue(validator.validatePassword("edw@rd"));
        Assert.assertTrue(validator.validatePassword("#dward"));
        Assert.assertTrue(validator.validatePassword("edwar$%"));
        Assert.assertTrue(validator.validatePassword("edward"));
    }
}
