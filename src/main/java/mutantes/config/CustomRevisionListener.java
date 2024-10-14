package mutantes.config;

import org.hibernate.envers.RevisionListener;
import mutantes.audit.Revision;

public class CustomRevisionListener implements RevisionListener {
    public void newRevision(Object revisionEntity){
        final Revision revision = (Revision) revisionEntity;
    }
}
