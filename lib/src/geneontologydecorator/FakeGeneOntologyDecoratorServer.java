package geneontologydecorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import us.kbase.common.service.RpcContext;

public class FakeGeneOntologyDecoratorServer {

	private static final String[] termNames = new String[]{
		"electron carrier activity",
		"cyclase regulator activity",
		"phosphatase activator activity",
		"toxin activity",
		"eukaryotic elongation factor-2 kinase activator activity",
		"11-deoxycortisol binding",
		"ADP binding"
	};
	private static final List<String> termRelationTypes = Arrays.asList("community","kbase","expression","fitness");
	private static final int FEATURE_COUNT = 4586;
	
	
	public List<String> getTermRelationTypes(RpcContext jsonRpcContext) {
		return termRelationTypes;
	}

	public List<TermRelation> getTermRelations(GetTermRelationsParams params, RpcContext jsonRpcContext) {
		List<TermRelation> termRelations = new ArrayList<TermRelation>();
		for(String relationType: termRelationTypes){
			termRelations.add(new TermRelation()
				.withRelationType(relationType)
				.withTermId( termId() )
				.withTermName( termName() )
				.withTermPosition( Math.random() ));
		}
		return termRelations;
	}

	private String rndFeatureId(){
		return "" + rndDigit() + rndDigit() + rndDigit() + rndDigit();
	}

	public List<FeatureOntologyPrediction> listFeatures(ListFeaturesParams params, RpcContext jsonRpcContext) {
		List<FeatureOntologyPrediction> features = new ArrayList<FeatureOntologyPrediction>(FEATURE_COUNT);
		for(int i = 0; i < FEATURE_COUNT; i++){
			features.add(new FeatureOntologyPrediction()
					.withFeatureId( rndFeatureId() )
					.withFeatureName( rndFeatureName() )
					.withDistance(Math.random()*10)
					.withCommunityTermId(termId())
					.withCommunityTermName(termName())
					.withKbaseTermId(termId())
					.withKbaseTermName(termName())
					);
		}
		return features;
	}
	
	private String termName(){
		int index = (int) (Math.random()*termNames.length); 
		return termNames[index];
	}
	
	private String termId(){
		return "GO:000" + rndDigit() + rndDigit() + rndDigit() + rndDigit();
	}
	
	private String rndDigit(){
		return "" + ((int) (Math.random()*9));
	}
		
	private String rndLetter(){
		int val = 'a' +  (int) (Math.random()*25);
		return "" + (char)val;
	}

	private String rndFeatureName(){
		return rndLetter() + rndLetter() + rndLetter() + rndLetter().toUpperCase();
	}	
	
	public static void main(String[] args) {
		FakeGeneOntologyDecoratorServer impl = new FakeGeneOntologyDecoratorServer();
		System.out.println("Relation types");
		for(String rt: impl.getTermRelationTypes(null)){
			System.out.println("\t" + rt);		
		}
		
		System.out.println("\ngetTermRelations\n");
		for(TermRelation tr: impl.getTermRelations(null, null)){
			System.out.println("\t" + tr);
		}
		
		System.out.println("\nlistFeatures");
		int index = 0;
		for(FeatureOntologyPrediction fop: impl.listFeatures(null, null)){
			System.out.println("\t" + fop);
			if(index++ > 20) break;
		}		
	}
	
	
}
