package org.unclesniper.choreo.build1.model;

import java.io.IOException;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.build1.run.BuildContext;

public interface Transform {

	Iterable<Artifact> getPrerequisites();

	void addPrerequisite(Artifact prereq);

	void removePrerequisite(Artifact prereq);

	void clearPrerequisites();

	void wouldPerform(BuildContext context, Artifact target);

	void perform(BuildContext context, Artifact target) throws IOException, ChoreoRunException;

}
