package com.acme.facet;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.search.impl.solr.facet.handler.AbstractFacetLabelDisplayHandler;
import org.alfresco.repo.search.impl.solr.facet.handler.FacetLabel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ParameterCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryFacetLabelDisplayHandler extends AbstractFacetLabelDisplayHandler {

	private static final Logger logger = LoggerFactory.getLogger(CategoryFacetLabelDisplayHandler.class);

	public CategoryFacetLabelDisplayHandler(Set<String> supportedFieldFacets) {
		ParameterCheck.mandatory("supportedFieldFacets", supportedFieldFacets);
		this.supportedFieldFacets = Collections.unmodifiableSet(new HashSet<>(supportedFieldFacets));
		logger.debug("category facet label");
	}

	@Override
	public FacetLabel getDisplayLabel(String value) {

		logger.debug("value={}", value);

		NodeRef categoryNode = new NodeRef(value);
		NodeService nodeService = serviceRegistry.getNodeService();

		if (!nodeService.exists(categoryNode)) {
			logger.error("No category found for {}, returns null", value);
			return null;
		} else {
			Map<QName, Serializable> props = nodeService.getProperties(categoryNode);
			String name = (String) props.get(ContentModel.PROP_NAME);
			logger.debug("name={}", name);

			return new FacetLabel(value, name, -1);
		}
	}

}