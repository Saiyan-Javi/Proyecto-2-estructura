import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

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

	public LinkedList<String> getIngredients()
	{
		 try ( Session session = driver.session() )
		 {	 
			 LinkedList<String> dPIZZA = session.readTransaction( new TransactionWork<LinkedList<String>>()
			 {
				 @Override
				 public LinkedList<String> execute( Transaction tx )
				 {
					 Result result = tx.run( "MATCH (ing:INGREDIENTE) RETURN ing.ingrediente");
					 LinkedList<String> ingredientes = new LinkedList<String>();
					 List<Record> registros = result.list();
					 for (int i = 0; i < registros.size(); i++) {
						 
						 ingredientes.add(registros.get(i).get("ing.ingrediente").asString());
					 }
					 
					 return ingredientes;
				 }
			 } );
			 
			 return dPIZZA;
		 }
	}

	public LinkedList<String> getSimilarPizzas(String p) //Este método devuelve pizzas en base a otra pizza.
	{
		try ( Session session = driver.session() )
		{
			LinkedList<String> dPIZZA = session.readTransaction( new TransactionWork<LinkedList<String>>()
			{
				@Override
				public LinkedList<String> execute( Transaction tx )
				{
					LinkedList<String> ingredientes = new LinkedList<>();
					LinkedList<String> list = new LinkedList<>();

						Result result = tx.run( "MATCH (mc:PIZZA {nombre: \"" + p + "\"})-[:CONTIENE]->(b:INGREDIENTE) RETURN b.ingrediente");
						List<Record> registros = result.list();
						for (int i = 0; i < registros.size(); i++) {
							String piz = registros.get(i).get("b.ingrediente").asString();

							ingredientes.add(piz);
						}

					list = getSimilarPizzas(ingredientes);

					return list;
				}
			} );

			return dPIZZA;
		}
	}

	public LinkedList<String> getSimilarPizzas(LinkedList<String> ingr) //Este método devuelve pizzas en base a los ingredientes.
	{
		 try ( Session session = driver.session() )
		 {	 
			 LinkedList<String> dPIZZA = session.readTransaction( new TransactionWork<LinkedList<String>>()
			 {
				 @Override
				 public LinkedList<String> execute( Transaction tx )
				 {
					HashMap<String, Integer> myPIZZA = new HashMap<String, Integer>();

					for (String ing : ingr) {
						Result result = tx.run( "MATCH (mc:PIZZA)-[:CONTIENE]->(:INGREDIENTE {ingrediente:\"" + ing + "\"}) RETURN mc.nombre");
						List<Record> registros = result.list();
						for (int i = 0; i < registros.size(); i++) {
							String piz = registros.get(i).get("mc.nombre").asString();
							
							if (myPIZZA.containsKey(piz)) {
								myPIZZA.put(piz, myPIZZA.get(piz) + 1);
							} else {
								myPIZZA.put(piz, 1);
							}
						}
					}
					

						int highestVal = 0;
						for (String a: myPIZZA.keySet()) {
							if (myPIZZA.get(a) > highestVal) {
								highestVal = myPIZZA.get(a);
							}
						}

						LinkedList<String> list = new LinkedList<>();
						for (String b : myPIZZA.keySet()) {
							if (highestVal == myPIZZA.get(b)) {
								list.add(b);
							}
						}

					 
					 return list;
				 }
			 } );
			 
			 return dPIZZA;
		 }
	}

}
