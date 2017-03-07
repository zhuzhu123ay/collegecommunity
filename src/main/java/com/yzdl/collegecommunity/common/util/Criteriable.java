package com.yzdl.collegecommunity.common.util;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface Criteriable {
	Criteria getExampleCriteria(Session session);
}
