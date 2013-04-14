/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j9p.ns.managers;

import j9p.ns.Namespace;
import j9p.server.NamespaceManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This namespace manager just stores user-specified
 * namepsaces for each styx user.
 * 
 * @author zubr
 */
public class SimpleNamespaceManager implements NamespaceManager {
    private Map<String, Namespace> namespaces;

    public SimpleNamespaceManager() {
        this.namespaces = new HashMap<String, Namespace>();
    }
    
    public void addUserNS(String user, Namespace ns) {
        namespaces.put(user, ns);
    }
    
    public void removeUserNS(String user) {
        namespaces.remove(user);
    }
    
    public Set<Entry<String, Namespace>> getAll() {
        return Collections.unmodifiableSet(namespaces.entrySet());
    }

    @Override
    public Namespace getUserNS(String user) {
        Namespace ns = namespaces.get(user);
        if (ns == null)
            return namespaces.get("*");
        return ns;
    }
    
}
