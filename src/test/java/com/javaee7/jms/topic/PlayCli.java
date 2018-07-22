package com.javaee7.jms.topic;

import org.jboss.as.arquillian.api.ServerSetupTask;
import org.jboss.as.arquillian.container.ManagementClient;
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.scriptsupport.CLI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PlayCli implements ServerSetupTask {

	protected List<String> cliCommands = new ArrayList<>();

	protected void run(final ManagementClient managementClient, final List<String> cliCommands) {
		cliCommands.addAll(cliCommands);
		final String host = managementClient.getWebUri().getHost();
		processCli(host, cliCommands);
	}
	
	@Override
	public void tearDown(ManagementClient managementClient, String s) throws Exception {
		final String host = managementClient.getWebUri().getHost();
		final List<String> removeCli = 
				cliCommands.stream()
				.map(cli->cli.replace("\\:.*", ""))
				.map(cli->{return cli+":remove";})
				.collect(Collectors.toList());
		processCli(host, removeCli);
	}


	private void processCli(String host, List<String> cliCommands) {
		CLI cli = CLI.newInstance();
		try {
			cli.connect(host, 9990, null, null);
			CommandContext commandContext = cli.getCommandContext();
			cliCommands.forEach(command->{commandContext.handleSafe(command.trim());});
		} finally {
			cli.disconnect();
		}
	}
}