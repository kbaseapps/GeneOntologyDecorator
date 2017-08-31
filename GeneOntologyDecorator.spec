/*
A KBase module: GeneOntologyDecorator
*/

module GeneOntologyDecorator {

        
    typedef structure{
    	string feature_id;
    } GetTermRelationsParams;
    
    
    typedef structure{
    	string relation_type;
    	float term_position;
    	string term_id;
    	string term_name;
    } TermRelation;
    
    funcdef getTermRelationTypes() returns (list<string>);

    funcdef getTermRelations(GetTermRelationsParams params) returns (list<TermRelation>) authentication required;
    
    
    typedef structure{
    	string genome_ref; 
    } ListFeaturesParams;
    
    typedef structure{
    	string feature_id;
    	string feature_name;
    	float distance;
    	string community_term_name;
    	string community_term_id;
    	string kbase_term_name;
    	string kbase_term_id;
    	
    } FeatureOntologyPrediction;
    
    funcdef listFeatures(ListFeaturesParams params) returns (list<FeatureOntologyPrediction>) authentication required;
};
