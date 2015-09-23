package sedion.jeffli.wmuitp.dao;

import java.util.List;

import sedion.jeffli.wmuitp.base.GenericHibernateDAO;
import sedion.jeffli.wmuitp.entity.PaperSubjectRelation;

public interface PaperSubjectRelationDAO extends
		GenericHibernateDAO<PaperSubjectRelation>
{
	
	List<PaperSubjectRelation> getPaperSubjectRelationByPaperInfoId(String paperInfoId);
}
