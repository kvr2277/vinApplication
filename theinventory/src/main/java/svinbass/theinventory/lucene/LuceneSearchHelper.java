package svinbass.theinventory.lucene;

import java.io.File;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;
import org.apache.lucene.util.Version;

public class LuceneSearchHelper {
	
	public void searchIndex(String indexDirPath, String queryStr, String[] fieldIds)
			throws Exception {

		File indexDir = new File(indexDirPath);
		int maxHits = 100;
		Directory directory = FSDirectory.open(indexDir);
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		QueryBuilder builder = new QueryBuilder(analyzer);
		Query query = builder.createBooleanQuery("contents", queryStr);

		TopDocs topDocs = searcher.search(query, maxHits);
		ScoreDoc[] hits = topDocs.scoreDocs;
		
		System.out.println("\nQuery Word : \""+queryStr + "\" found in " + hits.length+ " docs");

		for (int i = 0; i < hits.length; i++) {
			
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			
			List<IndexableField> fields = d.getFields();
			
			/*for (IndexableField object : fields) {
				System.out.println("Indexed fields : " + object.name());
			}*/
			System.out.println("\nReview Object details :");
			for (String fieldId : fieldIds) {
				System.out.println(fieldId +" : "+d.get(fieldId));
			}
		}
		
	}

}
