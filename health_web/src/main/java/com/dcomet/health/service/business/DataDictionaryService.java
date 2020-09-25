package com.dcomet.health.service.business;

import com.dcomet.fw.exception.DcometServiceException;
import com.dcomet.module.domain.Entity;
import com.dcomet.module.service.DCometDictionaryService;
import java.util.List;

public interface DataDictionaryService extends DCometDictionaryService {

    public List<Entity> getUserEntity(Integer entityRid, boolean includeChild) throws DcometServiceException;

}
