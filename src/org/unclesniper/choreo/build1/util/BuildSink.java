package org.unclesniper.choreo.build1.util;

import java.io.IOException;
import org.unclesniper.choreo.ChoreoRunException;

public interface BuildSink<ElementT> {

	void sinkBuildObject(ElementT element) throws IOException, ChoreoRunException;

}
