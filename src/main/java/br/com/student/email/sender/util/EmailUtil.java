package br.com.student.email.sender.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class EmailUtil {
	/**
	 * Verifica se um e-mail possui uma sintaxe válida
	 * @param email
	 * @return true caso positivo ou false caso negativo
	 */
	public static boolean isAValidEmail(String email){
        if(StringUtils.isNotBlank(email)) {
        	String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;		
    }
}
