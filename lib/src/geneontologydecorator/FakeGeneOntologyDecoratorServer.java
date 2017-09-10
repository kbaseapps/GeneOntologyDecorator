package geneontologydecorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class FakeGeneOntologyDecoratorServer {

	private static int MAX_TERMS_NUMBER = 7;
	private static final String[][] termCategories = new String[][]{
		{"activity 1", "0.0","0.3"},
		{"activity 2", "0.3","0.4"},
		{"protein kinase activity", "0.4","0.5"},
		{"transcription", "0.5","0.7"},
		{"transport activity", "0.7","0.9"},
		{"other", "0.9","1"}
	};

	
	private static final String[] termNames = new String[]{
		"electron carrier activity",
		"cyclase regulator activity",
		"phosphatase activator activity",
		"toxin activity",
		"eukaryotic elongation factor-2 kinase activator activity",
		"11-deoxycortisol binding",
		"ADP binding"
	};
	private static final List<String> termRelationTypes = Arrays.asList("reference","kbase","expression","fitness");
	private static final int FEATURE_COUNT = 4586;
	
	
	public List<String> getTermRelationTypes() {
		return termRelationTypes;
	}

	public Map<String,TermProfile> getTermRelations(GetTermRelationsParams params) {
		Map<String,TermProfile> termRelations = new Hashtable<String,TermProfile>();
		for(String relationType: termRelationTypes){
			
			List<Term> terms = new ArrayList<Term>();
			int termsNumber = (int)(Math.random()* MAX_TERMS_NUMBER);
			Term bestTerm = null;
			for(int i = 0; i < termsNumber; i++ ){
				Term term = new Term()
						.withTermGuid(termId())
						.withTermName(termName())
						.withTermPosition(Math.random())
						.withPvalue(Math.random()/20);
				if(bestTerm == null || term.getPvalue() < bestTerm.getPvalue()){
					bestTerm = term;
				}
				terms.add(term);
			}
						
			termRelations.put(relationType, 
					new TermProfile()
					.withBestTerm(bestTerm)
					.withTerms(terms));			
		}
		return termRelations;
	}

	private String rndFeatureId(){
		return "" + rndDigit() + rndDigit() + rndDigit() + rndDigit();
	}

	public List<FeatureOntologyPrediction> listFeatures(ListFeaturesParams params) {
		List<FeatureOntologyPrediction> features = new ArrayList<FeatureOntologyPrediction>(FEATURE_COUNT);
		for(int i = 0; i < FEATURE_COUNT; i++){
			features.add(new FeatureOntologyPrediction()
					.withFeatureGuid( rndFeatureId() )
					.withFeatureName( rndFeatureName() )
					.withDistance(Math.random()*10)
					.withReferenceTermGuid(termId())
					.withReferenceTermName(termName())
					.withKbaseTermGuid(termId())
					.withKbaseTermName(termName())
					);
		}
		
		Collections.sort(features, new Comparator<FeatureOntologyPrediction>(){
			@Override
			public int compare(FeatureOntologyPrediction o1, FeatureOntologyPrediction o2) {				
				return o1.getDistance().compareTo(o2.getDistance()) * -1;
			}			
		});
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
	
	public List<TermCategory> getTopTermCategories() {		
		List<TermCategory> categories = new ArrayList<TermCategory>(termCategories.length);
		for(String[] vals: termCategories){
			categories.add(
					new TermCategory()
					.withCategoryName(vals[0])
					.withPositionFrom(Double.parseDouble(vals[1]))
					.withPositionTo(Double.parseDouble(vals[2])));
		}		
		return categories;
	}	
	
	public static void main(String[] args) {
		FakeGeneOntologyDecoratorServer impl = new FakeGeneOntologyDecoratorServer();
		
		System.out.println("Relation types");
		for(TermCategory tc: impl.getTopTermCategories()){
			System.out.println("\t" + tc);		
		}
		
		System.out.println("Relation types");
		for(String rt: impl.getTermRelationTypes()){
			System.out.println("\t" + rt);		
		}
		
		System.out.println("\ngetTermRelations\n");
		Map<String, TermProfile> termRelations = impl.getTermRelations(null);
		for(String termRelation: termRelations.keySet()){
			System.out.println("\t" + termRelation + "\t" + termRelations.get(termRelation));
		}
		
		System.out.println("\nlistFeatures");
		int index = 0;
		for(FeatureOntologyPrediction fop: impl.listFeatures(null)){
			System.out.println("\t" + fop);
			if(index++ > 20) break;
		}		
	}
}
