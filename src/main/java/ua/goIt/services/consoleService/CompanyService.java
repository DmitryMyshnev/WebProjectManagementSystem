package ua.goIt.services.consoleService;

import ua.goIt.dao.CompanyDao;
import ua.goIt.model.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.goIt.services.consoleService.Validate.*;
import static ua.goIt.services.consoleService.ValidatePattern.*;


public class CompanyService implements CrudConsole {
    private final Company company;
    private static CompanyDao companyDao;
    public static CompanyService companyService;

    public CompanyService() {
        company = new Company();
        companyDao = new CompanyDao();
    }


    private boolean isValid(String param) {
        String[] arrayParam = param.split(",");
        if (!isValidByPattern(NAME_PATTERN, arrayParam[0])) {
            System.out.printf((NAME_ERROR) + "%n", arrayParam[0]);
            return false;
        }
        if (!isValidByPattern(DIGITAL_PATTERN, arrayParam[1])) {
            System.out.printf((DIGITAL_ERROR) + "%n", arrayParam[1]);
            return false;
        }
        return true;
    }

    @Override
    public void save(String arg) {
        if (!isValidByPattern(COMPANY_SAVE_PATTERN, arg)) {
            System.out.println(TEMPLATE_ERROR);
        }else
        if (isValid(arg)) {
            prepareInstance(arg);
            getDao().create(company);
            System.out.println("Company '" + company.getName() + "' was added.");
        }
    }

    @Override
    public void update(String arg) {
        if (!isValidByPattern(COMPANY_UPDATE_PATTERN, arg)) {
            System.out.println(TEMPLATE_ERROR);
            return;
        }
        if(!isValidByPattern(DIGITAL_PATTERN,arg.split(",")[2])){
            System.out.printf((DIGITAL_ERROR) + "%n",arg);
            return;
        }
        if (isValid(arg)) {
            prepareInstance(arg);
            getDao().update(company);
            System.out.println("Company '" + company.getName() + "' was updated.");
        }
    }

    @Override
    public void delete(String arg) {
        if(!isValidByPattern(DIGITAL_PATTERN,arg)){
            System.out.printf((DIGITAL_ERROR) + "%n",arg);
        }else {
            company.setId(Long.parseLong(arg));
            getDao().delete(company);
            System.out.println("Company  was deleted.");
        }
    }

    @Override
    public List<Object> getAll() {
        List<Company> all = getDao().getAll();
       all.forEach(System.out::println);
       return new ArrayList<>(all);
    }

    @Override
    public Optional<Object> findById(Long id) {
        Optional<Company> company = getDao().getById(id);
        company.ifPresent(System.out::println);
        if (company.isEmpty()) {
            return Optional.empty();
        } else
            return Optional.of(company.get());
    }
    public static CompanyDao getDao() {
        if (companyDao == null) {
            companyDao = new CompanyDao();
        }
        return companyDao;
    }

    public static CompanyService getInstance() {
        if (companyService == null) {
            companyService = new CompanyService();
        }
        return companyService;
    }
    private void prepareInstance(String data) {
        String[] fields = data.split(",");
        company.setName(fields[0]);
        company.setQuantityEmployee(Integer.parseInt(fields[1]));
        if(fields.length == 3){
            company.setId(Long.parseLong(fields[2]));
        }
    }
}
