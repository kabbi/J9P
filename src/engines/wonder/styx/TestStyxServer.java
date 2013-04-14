/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wonder.styx;

import j9p.Library;
import j9p.Server;
import j9p.auth.Authenticator;
import j9p.ns.managers.XmlNamespaceManager;
import j9p.server.NamespaceManager;
import j9p.util.Args;

/**
 *
 * @author zubr
 */
public class TestStyxServer extends Server {
    
    TestStyxServer(int port, int maxConn, boolean auth, NamespaceManager mgr) {
        super(port, maxConn, auth, mgr);
    }
    
    void start() {
        super.run();
    }
    
    
	/**
	 * Starts test styx server.
	 * @param argv
	 */
	public static void main (String[] argv) {
		
		// punch welcome message
		System.out.println("==================================");
		System.out.println("J9P/StyxServer v" + Library.getVersion());
		System.out.println("Author: " + Library.getAuthor());
		System.out.println("==================================");
		System.out.println();
		System.out.flush();
		
		// parse commandline options
		Args args = new Args(argv, "p:s:a:");
		int port = args.getWordOpt("-p", 6666);
		int maxSessions = args.getWordOpt("-s", 10);
		String authConfig = args.getStringOpt("-a", null);
		boolean auth = (authConfig != null);
		
		int argc = args.getNumArgs();
		if (argc != 1) {
			System.err.println("Only one positional argument (namespace config) allowed!");
			System.err.println("Usage: StyxServer [-p <port>] [-s <maxSessions>] [-a <auth config>] <namespace config>");
			System.err.println("Defaults: Port = 6666, maxSessions = 10");
			System.err.flush();
			return;
		}
		
		System.out.println ("Using port " + port + " for max. " + maxSessions + " concurrent sessions.");
		
		// read namespace configuration
		String nsConfig = args.getStringArg(0, null);
		XmlNamespaceManager mgr = new XmlNamespaceManager();
		System.out.println("Reading namespace configurarations from '" + nsConfig + "'...");
		if (!mgr.readConfig(nsConfig)) {
			System.err.println("Can't read namespace definitions from file");
			System.err.println(" '" + nsConfig + "' -- terminating...");
			System.err.flush();
			return;
		}

		// Read authentication data
		if (auth) {
			System.out.println("Authentication MANDATORY - using '" + authConfig + "' as configuration data file...");
			if (!Authenticator.getInstance().readAuthConfig(authConfig)) {
				System.err.println("Reading/parsing of authentication data failed!");
				System.err.print("Correct problem or re-start server without authentication (no '-a' option)");
				System.err.flush();
				return;
			}
		} else
			System.out.println("Authentication SWITCHED OFF - not required for clients.");
		
		// instantiate new Styx server
		TestStyxServer srv = new TestStyxServer(port, maxSessions, auth, mgr);
		System.out.println();
		System.out.println("Now serving up to " + maxSessions + " sessions on port " + port + " ...");
		
		// run server
		srv.start();
	}
}
