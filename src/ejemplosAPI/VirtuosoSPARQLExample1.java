package ejemplosAPI;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import virtuoso.jena.driver.*;

public class VirtuosoSPARQLExample1 {

	public static void main(String[] args) {
		VirtGraph set = new VirtGraph ("name","IP", "dba", "dba");
/*		Select all data in virtuoso	*/
		Query sparql = QueryFactory.create("PREFIX ds:<https://www.datos.gov.co/resource>"
				+ " SELECT ?ciudad  "
				+ "WHERE{ ?x ds:municipio ?ciudad. "
				+ "}");

		VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create (sparql, set);
		ResultSet results = vqe.execSelect();

		while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            Literal ciudad = soln.getLiteral("ciudad");
            System.out.println(ciudad);
		}
	}
}
