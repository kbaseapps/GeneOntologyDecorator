/*
A KBase module: GeneOntologyDecorator
*/

module GeneOntologyDecorator {

	typedef string term_relation_type;

	funcdef getTermRelationTypes() returns (list<term_relation_type>);
    
    typedef structure{
    	string category_name;
    	float position_from;
    	float position_to;
    } TermCategory;
    
    funcdef getTopTermCategories() returns (list<TermCategory>);
    
    typedef structure{
    	string term_guid;
    	string term_name;
    	float term_position;
    	float pvalue;    
    } Term;
    
    typedef structure{
    	Term best_term;
    	list<Term> terms;
    } TermProfile;
    
        
    typedef structure{
    	string feature_guid;
    } GetTermRelationsParams;
    
    funcdef getTermRelations(GetTermRelationsParams params) returns (mapping<term_relation_type,TermProfile>) authentication required;
    
    
    typedef structure{
    	string genome_ref; 
    	string genome_guid; 
    } ListFeaturesParams;
    
    typedef structure{
    	string feature_guid;
    	string feature_name;
    	float distance;
    	string reference_term_name;
    	string reference_term_guid;
    	string kbase_term_name;
    	string kbase_term_guid;
    	
    } FeatureOntologyPrediction;
    
    funcdef listFeatures(ListFeaturesParams params) returns (list<FeatureOntologyPrediction>) authentication required;
};
