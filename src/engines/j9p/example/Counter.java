
package j9p.example;

import j9p.ns.handlers.ProcessFile;


public class Counter implements ProcessFile.Listener {

	private long counter = 0;
    
    public Counter() {
    }
	
    @Override
	public void asInput(byte[] data, long offset) {
		counter += data.length;
	}

    @Override
	public byte[] getOutput(long offset, int count) {
		if (offset != 0) return null;
		return (counter + "\n").getBytes();
	}
}
