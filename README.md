# Add Category as Facet in Alfresco Share Faceted Search

## Add field definition for category property in solr4 schema.xml
```
<fieldType name="string" class="solr.StrField" sortMissingLast="true" />
<field name="acme_region" type="string" indexed="true" stored="true" multiValued="false"/>
<copyField source="category@s_@{http://www.acme.com/model/content/1.0}region" dest="acme_region" />
```
