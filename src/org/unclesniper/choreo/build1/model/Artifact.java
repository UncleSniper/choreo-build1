package org.unclesniper.choreo.build1.model;

import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.unclesniper.choreo.TypeMap;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.build1.util.BuildSink;
import org.unclesniper.choreo.build1.run.BuildContext;

public interface Artifact {

	enum Currency {
		UNKNOWN,
		UP_TO_DATE,
		NOT_UP_TO_DATE
	}

	public static final class HashChunk {

		private final String key;

		private final byte[] hash;

		public HashChunk(String key, byte[] hash) {
			this.key = key;
			this.hash = hash;
		}

		public String getKey() {
			return key;
		}

		public byte[] getHash() {
			return hash;
		}

	}

	public interface ReferenceCookie<ReferenceT> {

		ReferenceT getReference();

		void releaseReference() throws IOException, ChoreoRunException;

	}

	TypeMap getProperties();

	Transform getGeneratingTransform();

	void setGeneratingTransform(Transform transform);

	void setPredictivelyCurrent(Currency currency);

	void setDefinitivelyCurrent(Currency currency);

	void require(BuildContext context) throws IOException, ChoreoRunException;

	void wouldRequire(BuildContext context) throws IOException, ChoreoRunException;

	boolean isPresent();

	boolean wouldBePresent();

	void getPredictedModificationTimestamp(BuildSink<Long> sink) throws IOException, ChoreoRunException;

	void getModificationTimestamp(BuildSink<Long> sink) throws IOException, ChoreoRunException;

	void getPredictedSHA256Hash(BuildSink<HashChunk> sink) throws IOException, ChoreoRunException;

	void getSHA256Hash(BuildSink<HashChunk> sink) throws IOException, ChoreoRunException;

	void getPredictedSHA512Hash(BuildSink<HashChunk> sink) throws IOException, ChoreoRunException;

	void getSHA512Hash(BuildSink<HashChunk> sink) throws IOException, ChoreoRunException;

	void wouldModify(BuildContext context);

	void remove() throws IOException, ChoreoRunException;

	void wouldRemove();

	InputStream getInputStream(BuildContext context) throws IOException, ChoreoRunException;

	ReferenceCookie<OutputStream> getOutputStream(BuildContext context, ReferenceMood mood)
			throws IOException, ChoreoRunException;

	void getFileReference(BuildContext context, ReferenceDirection direction, ReferenceMood mood,
			File suffix, BuildSink<ReferenceCookie<File>> sink) throws IOException, ChoreoRunException;

	String getLabel();

	File getPathLabel();

	URL getURLLabel();

}
