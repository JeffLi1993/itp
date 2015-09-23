package sedion.jeffli.wmuitp.dao.impl;

import org.springframework.stereotype.Repository;

import sedion.jeffli.wmuitp.base.GenericHibernateDAOImpl;
import sedion.jeffli.wmuitp.dao.DiscussionTopicDAO;
import sedion.jeffli.wmuitp.entity.DiscussionTopic;

@Repository
public class DiscussionTopicDAOImpl extends GenericHibernateDAOImpl<DiscussionTopic> implements DiscussionTopicDAO
{}