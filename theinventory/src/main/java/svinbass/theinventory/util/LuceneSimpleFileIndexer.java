package svinbass.theinventory.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class LuceneSimpleFileIndexer {

	public static void main(String[] args) throws Exception {

		File indexDir = new File("F:/index/");
		File dataDir = new File("F:/git/vinbass/theinventory/");
		String suffix = "java";
		LuceneSimpleFileIndexer indexer = new LuceneSimpleFileIndexer();
		int numIndex = indexer.index(indexDir, dataDir, suffix);
		System.out.println("Total files indexed " + numIndex);
	}

	private int index(File indexDir, File dataDir, String suffix)
			throws Exception {
		Directory dir = FSDirectory.open(indexDir);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,
				analyzer);
		IndexWriter indexWriter = new IndexWriter(dir, iwc);

		// indexWriter.setUseCompoundFile(false);
		indexDirectory(indexWriter, dataDir, suffix);
		int numIndexed = indexWriter.maxDoc();
		// indexWriter.o.optimize();
		indexWriter.close();
		return numIndexed;
	}

	private void indexDirectory(IndexWriter indexWriter, File dataDir,
			String suffix) throws IOException {
		File[] files = dataDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(indexWriter, f, suffix);
			} else {
				indexFileWithIndexWriter(indexWriter, f, suffix);
			}
		}
	}

	private void indexFileWithIndexWriter(IndexWriter indexWriter, File file,
			String suffix) throws IOException {
		if (file.isHidden() || file.isDirectory() || !file.canRead()
				|| !file.exists()) {
			return;
		}

		if (suffix != null && !file.getName().endsWith(suffix)) {
			return;
		}
		FileInputStream fis = new FileInputStream(file);

		System.out.println("Indexing file " + file.getCanonicalPath());

		Document doc = new Document();
		Field pathField = new StringField("path", file.getPath(),
				Field.Store.YES);
		doc.add(pathField);
		LongField longfield = new LongField("modified", file.lastModified(),
				Field.Store.YES);
		doc.add(longfield);
		TextField textField = new TextField("contents", new BufferedReader(
				new InputStreamReader(fis, "UTF-8")));
		doc.add(textField);

		if (indexWriter.getConfig().getOpenMode() == OpenMode.CREATE) {
			System.out.println("adding " + file);
			indexWriter.addDocument(doc);
		} else {
			// Existing index (an old copy of this document may have been
			// indexed) so
			// we use updateDocument instead to replace the old one matching the
			// exact
			// path, if present:
			System.out.println("updating " + file);
			indexWriter.updateDocument(new Term("path", file.getPath()), doc);
		}

	}

}
