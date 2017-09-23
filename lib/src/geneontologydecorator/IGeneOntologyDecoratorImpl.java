package geneontologydecorator;

import java.util.List;
import java.util.Map;

public interface IGeneOntologyDecoratorImpl {

	public List<String> getTermRelationTypes() throws Exception;

	public Map<String,TermProfile> getTermRelations(GetTermRelationsParams params) throws Exception;

	public List<FeatureOntologyPrediction> listFeatures(ListFeaturesParams params) throws Exception;

	public List<TermCategory> getTopTermCategories() throws Exception;
}
