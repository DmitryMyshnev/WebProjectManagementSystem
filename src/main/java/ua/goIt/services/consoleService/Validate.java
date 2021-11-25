package ua.goIt.services.consoleService;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
 public static final String TEMPLATE_ERROR = "Missing  one or more  arguments";
 public static final String NAME_ERROR = "Mistake in  name '%s'. Name need contain  only symbols a-z or A-Z";
 public static final String AGE_ERROR = "Mistake in  age '%s'. Age need contain  only symbols 0-9";
 public static final String LEVEL_ERROR = "Mistake in  level '%s'. Level need contain  only 'Junior','Middle' or 'Senior'";
 public static final String GENDER_ERROR = "Mistake in  gender '%s'. Gender need contain  only 'male' or 'female'";
 public static final String DIGITAL_ERROR = "Mistake in '%s'. Argument  need contain  only digital symbols";
    public static boolean isValidByPattern(Pattern pattern, String param) {
      Matcher matcher = pattern.matcher(param);
      return matcher.find();
    }

}
