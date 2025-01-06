package com.example.crypticlibexample.bukkit;

import crypticlib.chat.BukkitMsgSender;
import crypticlib.command.BukkitCommand;
import crypticlib.command.BukkitSubcommand;
import crypticlib.command.CommandInfo;
import crypticlib.command.annotation.Command;
import crypticlib.command.annotation.Subcommand;
import crypticlib.perm.PermInfo;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Command
public class ExampleCommand extends BukkitCommand {

    public ExampleCommand() {
        super(new CommandInfo("test", new PermInfo("test.test"), List.of("tt")));
    }

    @Subcommand
    BukkitSubcommand reload = new BukkitSubcommand("reload", new PermInfo("test.test.reload")) {
        @Override
        public void execute(@NotNull CommandSender sender, @NotNull List<String> args) {
            Example.INSTANCE.reloadPlugin();
            BukkitMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test.value());
        }
        @Subcommand
        BukkitSubcommand test = new BukkitSubcommand("test2") {
            @Override
            public void execute(@NotNull CommandSender sender, @NotNull List<String> args) {
                BukkitMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test2.value().toString());
            }
        };
    };


}
