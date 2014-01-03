package svinbass.theinventory.util;

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

public class LuceneSimpleSearcher {

	public static void main(String[] args) throws Exception {

		File indexDir = new File("F:/index/");
		String query = "purchase";
		int hits = 100;
		LuceneSimpleSearcher searcher = new LuceneSimpleSearcher();
		searcher.searchIndex(indexDir, query, hits);
	}

	private void searchIndex(File indexDir, String queryStr, int maxHits)
			throws Exception {

		Directory directory = FSDirectory.open(indexDir);
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		QueryBuilder builder = new QueryBuilder(analyzer);
		Query query = builder.createBooleanQuery("contents", queryStr);

		TopDocs topDocs = searcher.search(query, maxHits);
		ScoreDoc[] hits = topDocs.scoreDocs;

		for (int i = 0; i < hits.length; i++) {
			
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println(d.get("path"));
			List<IndexableField> fields = d.getFields();
			
			for (IndexableField object : fields) {
				System.out.println("" + object.name());
			}

			System.out.println(d.get("modified"));
		}
		System.out.println("Found " + hits.length);
	}

}
