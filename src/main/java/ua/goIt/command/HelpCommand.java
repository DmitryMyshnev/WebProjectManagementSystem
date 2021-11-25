package ua.goIt.command;

public class HelpCommand implements Command {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    @Override
    public void execute(String... param) {
        System.out.println( """
        migration - run migration"
        all_salary: - get all salary from project. After ':' you need write name of project" );
        all_devFromPrj: -  get all developer from project. After ':' you need write name of project");
        dev_skills:  -  get all developer by skill. After ':' you need write name of skill");
        dev_level: -  get all developer by level. After ':' you need write name of level from this variant: Junior, Middle, Senior");
        all_projects - get all projects");
        create developer: - write data of developer in this format: name,age,(male or female),salary");
        update developer: - write new data of developer  in this format: name,age,(male or female),salary,id");
        delete developer: - write id of developer");
        read developer: - write id of developer");
        create project: -  write data of developer in this format: name,description,cost");
        update project: - write new data of developer  in this format: name,description,cost,id");
        delete project: - write id of project");
        read project: - write id of project");
        create customer: - write data of customer in this format: first_name,last_name");
        update customer: - write new data of customer  in this format: first_name,last_name,id");
        delete customer: - write id of customer");
        read customer: - write id of customer");
        create company: - write data of company in this format: name,quantity_employee");
        update company: - write new data of company  in this format: name,quantity_employee,id");
        delete company: - write id of company");
        read company: - write id of company");
        create skills: - write data of skills in this format: language");
        update skills: - write new data of skills  in this format: language,id");
        delete skills: - write id of skills");
        read skills : - write id of skills");
""");
    }
}
