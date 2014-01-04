package svinbass.theinventory.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import svinbass.theinventory.model.Review;

public class LuceneIndexingHelper {
	
	IndexWriter indexWriter;
	
	public IndexWriter getIndexWriter() {
		return indexWriter;
	}


	public void setIndexWriter(IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}


	public LuceneIndexingHelper(String indexDirPath){
		
		File indexDir = new File(indexDirPath);
		Directory dir;
		
		try {
			dir = FSDirectory.open(indexDir);
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,
					analyzer);
			indexWriter = new IndexWriter(dir, iwc);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void indexFileWithIndexWriter(Document doc, String field, String fieldId) throws IOException {
		
		if(indexWriter == null){
			//throw exception
			return;
		}			
		
		if (indexWriter.getConfig().getOpenMode() == OpenMode.CREATE) {
			System.out.println("adding Index ");
			indexWriter.addDocument(doc);
		} else {
			System.out.println("updating index ");
			indexWriter.updateDocument(new Term(field , fieldId), doc);
		}
		
		//indexWriter.close();

	}
	
	public void createDocForReview(Review review, String[] fieldIds) throws IOException{
		
		Document doc = new Document();
		
		Field empIdField = new StringField(fieldIds[0], String.valueOf(review.getEmpId()), Field.Store.YES);
		Field vendorIdField = new StringField(fieldIds[1], String.valueOf(review.getVendorId()), Field.Store.YES);
		Field reviewIdField = new StringField(fieldIds[2], String.valueOf(review.getReviewId()), Field.Store.YES);

		doc.add(empIdField);
		doc.add(vendorIdField);		
		doc.add(reviewIdField);
		
		TextField textField = new TextField("contents", review.getComment(),Field.Store.YES);
		doc.add(textField);
		
		indexFileWithIndexWriter(doc, fieldIds[0], String.valueOf(review.getEmpId()));
	}
	
	

}
