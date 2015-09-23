package sedion.jeffli.wmuitp.service;

import java.util.List;

import sedion.jeffli.wmuitp.entity.PaperSubjectRelation;


public interface PaperSubjectRelationService
{
	/**
	 * 获取试卷试题
	 * @param paperInfoID 试卷ID
	 * @return
	 */
	List<PaperSubjectRelation> getPaperSubjectRelationsByPaperInfoID(
			String paperInfoID);

}
