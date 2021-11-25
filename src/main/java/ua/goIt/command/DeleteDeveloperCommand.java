package ua.goIt.command;

import java.util.regex.Pattern;

public class DeleteDeveloperCommand implements Command{
    private final Pattern namePattern;
    private final Pattern idPattern;

    public DeleteDeveloperCommand() {
        namePattern = Pattern.compile("^[a-zA-Z]+\\s*$");
        idPattern = Pattern.compile("^\\d+\\s*$");
    }

    @Override
    public void execute(String... param) {
     /*   Matcher matcher = namePattern.matcher(param);
        if (matcher.find()){
            List<Developer> listOfDeveloper = DeveloperService.findByName(param.trim());
            if(listOfDeveloper.isEmpty()){
                System.out.println("Not found name '" + param + "'");
                return;
            }
            if(listOfDeveloper.size() > 1 ){
                System.out.println("Found several developers with name '" + param + "'." +
                        "\nChoose developer from this list and write command 'remove_developer:' with  his id ");
                listOfDeveloper.forEach(dev->{
                    System.out.println("id: " + dev.getId() + " | " + dev);
                });
            }else {
                listOfDeveloper.stream().findFirst().ifPresent(dev->{
                    Developer developer = new Developer();
                    developer.setId(dev.getId());
                    DeveloperService.remove(developer);
                    System.out.println("Susses! Developer was deleted");
                });
            }
        }else{
            matcher = idPattern.matcher(param);
            if(matcher.find()){
                Developer developer = new Developer();
                developer.setId(Long.parseLong(param.trim()));
               DeveloperService.remove(developer);
                System.out.println("Susses! Developer was deleted ");
            }else {
                System.out.println("Mistake in id '" + param + "'");
            }
        }*/
    }
}
