package com.example.crypticlibexample.bungee;

import crypticlib.chat.BungeeMsgSender;
import crypticlib.command.BungeeCommand;
import crypticlib.command.BungeeSubcommand;
import crypticlib.command.CommandInfo;
import crypticlib.command.annotation.Command;
import crypticlib.command.annotation.Subcommand;
import crypticlib.perm.PermInfo;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Command
public class ExampleCommand extends BungeeCommand {

    public ExampleCommand() {
        super(new CommandInfo("test", new PermInfo("test.test"), List.of("tt")));
    }

    @Subcommand
    BungeeSubcommand reload = new BungeeSubcommand("reload", new PermInfo("test.test.reload")) {
        @Override
        public void execute(@NotNull CommandSender sender, @NotNull List<String> args) {
            Example.INSTANCE.reloadPlugin();
            BungeeMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test.value());
        }
        @Subcommand
        BungeeSubcommand test = new BungeeSubcommand("test2") {
            @Override
            public void execute(@NotNull CommandSender sender, @NotNull List<String> args) {
                BungeeMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test2.value().toString());
            }
        };
    };


}
