package ua.goIt.services.consoleService;

import java.util.regex.Pattern;

public class ValidatePattern {
    public static final Pattern DEVELOPER_SAVE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+),\\s*(\\w+),\\s*(\\w+)\\s*$");
    public static final Pattern DEVELOPER_UPDATE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+),\\s*(\\w+),\\s*(\\w+),\\s*(\\w+)\\s*$");
    public static final Pattern PROJECT_SAVE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+\\s*)+,\\s*(\\w+)\\s*$");
    public static final Pattern PROJECT_UPDATE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+),\\s*(\\w+),\\s*(\\w+)\\s*$");
    public static final Pattern CUSTOMER_SAVE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+)\\s*$");
    public static final Pattern CUSTOMER_UPDATE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+),\\s*(\\w+)\\s*$");
    public static final Pattern COMPANY_SAVE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+)\\s*$");
    public static final Pattern COMPANY_UPDATE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\w+),\\s*(\\w+)\\s*$");
    public static final Pattern SKILLS_UPDATE_PATTERN = Pattern.compile("^(\\w+),\\s*(\\d+)\\s*$");

    public static final Pattern NAME_PATTERN = Pattern.compile("^([a-zA-Z]+\\s*)+$");
    public static final Pattern AGE_PATTERN = Pattern.compile("(\\d{1,2})");
    public static final Pattern GENDER_PATTERN = Pattern.compile("(male|female)");
    public static final Pattern DIGITAL_PATTERN = Pattern.compile("^(\\d+)$");

}
