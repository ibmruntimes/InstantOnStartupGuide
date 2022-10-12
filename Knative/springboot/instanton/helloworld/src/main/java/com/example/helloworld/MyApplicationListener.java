package com.example.helloworld;

import java.nio.file.Paths;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import org.eclipse.openj9.criu.CRIUSupport;

@Component
class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		String path = "checkpointData";
                if (CRIUSupport.isCRIUSupportEnabled()) {
                        new CRIUSupport(Paths.get(path))
                                        .setLeaveRunning(false)
                                        .setShellJob(true)
                                        .setFileLocks(true)
                                        .setLogLevel(4)
                                        .setLogFile("logs")
					.setUnprivileged(true)
                                        .checkpointJVM();
                } else {
                        System.err.println("CRIU is not enabled: " + CRIUSupport.getErrorMessage());
                }
	}
}
