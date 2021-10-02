package com.example.FinalProject.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("addToReceipt", new CreateReceiptCommand());
        commands.put("calculate", new CalculateParcelCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("createParcel", new CreateParcelCommand());
        commands.put("findParcels", new FindUserParcelCommand());
        commands.put("modifyParcel", new ModifyParcelCommand());
        commands.put("createReceipt", new CreateReceiptCommand());
        commands.put("findReceipts", new FindUserReceiptCommand());
        commands.put("payReceipt", new PayReceiptCommand());
        commands.put("providePayReceipt", new ProvidePayReceiptCommand());
        commands.put("modifyReceipt", new ModifyReceiptCommand());
        commands.put("setLang", new SetLanguageCommand());
    }
    public static Command   getCommand(String commandName) {
        return commands.get(commandName);
    }
}
