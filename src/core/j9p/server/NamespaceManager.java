package j9p.server;

import j9p.ns.Namespace;

/**
 * Per-user styx namepspace manager.
 * 
 * @author zubr
 */
public interface NamespaceManager {
    /**
     * The only function of this class.
     * Returns the Namespace object associated with
     * the 'user' provided in styx message.
     * 
     * @param user the user associated with the namespace
     * @return the namespace object
     */
    public Namespace getUserNS(String user);
}
