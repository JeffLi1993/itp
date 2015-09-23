package sedion.jeffli.wmuitp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.PaperSubjectRelationDAO;
import sedion.jeffli.wmuitp.entity.PaperSubjectRelation;

@Repository
public class PaperSubjectRelationDAOImpl extends
		GenericHibernateDAOImpl<PaperSubjectRelation> implements
		PaperSubjectRelationDAO
{
	private static final String ALL_PAPER_SUBJECT_RELATION = "FROM PaperSubjectRelation AS psr ";
		
	@Override
	public List<PaperSubjectRelation> getPaperSubjectRelationByPaperInfoId(
			String paperInfoId) 
	{
		StringBuilder hql = new StringBuilder(ALL_PAPER_SUBJECT_RELATION);
		hql.append(CommonConstant.ONE_EQUALS_ONE);
		hql.append(" AND psr.paperInfo.piId=");
		hql.append(paperInfoId);
		return getListByHQL(hql.toString());
	}

}