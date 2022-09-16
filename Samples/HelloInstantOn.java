import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.PrintStream;
import java.io.File;
import java.io.*;
import org.eclipse.openj9.criu.CRIUSupport;

public class HelloInstantOn {

	public static void main(String args[]) throws Throwable {
		System.out.println("Start");

		for (int i = 0; i < 3; i++) {
			System.out.print(".");
		}
		
		System.out.println("Pre checkpoint");

		checkPointJVM("checkpointData");

		System.out.println("Post checkpoint");
	}

	public static void checkPointJVM(String path) {
		if (CRIUSupport.isCRIUSupportEnabled()) {
			new CRIUSupport(Paths.get(path))
					.setLeaveRunning(false)
					.setShellJob(true)
					.setFileLocks(true)
					.checkpointJVM();
		} else {
			System.err.println("CRIU is not enabled: " + CRIUSupport.getErrorMessage());
		}

	}
}