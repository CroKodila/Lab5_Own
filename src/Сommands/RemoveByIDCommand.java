package Сommands;

import Exceptions.InvalidValueException;
import Managers.CollectionManager;
import Managers.ConsoleManager;

public class RemoveByIDCommand extends Commands {
    public RemoveByIDCommand(){
        cmdName = "remove_by_id";
        argCount = 1;
    }

    @Override
    public void execute(ConsoleManager consoleManager, CollectionManager collectionManager, String[] args) {
        if (args.length < argCount) {
            throw new InvalidValueException("Введено " + args.length + " аргументов, ожидалось " + argCount);
        }

        long id;
        try {
            id = Long.parseLong(args[0]);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return;
        }

        if(!collectionManager.checkIdExist(id))
            throw new InvalidValueException("Такого id не существует");

        collectionManager.removebyID(id);
        consoleManager.print("Элемент " + id + " удален");
    }
}
