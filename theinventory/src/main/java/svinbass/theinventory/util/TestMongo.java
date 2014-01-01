package svinbass.theinventory.util;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class TestMongo {

	public static void main(String[] args){
		try {
			MongoClient mc = new MongoClient("localhost" , 27017);
			System.out.println("Connected to Mongo on localhost, 27017 \n");
			DB db = mc.getDB("mydb");
			List<String> dbs = mc.getDatabaseNames();
			for (String string : dbs) {
				System.out.println("List of DBs : "+string);
			}
			DBCollection table = db.getCollection("testdata");
			Set<String> tables = db.getCollectionNames();
			for (String string : tables) {
				System.out.println("Collection Names : "+string);
			}
				
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "mongo");
			
			DBCursor cursor = table.find(searchQuery);
			System.out.println("\nQuerying on collection : "+table.getName());
			while(cursor.hasNext()){
				System.out.println("value is : "+cursor.next());
			}
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
