package com.mobileappdevelopersclub.fapp.models;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;


public class ScheduleItemRepository extends CouchDbRepositorySupport<ScheduleItem> {
	
    public ScheduleItemRepository(CouchDbConnector db) {
            super(ScheduleItem.class, db);
            initStandardDesignDocument();
    }
}