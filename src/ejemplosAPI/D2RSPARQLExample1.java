package ejemplosAPI;

import org.apache.jena.query.Query;

import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import virtuoso.jena.driver.*;
public class D2RSPARQLExample1 {

	public static void main(String[] args) {

		VirtGraph set = new VirtGraph ("name","IP", "dba", "dba");
		Query sparql = QueryFactory.create(
				"PREFIX ds:<https://www.datos.gov.co/resource>"
				+"	SELECT  ?ciudad "
				+"	WHERE{"
				+"	?x ds:nombre_ciudad ?ciudad;"
				+"}");
		VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create (sparql, set);
		ResultSet results = vqe.execSelect();
		while (results.hasNext()) {
			//Datos 
            QuerySolution querySolution = results.nextSolution();
            Literal nombreZona = querySolution.getLiteral("nombre");
            System.out.println(nombreZona);
		}
		
		
		
		String sparqlQueryString1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
									+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
									+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
									+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
									+ "SELECT   ?ciudad "
									+ "WHERE {"
									+ "?x vocab:Departamento_ciudad ?ciudad; "
									+ "}";
		
        // Crear el objeto consulta
        Query query = QueryFactory.create(sparqlQueryString1);
        QueryExecution qexec = QueryExecutionFactory.sparqlService("IP", query);

        ResultSet resultsd2r = qexec.execSelect();
		while (resultsd2r.hasNext()) {
            QuerySolution querySolution = resultsd2r.nextSolution();
            Literal nombre = querySolution.getLiteral("nombre");
            Literal ciudad = querySolution.getLiteral("municipio");

            System.out.println(ciudad + " " + nombre);
		}
	
	}

}
