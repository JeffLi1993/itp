package sedion.jeffli.wmuitp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sedion.jeffli.wmuitp.constant.database.CommonConstant;
import sedion.jeffli.wmuitp.dao.PaperSubjectRelationDAO;
import sedion.jeffli.wmuitp.entity.PaperSubjectRelation;
import sedion.jeffli.wmuitp.service.PaperSubjectRelationService;

//是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
//@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Service
public class PaperSubjectRelationServiceImpl implements
		PaperSubjectRelationService
{

	private static final String ALL_PAPER_SUBJECT_RELATION = "FROM PaperSubjectRelation AS psr ";

	@Autowired
	private PaperSubjectRelationDAO paperSubjectRelationDAO;

	@Override
	public List<PaperSubjectRelation> getPaperSubjectRelationsByPaperInfoID(
			String paperInfoID) 
	{

		String hql = ALL_PAPER_SUBJECT_RELATION + CommonConstant.ONE_EQUALS_ONE;
		if (paperInfoID != null && !paperInfoID.equals(""))
			hql += " AND psr.paperInfo.piId=" + paperInfoID;

		return paperSubjectRelationDAO.getListByHQL(hql);

	}

}
