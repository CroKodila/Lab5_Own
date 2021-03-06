package Managers;

import Exceptions.NoCommandException;
import Сommands.*;

import java.util.*;

public class CommandManager {
    private static CommandManager instance;

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    private Map<String, Commands> commands = new HashMap<>();

    public CommandManager(){
        addCommand(new AddElementCommand());
        addCommand(new HelpCommand());
        addCommand(new ExitCommand());
        addCommand(new ShowCommand());
        addCommand(new InfoCommand());
        addCommand(new InsertAtIndexCommand());
        addCommand(new RemoveByIDCommand());
        addCommand(new ClearCommand());
        addCommand(new GroupCountingByIDCommand());
        addCommand(new PrintAnnualTurnoverCommand());
        addCommand(new RemoveByPostalAdressCommand());
        addCommand(new RemoveGreaterCommand());
        addCommand(new RemoveLowerCommand());
        addCommand(new RemoveLowerCommand());
        addCommand(new SaveCommand());
        addCommand(new ExecuteScriptCommand());
        addCommand(new UpdateIDCommand());
    }

    private void addCommand(Commands cmd){
        commands.put(cmd.getCmdName(), cmd);
    }

    /**
     *
     * @param s
     * проверяет существование команды
     * @throws NoCommandException
     */
    public Commands getCommand(String s) throws NoCommandException {
        if (!commands.containsKey(s)) {
            throw new NoCommandException("Команда не найдена");
        }
        return commands.get(s);
    }

    /**
     * Выполняет команды введенные пользователем
     * @param str
     * @param consoleManager
     * @param collectionManager
     */
    public void execute(String str, ConsoleManager consoleManager, CollectionManager collectionManager) throws NoCommandException {
        String[] parse = str.trim().split(" ");
        if(!parse[0].equals("")) {
            Commands cmd = null;
                cmd = getCommand(parse[0].toLowerCase());

            //consoleManager.writeln("execution cmd: " + parse[0]);

            String[] args = Arrays.copyOfRange(parse, 1, parse.length);
            //consoleManager.writeln("args count: " + args.length);

            cmd.execute( consoleManager, collectionManager , args);
        }
    }


    public List<Commands> getAllCommands() {
        return new ArrayList<>(commands.values());
    }
}

