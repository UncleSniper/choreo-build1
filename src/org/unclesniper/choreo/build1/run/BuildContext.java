package org.unclesniper.choreo.build1.run;

public final class BuildContext {

	private final BuildUI ui;

	private long virtualTime;

	private final long[] virtualHash = new long[8];

	public BuildContext(BuildUI ui) {
		this.ui = ui;
	}

	public BuildUI getUI() {
		return ui;
	}

	public long nextVirtualTime() {
		return ++virtualTime;
	}

	public byte[] nextVirtualHash() {
		for(int i = 0; i < virtualHash.length; ++i) {
			if(++virtualHash[i] != 0l)
				break;
		}
		byte[] hash = new byte[virtualHash.length * 8];
		for(int i = 0; i < virtualHash.length; ++i)
			BuildContext.longToBytes(hash, i * 8, virtualHash[i]);
		return hash;
	}

	private static void longToBytes(byte[] buffer, int offset, long value) {
		for(int i = 0; i < 8; ++i) {
			buffer[offset + i] = (byte)(value & 0xFFl);
			value >>>= 8;
		}
	}

}
