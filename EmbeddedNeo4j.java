import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

	private final Driver driver;
	

	public EmbeddedNeo4j( String uri, String user, String password )
	{
		driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
	}

	@Override
	public void close() throws Exception
	{
		driver.close();
	}

	public void printGreeting( final String message )
	{
		try ( Session session = driver.session() )
		{
			String greeting = session.writeTransaction( new TransactionWork<String>()
			{
				@Override
				public String execute( Transaction tx )
				{
					Result result = tx.run( "CREATE (a:Greeting) " +
													 "SET a.message = $message " +
													 "RETURN a.message + ', from node ' + id(a)",
							parameters( "message", message ) );
					return result.single().get( 0 ).asString();
				}
			} );
			System.out.println( greeting );
		}
	}
	
	public LinkedList<String> getPIZZAS()
	{
		 try ( Session session = driver.session() )
		 {	 
			 LinkedList<String> dPIZZA = session.readTransaction( new TransactionWork<LinkedList<String>>()
			 {
				 @Override
				 public LinkedList<String> execute( Transaction tx )
				 {
					 Result result = tx.run( "MATCH (mc:PIZZA) RETURN mc.nombre");
					 LinkedList<String> myPIZZA = new LinkedList<String>();
					 List<Record> registros = result.list();
					 for (int i = 0; i < registros.size(); i++) {
						 
						 myPIZZA.add(registros.get(i).get("mc.nombre").asString());
					 }
					 
					 return myPIZZA;
				 }
			 } );
			 
			 return dPIZZA;
		 }
	}

	public LinkedList<String> getSimilarPizzas(String ing)
	{
		 try ( Session session = driver.session() )
		 {	 
			 LinkedList<String> dPIZZA = session.readTransaction( new TransactionWork<LinkedList<String>>()
			 {
				 @Override
				 public LinkedList<String> execute( Transaction tx )
				 {
					 Result result = tx.run( "MATCH (mc:PIZZA {ingrediente:\"" + ing + "\"}) RETURN mc.nombre");
					 LinkedList<String> myPIZZA = new LinkedList<String>();
					 List<Record> registros = result.list();
					 for (int i = 0; i < registros.size(); i++) {
						 
						 myPIZZA.add(registros.get(i).get("mc.nombre").asString());
					 }
					 
					 return myPIZZA;
				 }
			 } );
			 
			 return dPIZZA;
		 }
	}

	public String insertMovie(String title, int releaseYear, String tagline) {
		try ( Session session = driver.session() ) {
			
			String result = session.writeTransaction( new TransactionWork<String>()
			
			{
				@Override
				public String execute( Transaction tx )
				{
					tx.run( "CREATE (Test:Movie {title:'" + title + "', released:"+ releaseYear +", tagline:'"+ tagline +"'})");
					
					return "OK";
				}
			}
			
			);
			
			return result;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
